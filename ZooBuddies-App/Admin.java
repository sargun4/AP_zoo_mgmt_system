import java.util.*; 
class Admin implements  User {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    private List<Attraction> attractions;
    private List<Animal> animals ;
    private List<Discount> discounts;
    private List<Visitor> visitors;
    private List<SpecialDeal> specialDeals;
    private List<Feedback> feedbackList;


    public Admin(String name, int age, String phone, String email, String password) {
        super();
        this.animals = new ArrayList<>();
        this.visitors = new ArrayList<>();
        this.attractions = new ArrayList<>(); 
        this.discounts = new ArrayList<>();
        this.specialDeals = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
    }
    @Override
    public String getName() {
        return ADMIN_USERNAME;
    }
    @Override
    public int getAge() {
        return 30;
    }
    public boolean login(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }


    public void createVisitor(String name, int age, int phone, String email, String password, double initialBalance, List<Discount> additionalDiscounts) {
        Visitor visitor = new Visitor(name, age, phone, email, password, initialBalance, additionalDiscounts);
     }

    public void manageAttractions(List<Attraction> attractions) {
        Scanner sc = new Scanner(System.in);
        boolean exitMenu = false;
            while (!exitMenu) {
            System.out.println("Attraction Management Menu:");
            System.out.println("1. Add Attraction");
            System.out.println("2. View Attractions");  
            System.out.println("3. Modify Attraction");
            System.out.println("4. Remove Attraction");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Attraction Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Attraction Description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter Attraction Price: ");
                    double price = sc.nextDouble();

                    int id = attractions.size() + 1;
                    Attraction newAttraction = new Attraction(id, name, description, price);

                    attractions.add(newAttraction);

                    System.out.println("Attraction added successfully.");
                    break;
                case 2:
                    // View Attractions
                    System.out.println("List of Attractions:");
                    for (Attraction attraction : attractions) {
                        System.out.println(attraction.getId() + ". " + attraction.getName()+", Description: " + attraction.getDescription()+", Price: ₹" +attraction.getPrice());
                    }
                    break;
                case 3:
                    System.out.print("Enter the ID of the attraction to modify: ");
                    int modifyId = sc.nextInt();
                    sc.nextLine();
                
                    Attraction modifyAttraction = findAttractionById(attractions, modifyId);
                
                    if (modifyAttraction != null) {
                        System.out.print("Enter new Attraction Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new Attraction Description: ");
                        String newDescription = sc.nextLine();
                        System.out.print("Enter new Attraction Price: ");
                        double newPrice = sc.nextDouble();
                
                        // Validate inputs
                        if (newName.isEmpty() || newDescription.isEmpty() || newPrice < 0) {
                            System.out.println("Invalid input. Please provide valid values.");
                        } else {
                            modifyAttraction.setName(newName);
                            modifyAttraction.setDescription(newDescription);
                            modifyAttraction.setPrice(newPrice);
                
                            System.out.println("Attraction modified successfully.");
                        }
                    } else {
                        System.out.println("Attraction not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the ID of the attraction to remove: ");
                    int removeId = sc.nextInt();
                    Attraction removeAttraction = findAttractionById(attractions, removeId);

                    if (removeAttraction != null) {
                        attractions.remove(removeAttraction);
                        System.out.println("Attraction removed successfully.");
                    } else {
                        System.out.println("Attraction not found.");
                    }
                    break;
                case 5: // Exit
                exitMenu = true;
                break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                }
            }
    }

    private Attraction findAttractionById(List<Attraction> attractions, int id) {
        return attractions.stream()
                .filter(attraction -> attraction.getId() == id)
                .findFirst()
                .orElse(null);
    }
    private int generateUniqueAnimalId(List<Animal> animals) {
        int animalId = 6;  //since i hv already given ids 1 to 6 to the specfifc animals  
        for (Animal animal : animals) {
            if (animal.getId() > animalId ) {
                animalId  = animal.getId();
            }
        }
        return animalId  + 1;
    }
    private static void addSpecificAnimals(List<Animal> animals) {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("Choose animals to add to the zoo:");
        System.out.println("1. Mammal: Elephant");
        System.out.println("2. Mammal: Monkey");
        System.out.println("3. Amphibian: Frog");
        System.out.println("4. Amphibian: Salamander");
        System.out.println("5. Reptile: Snake");
        System.out.println("6. Reptile: Turtle");
        System.out.println("7. Exit");

        while (true) {
            System.out.print("Enter your choice (press 7 to exit): ");
            int choice = sc.nextInt();
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    animals.add(new Mammal(1, "Elephant"));
                    break;
                case 2:
                    animals.add(new Mammal(2, "Monkey"));
                    break;
                case 3:
                    animals.add(new Amphibian(3, "Frog"));
                    break;
                case 4:
                    animals.add(new Amphibian(4, "Salamander"));
                    break;
                case 5:
                    animals.add(new Reptile(5, "Snake"));
                    break;
                case 6:
                    animals.add(new Reptile(6, "Turtle"));
                    break;
                case 7:
                    System.out.println("Done adding animals.");
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    
    public void manageAnimals(List<Animal> animals, List<Attraction> attractions) {
        Scanner sc = new Scanner(System.in);
        boolean exitMenu = false;

        boolean specificAnimalsAdded = false; // to track whether specific animals have been added
        while (!exitMenu) {
                System.out.println("Animal Management Menu:");
                System.out.println("0. Add Animals from List-");
                System.out.println("1. Add Other Animal");
                System.out.println("2. Update Animal Details");
                System.out.println("3. Remove Animal");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
    
                switch (choice) {
                    case 0:
                    if (!specificAnimalsAdded) {
                        addSpecificAnimals(animals);
                        specificAnimalsAdded = true;
                    } else {
                        System.out.println("Specific animals have already been added.");
                    }
                    break;
                    case 1:
                        // Add Animal
                        System.out.print("Enter Animal Name: ");
                        String animalName = sc.nextLine();
                        System.out.print("Enter Animal Type (Mammal, Amphibian, or Reptile): ");
                        String animalType = sc.nextLine();
                    
                        int animalId = generateUniqueAnimalId(animals);
                    
                        
                        Animal animal = null;
                    
                        switch (animalType.toLowerCase()) {
                            case "mammal":
                                animal = new Mammal(animalId, animalName);
                                break;
                            case "amphibian":
                                animal = new Amphibian(animalId, animalName);
                                break;
                            case "reptile":
                                animal = new Reptile(animalId, animalName);
                                break;
                            default:
                                System.out.println("Invalid animal type. Please use Mammal, Amphibian, or Reptile.");
                                break;
                        }
                    
                        if (animal != null) {
                            animals.add(animal);
                            System.out.println("Animal added successfully.");
                        }
                        break;
                    
                    case 2:
                        System.out.println("List of Animals:");
                        for (Animal a : animals) {
                            System.out.println("ID: " + a.getId() + ", Name: " + a.getName());
                        }
                        System.out.print("Enter the ID of the animal to update: ");
                        int updateAnimalId = sc.nextInt();
                        sc.nextLine();
                    
                        Animal updateAnimal = findAnimalById(animals, updateAnimalId);
                    
                        if (updateAnimal != null) {
                            System.out.print("Enter new Animal Name: ");
                            String newName = sc.nextLine();
                    
                            System.out.print("Enter new Animal Type (Mammal, Amphibian, or Reptile): ");
                            String newType = sc.nextLine();
                    
                            // System.out.print("Enter the ID of the attraction to update the animal: ");
                            // int newAttractionId = sc.nextInt();
                    
                            if (validateAnimalCategory(newType)) {
                                updateAnimal.setName(newName);
                                updateAnimal.setType(newType);
                            } else {
                                System.out.println("Invalid animal type. Please use Mammal, Amphibian, or Reptile.");
                            }
                        } else {
                            System.out.println("Animal not found.");
                        }
                        break;
                    
                    case 3:
                        System.out.print("Enter the ID of the animal to remove: ");
                        int removeAnimalId = sc.nextInt();
                        Animal removeAnimal = findAnimalById(animals, removeAnimalId);
    
                        if (removeAnimal != null) {
                            animals.remove(removeAnimal);
                            System.out.println("Animal removed successfully.");
                        } else {
                            System.out.println("Animal not found.");
                        }
                        break;

                    case 4:
                        exitMenu = true;
                        break;
    
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
    }
    
    
    private boolean validateAnimalCategory(String type) {
        return type.equalsIgnoreCase("Mammal") || type.equalsIgnoreCase("Amphibian") || type.equalsIgnoreCase("Reptile");
    }
    
    private Animal findAnimalById(List<Animal> animals, int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null; 
    }

    public void scheduleEvents(List<Attraction> attractions) {
         
        Scanner sc = new Scanner(System.in);
        boolean exitMenu = false;
            while (!exitMenu) {

            System.out.println("Event Scheduling Menu:");
            System.out.println("1. Schedule Event");
            System.out.println("2. Modify Event");
            System.out.println("3. Remove Event");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the ID of the attraction to schedule an event: ");
                    int eventAttractionId = sc.nextInt();
                    Attraction eventAttraction = findAttractionById(attractions, eventAttractionId);

                    if (eventAttraction != null) {
                        System.out.print("Enter the event price: ");
                        double eventPrice = sc.nextDouble();
                        eventAttraction.setPrice(eventPrice);
                        System.out.println("Event scheduled successfully.");
                    } else {
                        System.out.println("Attraction not found.");
                    }
                    break;
                case 2:
                    System.out.println("List of Events:");
                        for (Attraction a : attractions) {
                            System.out.println("ID: " + a.getId() + ", Name: " + a.getName());
                        }
                    System.out.print("Enter the ID of the attraction to modify the event: ");
                    int modifyEventId = sc.nextInt();
                    Attraction modifyEventAttraction = findAttractionById(attractions, modifyEventId);

                    if (modifyEventAttraction != null) {
                        System.out.print("Enter new event price: ");
                        double newEventPrice = sc.nextDouble();
                        modifyEventAttraction.setPrice(newEventPrice);
                        System.out.println("Event modified successfully.");
                    } else {
                        System.out.println("Event attraction not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the ID of the attraction to remove the event: ");
                    int removeEventId = sc.nextInt();
                    Attraction removeEventAttraction = findAttractionById(attractions, removeEventId);

                    if (removeEventAttraction != null) {
                        removeEventAttraction.setPrice(0.0);  
                        System.out.println("Event removed successfully.");
                    } else {
                        System.out.println("Event attraction not found.");
                    }
                    break;
                case 4:
                    exitMenu=true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    
    private List<Discount> additionalDiscounts = new ArrayList<>();
    public void addAdditionalDiscount(Discount additionalDiscount) {
        additionalDiscounts.add(additionalDiscount);
    }
    
    public void setDiscounts(List<Discount> discounts) {
        int idCounter = 1;
        Scanner sc = new Scanner(System.in);
        boolean exitMenu = false;

        while (!exitMenu) {
                System.out.println("Set Discounts:");
                System.out.println("1. Add Discount");
                System.out.println("2. Modify Discount");
                System.out.println("3. Remove Discount");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
    
                switch (choice) {
                    case 1:
                    System.out.print("Enter Discount Category (e.g., Students): ");
                    String category = sc.nextLine();
                    System.out.print("Enter Discount Description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter Discount Percentage (e.g., 20 for 20%): ");
                    double percentage = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Discount Code: ");
                    String code = sc.nextLine();
                    
                    Discount newDiscount = new Discount(idCounter++, description, percentage, code, category);
                    discounts.add(newDiscount);

                    addAdditionalDiscount(newDiscount);

                    
                    System.out.println("Discount added successfully.");
                    break;
                
                    case 2:
                        System.out.print("Enter the ID of the discount to modify: ");
                        int modifyDiscountId = sc.nextInt();
    
                        Discount modifyDiscount = findDiscountById(discounts, modifyDiscountId);
                        if (modifyDiscount != null) {
                            System.out.print("Enter new Discount Percentage (e.g., 20 for 20%): ");
                            double newPercentage = sc.nextDouble();
                            modifyDiscount.setPercentage(newPercentage);
                            System.out.println("Discount modified successfully.");
                        } else {
                            System.out.println("Discount not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the ID of the discount to remove: ");
                        int removeDiscountId = sc.nextInt();
    
                        Discount removeDiscount = findDiscountById(discounts, removeDiscountId);
                        if (removeDiscount != null) {
                            discounts.remove(removeDiscount);
                            System.out.println("Discount removed successfully.");
                        } else {
                            System.out.println("Discount not found.");
                        }
                        break;
                    case 4:
                        exitMenu=true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
    }
     
    private Discount findDiscountById(List<Discount> discounts, int id) {
        return discounts.stream()
                .filter(discount -> discount.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void setSpecialDeals(List<SpecialDeal> specialDeals) {
        Scanner sc = new Scanner(System.in);

        SpecialDeal specialDeal = new SpecialDeal(0, "Special Dealz", 0, 0);
        specialDeal.addCondition(2, 15.0); // If a person buys 2 attractions, they get a 15% discount
        specialDeal.addCondition(3, 30.0); // If a person buys 3 or more attractions, they get a 30% discount

        specialDeals.add(specialDeal);
        System.out.println("Default special deal added successfully.");
        boolean exitMenu = false;
        while (!exitMenu) {
                System.out.println("Special Deal Management Menu:");
                System.out.println("1. Add Special Deal");
                System.out.println("2. Remove Special Deal");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
    
                switch (choice) {
                    case 1:
                    System.out.print("Enter Special Deal Name (e.g., Buy 2 Get 15% Off): ");
                    String name = sc.nextLine();
                    System.out.print("Enter the minimum number of attractions for the deal: ");
                    int minAttractions = sc.nextInt();
                    System.out.print("Enter the discount percentage (e.g., 15 for 15%): ");
                    double discountPercentage = sc.nextDouble();

                    int id = specialDeals.size() + 1;
                    SpecialDeal newSpecialDeal = new SpecialDeal(id, name, minAttractions, discountPercentage);
                    specialDeals.add(newSpecialDeal);
                    System.out.println("Special Deal added successfully.");
                    break;

                    case 2:
                        if (specialDeals.isEmpty()) {
                            System.out.println("No special deals available to remove.");
                        } else {
                            System.out.print("Enter the ID of the special deal to remove: ");
                            int removeSpecialDealId = sc.nextInt();
                            SpecialDeal removeSpecialDeal = findSpecialDealById(specialDeals, removeSpecialDealId);
    
                            if (removeSpecialDeal != null) {
                                specialDeals.remove(removeSpecialDeal);
                                System.out.println("Special Deal removed successfully.");
                            } else {
                                System.out.println("Special Deal not found.");
                            }
                        }
                        break;
                    case 3:
                        exitMenu=true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
    }
    
    private SpecialDeal findSpecialDealById(List<SpecialDeal> specialDeals, int id) {
        return specialDeals.stream()
                .filter(specialDeal -> specialDeal.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public void viewVisitorStats(List<Visitor> visitors, List<Attraction> attractions) {
        if (visitors.isEmpty()) {
            System.out.println("No visitors yet. Visitor statistics are not available.");
        } else {
            int totalVisitors = visitors.size();
            double totalRevenue = calculateTotalRevenue(visitors, attractions,discounts);
            Attraction popularAttraction = findMostPopularAttraction(attractions);
    
            System.out.println("Visitor Statistics:");
            System.out.println("Total Visitors: " + totalVisitors);
            System.out.println("Total Revenue: ₹" + totalRevenue);
            System.out.println("Most Popular Attraction: " + popularAttraction.getName());
        }
    }
    

    //Revenue is the sum of tickets and memberships.
    public double calculateTotalRevenue(List<Visitor> visitors, List<Attraction> attractions, List<Discount> discounts) {
        double totalRevenue = 0.0;
        for (Visitor visitor : visitors) {
            double visitorRevenue = calculateVisitorRevenue(visitor, attractions, discounts);
            if (visitor.getMembership() != null && visitor.getMembership().getName().equals("Premium Membership")) {
                // Premium members don't need discount coupons
                totalRevenue += visitorRevenue;
            } else {
                // incl discount amount in revenue
                double discountAmount = calculateDiscountAmount(visitor, visitorRevenue, discounts);
                totalRevenue += visitorRevenue - discountAmount;
            }
        }
        return totalRevenue;
    }
    private double calculateVisitorRevenue(Visitor visitor, List<Attraction> attractions, List<Discount> discounts) {
        double totalRevenue = 0.0;
        for (Ticket ticket : visitor.getPurchasedTickets()) {
            Attraction attraction = ticket.getAttraction();
            totalRevenue += attraction.getPrice();
        }
        return totalRevenue;
    }
    private double calculateDiscountAmount(Visitor visitor, double totalRevenue, List<Discount> discounts) {
        double discountAmount = 0.0;
        for (Discount discount : discounts) {
            // Check if the discount is applicable to the visitor
            if (isDiscountApplicable(discount, visitor)) {
                // Calculate the discount amount based on the discount type
                if (discount.getCategory().equals("PERCENTAGE")) {
                    discountAmount += (discount.getPercentage() / 100.0) * totalRevenue;
                } else if (discount.getCategory().equals("AMOUNT")) {
                    discountAmount += discount.getPercentage(); // Assuming getPercentage() returns the discount amount.
                }
            }
        }
        return discountAmount;
    }
    public boolean isDiscountApplicable(Discount discount, Visitor visitor) {
        int visitorAge = visitor.getAge();
        if (discount.getCategory().equalsIgnoreCase("Minor") && visitorAge < 18) {
            return true;
        } else if (discount.getCategory().equalsIgnoreCase("Senior Citizen") && visitorAge > 60) {
            return true;
        }
        return false;
    }
    
    
    private Attraction findMostPopularAttraction(List<Attraction> attractions) {
        return attractions.stream()
                .max(Comparator.comparingInt(Attraction::getVisitorCount))
                .orElse(null);
    }
    
    public void viewFeedback(List<Feedback> feedbackList) {
        System.out.println("Feedback from Visitors:");
        feedbackList.forEach(feedback -> {
            System.out.println("Visitor: " + feedback.getVisitorName());
            System.out.println("Feedback: " + feedback.getFeedbackText());
            System.out.println("-------------------------");
        });
    }
}