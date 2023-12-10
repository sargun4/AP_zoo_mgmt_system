import java.util.*;

public class ZooBuddiesApp {
    private static boolean exitRequested = false;
     
    public static int handleVisitorLoginRegistration(Scanner sc, List<Visitor> visitors) {
        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice;
    
            try {
                choice = sc.nextInt();
                sc.nextLine(); 
    
                if (choice >= 1 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                sc.nextLine(); 
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Visitor> visitors = new ArrayList<>();
        List<Attraction> attractions = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        List<Discount> discounts = new ArrayList<>();
        List<SpecialDeal> specialDeals = new ArrayList<>();
        List<Feedback> feedbackList = new ArrayList<>();
        List<Ticket> tickets = new ArrayList<>();
        List<Discount> availableDiscounts=new ArrayList<>();
        Admin admin = new Admin("Admin", 0, "admin", "admin", "admin123");

        while (!exitRequested) {
            System.out.println("Main Menu");
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as a Visitor");
            System.out.println("3. View Special Deals");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice: ");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine(); 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Username: ");
                    String adminUsername = sc.nextLine();
                    System.out.print("Enter Admin Password: ");
                    String adminPassword = sc.nextLine();

                    if (admin.login(adminUsername, adminPassword)) {
                        System.out.println("\nLogged in as Admin.");
                        adminMenu(admin, attractions, animals, discounts, specialDeals, feedbackList, visitors);
                        
                    } else {
                        System.out.println("Invalid admin credentials.");
                    }
                    break;
                case 2:
                    int visitorChoice = handleVisitorLoginRegistration(sc, visitors);
                    if (visitorChoice == 1) {
                        
                        Visitor visitor = Visitor.register();
                        if (visitor != null) {
                            System.out.println("\nRegistration is successful.");
                            visitors.add(visitor);
                        } else {
                            System.out.println("Visitor registration failed.");
                        }
                    } else if (visitorChoice == 2) {
                        
                        System.out.print("Enter Visitor Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Visitor Password: ");
                        String password = sc.nextLine();
                        Visitor visitor = Visitor.login(email, password, visitors);
                        if (visitor != null) {
                            System.out.println("Login Successful.");
                            visitorMenu(visitor, attractions, animals, discounts, specialDeals, feedbackList,tickets,availableDiscounts);
                        } else {
                            System.out.println("Login Failed. Invalid email or password.");
                        }
                    }else if(visitorChoice == 3)  {
                        System.out.println("Going back to Main menu");
                    } 
                    else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case 3:
                    viewSpecialDeals(specialDeals);
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    exitRequested = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        sc.close();
    }


    static boolean adminMenu(Admin admin, List<Attraction> attractions, List<Animal> animals, List<Discount> discounts, List<SpecialDeal> specialDeals, List<Feedback> feedbackList, List<Visitor> visitors) {
        Scanner sc = new Scanner(System.in);
        boolean exitAdminMenu = false;
    
        while (!exitAdminMenu) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Manage Attractions");
            System.out.println("2. Manage Animals");
            System.out.println("3. Schedule Events");
            System.out.println("4. Set Discounts");
            System.out.println("5. Set Special Deal");
            System.out.println("6. View Visitor Stats");
            System.out.println("7. View Feedback");
            System.out.println("8. Exit");
            System.out.print("\nEnter your choice:");
    
            int choice;
    
            try {
                choice = sc.nextInt();
                sc.nextLine(); 
    
                switch (choice) {
                    case 1:
                        admin.manageAttractions(attractions);
                        break;
                    case 2:
                        admin.manageAnimals(animals, attractions);
                        break;
                    case 3:
                        admin.scheduleEvents(attractions);
                        break;
                    case 4:
                        admin.setDiscounts(discounts);
                        break;
                    case 5:
                        admin.setSpecialDeals(specialDeals);
                        break;
                    case 6:
                        admin.viewVisitorStats(visitors, attractions);
                        break;
                    case 7:
                        admin.viewFeedback(feedbackList);
                        break;
                    case 8:
                        System.out.print("Return to Main Menu? (yes/no): ");
                        String returnToMainMenu = sc.nextLine().trim().toLowerCase();
                        if (returnToMainMenu.equals("no")) {
                            exitRequested = true;
                        } else if (returnToMainMenu.equals("yes")) {
                            exitAdminMenu = true; 
                        } else {
                            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); 
            }
        }
    
        return exitAdminMenu;
    }
    

    private static boolean visitorMenu(Visitor visitor, List<Attraction> attractions, List<Animal> animals, List<Discount> discounts, List<SpecialDeal> specialDeals, List<Feedback> feedbackList,List<Ticket> tickets,List<Discount> availableDiscounts) {
        Scanner sc = new Scanner(System.in);
        boolean exitVisitorMenu = false;
        

            List<Membership> memberships = new ArrayList<>();
            
            Membership membership1 = new Membership("Premium Membership", 100.0);
            memberships.add(membership1);

            Membership membership2 = new Membership("Basic Membership", 50.0);
            memberships.add(membership2);

            while (!exitVisitorMenu) {
                System.out.println("\nVisitor Menu:");
                System.out.println("1. Explore the Zoo");
                System.out.println("2. Buy Membership");
                System.out.println("3. Buy Tickets");
                System.out.println("4. View Discounts");
                System.out.println("5. View Special Deals");
                System.out.println("6. Visit Animals");
                System.out.println("7. Visit Attractions");
                System.out.println("8. Leave Feedback");
                System.out.println("9. Log Out");
                System.out.print("\nEnter your choice: ");
        
                int choice;
        
                try {
                    choice = sc.nextInt();
                    sc.nextLine(); 
        
                    switch (choice) {
                        case 1:
                            visitor.exploreZoo(attractions,animals);
                            break;
                        case 2:
                            visitor.buyMembership(memberships);
                            break;
                        case 3:
                            visitor.buyTickets(attractions, availableDiscounts);
                            break;
                        case 4: 
                            visitor.viewDiscounts(visitor); 
                            break;
                        case 5:
                            visitor.viewSpecialDeals(specialDeals);
                            break;
                        case 6:
                            visitor.visitAnimals(animals);
                            break;
                        case 7:
                            visitor.visitAttractions(tickets, attractions);
                            break;
                        case 8:
                            visitor.leaveFeedback(feedbackList);
                            break;
                        case 9:
                            System.out.print("Return to Main Menu? (yes/no): ");
                            String returnToMainMenu = sc.nextLine().trim().toLowerCase();
                            if (returnToMainMenu.equals("no")) {
                                exitRequested = true;
                            } else if (returnToMainMenu.equals("yes")) {
                                exitVisitorMenu = true; 
                            } else {
                                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                            }
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); 
                }
            }
            return exitVisitorMenu;
        
        
        
    }

    private static void viewSpecialDeals(List<SpecialDeal> specialDeals) {
        System.out.println("\nSpecial Deals:");

        if (specialDeals.isEmpty()) {
            System.out.println("No special deals available at the moment.");
        } else {
            for (int i = 0; i < specialDeals.size(); i++) {
                SpecialDeal deal = specialDeals.get(i);
                System.out.println((i + 1) + ". " + deal.getDescription());
                System.out.println("Conditions:");

                Map<Integer, Double> conditions = deal.getConditions();
                for (Map.Entry<Integer, Double> entry : conditions.entrySet()) {
                    int numAttractions = entry.getKey();
                    double discountPercentage = entry.getValue();
                    System.out.println("- Buy " + numAttractions + " tickets and get " + discountPercentage + "% off");
                }
            }
        }
    }
}


