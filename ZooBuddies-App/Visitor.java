import java.util.*;
class Visitor implements User {
    private String name;
    private int age;
    private int phone;
    private String email;
    private String password;
    private double balance;
    private Membership membership;
    private List<Ticket> purchasedTickets;
    private List<Feedback> feedbackList;
    private static List<Discount> additionalDiscounts;

    public Visitor(String name, int age, int phone, String email, String password, double initialBalance, List<Discount> additionalDiscounts) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.balance = initialBalance;
        this.membership = null;
        this.purchasedTickets = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
        this.additionalDiscounts = additionalDiscounts;
    } 

    @Override
    public int getAge() {
        return age;
    }
   public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public double getBalance() {
        return balance;
    }
    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }
    public String getName() {
        return name;
    } 
    public Membership getMembership() {
        return membership;
    }


    public static Visitor register() {
        Scanner sc = new Scanner(System.in); 
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your age: ");
            int age = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter your phone number: ");
            int phone = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Enter a password: ");
            String password = sc.nextLine();
            System.out.print("Enter your initial balance: ");
            double balance = sc.nextDouble(); // initial balance
            sc.nextLine();
    
            return new Visitor(name, age, phone, email, password, balance, additionalDiscounts); 
    }
     public static Visitor login(String email, String password, List<Visitor> registeredVisitors) {
        for (Visitor visitor : registeredVisitors) {
            if (visitor.getEmail().equals(email) && visitor.getPassword().equals(password)) {
                return visitor;  
            }
        }
        return null; 
    }

    public void exploreZoo(List<Attraction> attractions,List<Animal> animals) {
        Scanner sc = new Scanner(System.in);
            System.out.println("Explore the Zoo:");
            System.out.println("1. Attractions");
            System.out.println("2. Animals");
            System.out.println("3. Exit");  

            System.out.print("Enter your choice: ");
            int exploreChoice = sc.nextInt();
            sc.nextLine();

            switch (exploreChoice) {
                case 1:
                    // Explore Attractions
                    System.out.println("Attractions:");
                    for (int i = 0; i < attractions.size(); i++) {
                        System.out.println((i + 1) + ". " + attractions.get(i).getName());
                    }
                    System.out.print("Select an attraction to explore (enter the number): ");
                    int selectedAttractionIndex = sc.nextInt();
                    sc.nextLine();
                    if (selectedAttractionIndex >= 1 && selectedAttractionIndex <= attractions.size()) {
                        Attraction selectedAttraction = attractions.get(selectedAttractionIndex - 1);
                        System.out.println("Welcome to " + selectedAttraction.getName());
                    } else {
                        System.out.println("Invalid attraction selection.");
                    }
                    break;
                case 2:
                    // Explore Animals
                    System.out.println("Animals:");
                    for (int i = 0; i < animals.size(); i++) {
                        System.out.println((i + 1) + ". " + animals.get(i).getName());
                    }
                    System.out.print("Select an animal to explore (enter the number): ");
                    int selectedAnimalIndex = sc.nextInt();
                    sc.nextLine();
                    if (selectedAnimalIndex >= 1 && selectedAnimalIndex <= animals.size()) {
                        Animal selectedAnimal = animals.get(selectedAnimalIndex - 1);
                        System.out.println("You are now viewing " + selectedAnimal.getName());
                    } else {
                        System.out.println("Invalid animal selection.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the Explore the Zoo menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    
    public void buyMembership(List<Membership> memberships) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Buy Membership:");
        System.out.println("1. Premium Membership (₹100.0)");
        System.out.println("2. Basic Membership (₹50.0)");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1 || choice == 2) {
            Membership selectedMembership = choice == 1
                    ? new Membership("Premium Membership", 100.0)
                    : new Membership("Basic Membership", 50.0);

            if (balance >= selectedMembership.getPrice()) {
                balance -= selectedMembership.getPrice();
                membership = selectedMembership;
                System.out.println(selectedMembership.getName() + " purchased successfully. Your balance is now ₹" + balance);
                return;
            } else {
                System.out.println("Insufficient balance to purchase the membership.");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        } 
    }
    public void deductBalance(double amount) {
        balance -= amount;
    }
    
    public void buyTickets(List<Attraction> attractions, List<Discount> availableDiscounts) {
        Scanner sc = new Scanner(System.in);
    
        while (true) {
            System.out.println("Buy Tickets:");
            System.out.println("Remember that 1 ticket is used for one-time usage for a particular attraction. You can buy only 1 ticket per time. (0 to exit)");
            int numberOfTickets = sc.nextInt();
            sc.nextLine();
    
            if (numberOfTickets == 0) {
                return; // Exit loop
            }
    
            System.out.println("Select an Attraction to Buy a Ticket:");
            for (int i = 0; i < attractions.size(); i++) {
                Attraction attraction = attractions.get(i);
                System.out.println((i + 1) + ". " + attraction.getName() + " (₹" + attraction.getPrice() + ")");
            }
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
    
            if (choice >= 1 && choice <= attractions.size()) {
                Attraction selectedAttraction = attractions.get(choice - 1);
                double ticketPrice = selectedAttraction.getPrice() * numberOfTickets;
    
                // Apply discounts to the ticket price
                for (Discount discount : availableDiscounts) {
                    if (isDiscountApplicable(discount)) {
                        if (discount.getCategory().equalsIgnoreCase("PERCENTAGE")) {
                            // Apply a percentage discount
                            ticketPrice *= (1 - discount.getPercentage() / 100.0);
                        } else if (discount.getCategory().equalsIgnoreCase("AMOUNT")) {
                            // Apply a fixed amount discount
                            ticketPrice -= discount.getPercentage();
                        }
                    }
                }
    
                if (balance >= ticketPrice) {
                    balance -= ticketPrice;
                    for (int i = 0; i < numberOfTickets; i++) {
                        purchasedTickets.add(new Ticket(selectedAttraction, choice));
                    }
                    System.out.println("The ticket for " + selectedAttraction.getName() + " was purchased successfully. Your balance is now ₹" + balance);
                } else {
                    System.out.println("Insufficient balance to purchase the ticket.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public boolean isDiscountApplicable(Discount discount) {
        int visitorAge = this.getAge();
        if (discount.getCategory().equalsIgnoreCase("Minor") && visitorAge < 18) {
            return true;
        } else if (discount.getCategory().equalsIgnoreCase("Senior Citizen") && visitorAge > 60) {
            return true;
        }
        return false;
    }
    
    public void viewDiscounts(Visitor visitor) {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("View Discounts:");
        List<Discount> availableDiscounts = createAvailableDiscounts(additionalDiscounts);
        
        for (int i = 0; i < availableDiscounts.size(); i++) {
            Discount discount = availableDiscounts.get(i);
            System.out.println((i + 1) + ". " + discount.getCategory() + " (" + discount.getDescription() + ") - " + discount.getCode());
        }
    
        System.out.print("Enter your choice to apply a discount (or 0 to go back): ");
        int discountChoice = sc.nextInt();
        sc.nextLine();
    
        if (discountChoice == 0) {
            // Go back to the visitor menu
            return;
        }
    
        if (discountChoice >= 1 && discountChoice <= availableDiscounts.size()) {
            Discount selectedDiscount = availableDiscounts.get(discountChoice - 1);
            selectedDiscount.applyDiscount(visitor);
            System.out.println("You selected: " + selectedDiscount.getCategory() + " - " + selectedDiscount.getDescription() + " (" + selectedDiscount.getCode() + ")");
        } else {
            System.out.println("Invalid choice.");
        }
    }
    
    private List<Discount> createAvailableDiscounts(List<Discount> additionalDiscounts) {
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(1, "Minor (10% discount)", 10, "MINOR10", "Minor"));
        discounts.add(new Discount(2, "Senior Citizen (20% discount)", 20, "SENIOR20", "Senior Citizen"));
    
        // Add additional discounts if they exist
        if (additionalDiscounts != null && !additionalDiscounts.isEmpty()) {
            discounts.addAll(additionalDiscounts);
        }
    
        return discounts;
    }
    
    
    public String getVisitorCategory(Visitor visitor) {
        int visitorAge = visitor.getAge(); // Get the visitor's age using the getAge() method from the Visitor object.
    
        if (visitorAge < 18) {
            return "Minor";
        } else if (visitorAge >= 60) {
            return "Senior Citizen";
        } else {
            return "Adult";  
        }
    }
    
    
    public void viewSpecialDeals(List<SpecialDeal> specialDeals) {
        System.out.println("View Special Deals:");
        List<SpecialDeal> specialDeal = new ArrayList<>();
    
        SpecialDeal deal1 = new SpecialDeal(1, "Buy 2 tickets and get 15% off", 2, 15);
        SpecialDeal deal2 = new SpecialDeal(2, "Buy 3 tickets and get 30% off", 3, 30);
    
        specialDeal.add(deal1);
        specialDeal.add(deal2);
    
        for (int i = 0; i < specialDeal.size(); i++) {
            SpecialDeal deal = specialDeal.get(i);
            System.out.println((i + 1) + ". " + deal.getDescription());
        }
    }


 
    public void visitAnimals(List<Animal> animals) {
        Scanner sc = new Scanner(System.in);
    
        while (true) {
            System.out.println("Choose an animal to visit (Enter the corresponding number):");
            for (int i = 0; i < animals.size(); i++) {
                System.out.println((i + 1) + ". " + animals.get(i).getName());
            }
            System.out.println("0. Exit");
    
            int choice = sc.nextInt();
            sc.nextLine();
    
            if (choice == 0) {
                return; 
            } else if (choice >= 1 && choice <= animals.size()) {
                Animal animal = animals.get(choice - 1);
                System.out.println("You are visiting " + animal.getName());
    
                while (true) {
                    System.out.println("Choose an action:");
                    System.out.println("1. Feed the animal");
                    System.out.println("2. Read about the animal");
                    System.out.println("3. Exit");
    
                    int action = sc.nextInt();
                    sc.nextLine();
    
                    switch (action) {
                        case 1:
                            animal.makeNoise();
                            break;
                        case 2:
                            String description = animal.getDescription();
                            System.out.println("Animal Description: " + description);
                            break;
                        case 3:
                            return;  
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid animal choice. Please try again.");
            }
        }
    } 

  
    public void purchaseTickets(Attraction attraction, int quantity) {
        Ticket newTicket = new Ticket(attraction, quantity);
        purchasedTickets.add(newTicket);
        System.out.println("The ticket for " + attraction.getName() + " was purchased successfully. Your balance is now ₹" + balance);
    }

    // public void visitAttractions(List<Ticket>tickets,List<Attraction> attractions) {
    //     Scanner sc = new Scanner(System.in);
    
    //     while (true) {
    //         System.out.println("Visit Attractions:");
    //         for (int i = 0; i < attractions.size(); i++) {
    //             Attraction attraction = attractions.get(i);
    //             System.out.println((i + 1) + ". " + attraction.getName());
    //         }
    
    //         System.out.print("Enter the attraction number to visit (0 to exit): ");
    //         int choice = sc.nextInt();
    //         sc.nextLine();
    
    //         if (choice == 0) {
    //             return;
    //         } else if (choice >= 1 && choice <= attractions.size()) {
    //             Attraction selectedAttraction = attractions.get(choice - 1);
    
    //             if (membership != null) {
    //                 if (membership.getName().equals("Basic Membership")) {
    //                     while (hasValidTicketForAttraction(selectedAttraction) && canVisitAttraction(selectedAttraction)) {
    //                         decrementVisitsForAttraction(selectedAttraction);
    //                         System.out.println("Welcome to " + selectedAttraction.getName());
    //                         System.out.println("Thank you for visiting " + selectedAttraction.getName() + ". Hope you enjoyed the attraction.");
    //                     }
    
    //                     if (!hasValidTicketForAttraction(selectedAttraction)) {
    //                         System.out.println("Your tickets for " + selectedAttraction.getName() + " have expired.");
    //                         System.out.print("Do you want to rebuy tickets for this attraction (yes/no)? ");
    //                         String rebuyChoice = sc.nextLine().toLowerCase();
    //                         if (rebuyChoice.equals("yes")) {
    //                             // Implement logic to rebuy tickets for the attraction
    //                             System.out.println("Tickets rebought successfully.");
    //                         } else {
    //                             System.out.println("You cannot visit " + selectedAttraction.getName() + "without valid tickets.");
    //                         }
    //                     }
    //                 } else if (membership.getName().equals("Premium Membership")) {
    //                     // Premium Membership - unlimited visits to attractions
    //                     decrementVisitsForAttraction(selectedAttraction);
    //                     System.out.println("Welcome to " + selectedAttraction.getName());
    //                     System.out.println("Thank you for visiting " + selectedAttraction.getName() + ". Hope you enjoyed the attraction.");
    //                 }
    //             } else {
    //                 System.out.println("You must have a membership to visit attractions.");
    //             }
    //         } else {
    //             System.out.println("Invalid choice. Please try again.");
    //         }
    //     }
    // }

    

    public int getNumberOfTicketsForAttraction(Attraction attraction) { 
        int count = 0;
        for (Ticket ticket : purchasedTickets) {
            if (ticket.getAttraction() == attraction) {
                count++;
            }
        }
        return count;
    }

    public void decrementTicketsForAttraction(Attraction attraction, int count) { 
        Iterator<Ticket> iterator = purchasedTickets.iterator();
        int remainingCount = count;
        while (iterator.hasNext() && remainingCount > 0) {
            Ticket ticket = iterator.next();
            if (ticket.getAttraction() == attraction) {
                iterator.remove();
                remainingCount--;
            }
        }
    }
      public void incrementTicketsForAttraction(Attraction attraction, int count) { 
        Iterator<Ticket> iterator = purchasedTickets.iterator();
        int remainingCount = count;
        while (iterator.hasNext() && remainingCount > 0) {
            Ticket ticket = iterator.next();
            if (ticket.getAttraction() == attraction) {
                iterator.remove();
                remainingCount++;
            }
        }
    }

    public void visitAttractions(List<Ticket>tickets,List<Attraction> attractions) {
        Scanner sc = new Scanner(System.in);
    
        while (true) {
            System.out.println("Visit Attractions:");
            for (int i = 0; i < attractions.size(); i++) {
                Attraction attraction = attractions.get(i);
                System.out.println((i + 1) + ". " + attraction.getName());
            }
    
            System.out.print("Enter the attraction number to visit (0 to exit): ");
            int choice = sc.nextInt();
            sc.nextLine();
    
            if (choice == 0) {
                return;
            } else if (choice >= 1 && choice <= attractions.size()) {
                Attraction selectedAttraction = attractions.get(choice - 1);
    
                if (membership != null) {
                    if (membership.getName().equals("Basic Membership")) {
                        int numberOfTickets = getNumberOfTicketsForAttraction(selectedAttraction);
                        while (hasValidTicketForAttraction(selectedAttraction) && canVisitAttraction(selectedAttraction)) {
                            decrementVisitsForAttraction(selectedAttraction);
                            decrementTicketsForAttraction(selectedAttraction, numberOfTickets);
                            System.out.println("Welcome to " + selectedAttraction.getName());
                            System.out.println("Thank you for visiting " + selectedAttraction.getName() + ". Hope you enjoyed the attraction.");
                        }
    
                        if (!hasValidTicketForAttraction(selectedAttraction)) {
                            System.out.println("Your tickets for " + selectedAttraction.getName() + " have expired.");
                            System.out.print("Do you want to rebuy tickets for this attraction (yes/no)? ");
                            String rebuyChoice = sc.nextLine().toLowerCase();
                            if (rebuyChoice.equals("yes")) {
                                // Implement logic to rebuy tickets for the attraction
                                double ticketPrice = selectedAttraction.getPrice() * numberOfTickets;
                                if (balance >= ticketPrice) {
                                    balance -= ticketPrice;
                                    incrementTicketsForAttraction(selectedAttraction, numberOfTickets);
                                    System.out.println("Tickets rebought successfully. Your balance is now ₹" + balance);
                                } else {
                                    System.out.println("Insufficient balance to rebuy tickets.");
                                }
                            } else {
                                System.out.println("You cannot visit " + selectedAttraction.getName() +" without valid tickets.");
                            }
                        }
                    } else if (membership.getName().equals("Premium Membership")) {
                        // Premium Membership - unlimited visits to attractions
                        decrementVisitsForAttraction(selectedAttraction);
                        System.out.println("Welcome to " + selectedAttraction.getName());
                        System.out.println("Thank you for visiting " + selectedAttraction.getName() + ". Hope you enjoyed the attraction.");
                    }
                } else {
                    System.out.println("You must have a membership to visit attractions.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    
    private boolean hasValidTicketForAttraction(Attraction attraction) {
        for (Ticket ticket : purchasedTickets) {
            if (ticket.getAttraction() == attraction && !ticket.isUsed()) {
                return true;
            }
        }
        return false;
    }

    private boolean canVisitAttraction(Attraction attraction) {
        for (Ticket ticket : purchasedTickets) {
            if (ticket.getAttraction() == attraction) {
                return ticket.getVisitsRemaining() > 0;
            }
        }
        return false;
    }

    private void decrementVisitsForAttraction(Attraction attraction) {
        for (Ticket ticket : purchasedTickets) {
            if (ticket.getAttraction() == attraction) {
                ticket.decrementVisits();
            }
        }
    }


    public void leaveFeedback(List<Feedback> feedbackList) {
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Leave Feedback (max 200 characters): ");
        String feedbackText = sc.nextLine();
    
        if (feedbackText.length() <= 200) {
            Feedback feedback = new Feedback(getName(), feedbackText);
            feedbackList.add(feedback);
            System.out.println("Thank you for your feedback.");
        } else {
            System.out.println("Feedback is too long. Please keep it within 200 characters.");
        }
    }
    
    // public void addFunds(double amount) {
    //     balance += amount;
    // }
    // public boolean deductBalance(double amount) {
    //     if (balance >= amount) {
    //         balance -= amount;
    //         return true;
    //     }
    //     return false;
    // }

    public double calculateTotalTicketPrice(List<Attraction> selectedAttractions, List<SpecialDeal> specialDeals) {
        double total = 0.0; 
        for (Attraction attraction : selectedAttractions) {
            total += attraction.getPrice();
        }
        //check for special deals
        for (SpecialDeal deal : specialDeals) {
            for (Map.Entry<Integer, Double> condition : deal.getConditions().entrySet()) {
                int minAttractions = condition.getKey();
                double discountPercentage = condition.getValue();
                if (selectedAttractions.size() >= minAttractions) { 
                    total -= (total * discountPercentage / 100.0);
                }
            }
        }

        return total;
    }
    public void logOut() {
        System.out.println("Logged out.");
    }
}
