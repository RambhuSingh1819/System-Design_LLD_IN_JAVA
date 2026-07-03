package com.tomato;

import com.tomato.models.*;
import com.tomato.singleton.*;
import com.tomato.strategy.*;
import com.tomato.factory.*;
import com.tomato.order.*;
import com.tomato.service.*;
import com.tomato.test.LldTestRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    // ANSI color escape sequences
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";

    private static List<User> demoUsers;
    private static User currentUser;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupMockData();
        
        boolean running = true;
        while (running) {
            printHeader();
            printMenu();
            System.out.print("Select an option (1-9): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewProfileAndCart();
                    break;
                case "2":
                    browseRestaurants();
                    break;
                case "3":
                    clearCart();
                    break;
                case "4":
                    checkoutAndPlaceOrder();
                    break;
                case "5":
                    viewOrderHistory();
                    break;
                case "6":
                    LldTestRunner.runAllTests();
                    pressEnterToContinue();
                    break;
                case "7":
                    changeUserProfile();
                    break;
                case "8":
                    explainDesignPatterns();
                    break;
                case "9":
                    System.out.println(GREEN + "\nThank you for choosing Tomato! Goodbye." + RESET);
                    running = false;
                    break;
                default:
                    System.out.println(RED + "Invalid option. Please choose a number between 1 and 9." + RESET);
                    pressEnterToContinue();
            }
        }
    }

    private static void setupMockData() {
      
        // Initialize 5 Demo Users (Composition initializes their Carts automatically)
        demoUsers = Arrays.asList(
            new User(101, "Ramesh Singh", "Punjab Farm, Ludhiana"),
            new User(102, "Sunita Sharma", "Srinagar Colony, Delhi"),
            new User(103, "Amit Patel", "Bapu Nagar, Ahmedabad"),
            new User(104, "Priya Nair", "Jayanagar, Bangalore"),
            new User(105, "David D'Souza", "Panaji Road, Goa")
        );
        currentUser = demoUsers.get(0);

        RestaurantManager rm = RestaurantManager.getInstance();
        rm.clear();

        // Restaurant 1: Punjabi Rasoi
        Restaurant r1 = new Restaurant(1, "Punjabi Rasoi", "Sector 17, Chandigarh");
        r1.addMenuItem(new Menu("PR01", "Butter Paneer Masala", 8.99));
        r1.addMenuItem(new Menu("PR02", "Dal Makhani", 7.49));
        r1.addMenuItem(new Menu("PR03", "Garlic Naan (2 pcs)", 2.49));
        r1.addMenuItem(new Menu("PR04", "Lassi", 1.99));
        rm.addRestaurant(r1);

        // Restaurant 2: Organic Pizza Co.
        Restaurant r2 = new Restaurant(2, "Organic Pizza Co.", "Mall Road, Shimla");
        r2.addMenuItem(new Menu("OP01", "Farmhouse Special Pizza", 12.99));
        r2.addMenuItem(new Menu("OP02", "Garden Fresh Salad", 6.99));
        r2.addMenuItem(new Menu("OP03", "Garlic Breadsticks", 4.49));
        rm.addRestaurant(r2);

        // Restaurant 3: South Indian Express
        Restaurant r3 = new Restaurant(3, "South Indian Express", "Station Road, Amritsar");
        r3.addMenuItem(new Menu("SE01", "Masala Dosa", 5.99));
        r3.addMenuItem(new Menu("SE02", "Idli Sambhar (2 pcs)", 3.99));
        r3.addMenuItem(new Menu("SE03", "Filter Coffee", 1.49));
        rm.addRestaurant(r3);

        // Restaurant 4: Burger Nest
        Restaurant r4 = new Restaurant(4, "Burger Nest", "Connaught Place, Delhi");
        r4.addMenuItem(new Menu("BN01", "Double Cheese Burger", 6.49));
        r4.addMenuItem(new Menu("BN02", "Crispy French Fries", 2.99));
        r4.addMenuItem(new Menu("BN03", "Oreo Milkshake", 3.99));
        r4.addMenuItem(new Menu("BN04", "Spicy Chicken Wings", 5.49));
        rm.addRestaurant(r4);

        // Restaurant 5: Tandoori Nights
        Restaurant r5 = new Restaurant(5, "Tandoori Nights", "Gachibowli, Hyderabad");
        r5.addMenuItem(new Menu("TN01", "Hyderabadi Chicken Biryani", 10.99));
        r5.addMenuItem(new Menu("TN02", "Mutton Seekh Kebab", 11.99));
        r5.addMenuItem(new Menu("TN03", "Rumali Roti", 1.49));
        r5.addMenuItem(new Menu("TN04", "Mixed Veg Raita", 0.99));
        rm.addRestaurant(r5);

        // Restaurant 6: Wok & Noodle
        Restaurant r6 = new Restaurant(6, "Wok & Noodle", "Indiranagar, Bangalore");
        r6.addMenuItem(new Menu("WN01", "Veg Hakka Noodles", 7.99));
        r6.addMenuItem(new Menu("WN02", "Kung Pao Chicken", 9.49));
        r6.addMenuItem(new Menu("WN03", "Crispy Spring Rolls", 4.49));
        r6.addMenuItem(new Menu("WN04", "Steamed Chicken Dim Sums", 5.99));
        rm.addRestaurant(r6);

        // Restaurant 7: Sweet Treats Bakery
        Restaurant r7 = new Restaurant(7, "Sweet Treats Bakery", "Park Street, Kolkata");
        r7.addMenuItem(new Menu("ST01", "Chocolate Fudge Cake", 4.99));
        r7.addMenuItem(new Menu("ST02", "Blueberry Muffin", 2.99));
        r7.addMenuItem(new Menu("ST03", "Butter Croissant", 3.49));
        r7.addMenuItem(new Menu("ST04", "Rich Hot Chocolate", 3.99));
        rm.addRestaurant(r7);

        // Restaurant 8: Taco Fiesta
        Restaurant r8 = new Restaurant(8, "Taco Fiesta", "Bandra West, Mumbai");
        r8.addMenuItem(new Menu("TF01", "Spicy Chicken Taco", 3.49));
        r8.addMenuItem(new Menu("TF02", "Cheesy Veg Quesadilla", 6.99));
        r8.addMenuItem(new Menu("TF03", "Loaded Nachos with Salsa", 4.99));
        r8.addMenuItem(new Menu("TF04", "Cinnamon Churros (4 pcs)", 3.99));
        rm.addRestaurant(r8);

        // Restaurant 9: The Salad Bar
        Restaurant r9 = new Restaurant(9, "The Salad Bar", "Koregaon Park, Pune");
        r9.addMenuItem(new Menu("SB01", "Classic Caesar Salad", 8.49));
        r9.addMenuItem(new Menu("SB02", "Avocado Toast", 7.99));
        r9.addMenuItem(new Menu("SB03", "Detox Green Smoothie", 4.99));
        r9.addMenuItem(new Menu("SB04", "Mediterranean Quinoa Bowl", 9.49));
        rm.addRestaurant(r9);

        // Restaurant 10: Royal Durbar
        Restaurant r10 = new Restaurant(10, "Royal Durbar", "Hazratganj, Lucknow");
        r10.addMenuItem(new Menu("RD01", "Melt-in-mouth Galouti Kebab", 9.99));
        r10.addMenuItem(new Menu("RD02", "Awadhi Chicken Biryani", 11.49));
        r10.addMenuItem(new Menu("RD03", "Sweet Shahi Tukda", 3.99));
        r10.addMenuItem(new Menu("RD04", "Baked Sheermal", 2.49));
        rm.addRestaurant(r10);
    }

    private static void printHeader() {
        System.out.println(RED + BOLD + "=================================================="); 
        System.out.println("==================================================" + RESET);
        System.out.printf("Current User: %s%s (%s)%s%n", 
            CYAN, currentUser.getName(), currentUser.getAddress(), RESET);
        System.out.println("--------------------------------------------------");
    }

    private static void printMenu() {
        System.out.println("1. " + BOLD + "View Profile & Cart" + RESET);
        System.out.println("2. " + BOLD + "Browse Restaurants & Add to Cart" + RESET);
        System.out.println("3. " + BOLD + "Clear Active Cart" + RESET);
        System.out.println("4. " + GREEN + BOLD + "Checkout & Place Order (Factory + Strategy)" + RESET);
        System.out.println("5. " + BOLD + "View Order History (Singleton)" + RESET);
        System.out.println("6. " + YELLOW + BOLD + "Run System Diagnostics (SOLID Unit Tests)" + RESET);
        System.out.println("7. " + BOLD + "Change User Profile" + RESET);
        System.out.println("8. " + CYAN + BOLD + "Explain Applied Design Patterns & SOLID" + RESET);
        System.out.println("9. " + RED + "Exit" + RESET);
        System.out.println("--------------------------------------------------");
    }

    private static void viewProfileAndCart() {
        System.out.println(YELLOW + "\n--- Customer Profile ---" + RESET);
        System.out.println("ID: " + currentUser.getUid());
        System.out.println("Name: " + currentUser.getName());
        System.out.println("Address: " + currentUser.getAddress());

        System.out.println(YELLOW + "\n--- Active Shopping Cart ---" + RESET);
        Cart cart = currentUser.getCart();
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Browse restaurants to add food!");
        } else {
            System.out.printf("Restaurant: %s (%s)%n", 
                cart.getRestaurant().getName(), cart.getRestaurant().getLocation());
            System.out.println("Items:");
            for (Menu item : cart.getItems()) {
                System.out.printf("  - %s ($%.2f)%n", item.getName(), item.getPrice());
            }
            System.out.printf("Total Cart Value: %s$%.2f%s%n", GREEN + BOLD, cart.countTotal(), RESET);
        }
        System.out.println();
        pressEnterToContinue();
    }

    private static void browseRestaurants() {
        System.out.println(YELLOW + "\n--- Registered Restaurants ---" + RESET);
        List<Restaurant> restaurants = RestaurantManager.getInstance().getRestaurants();
        for (Restaurant r : restaurants) {
            System.out.printf("%d. %s [%s]%n", r.getRestId(), r.getName(), r.getLocation());
        }

        System.out.print("\nEnter Restaurant ID to view menu (or press Enter to return): ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return;

        try {
            int id = Integer.parseInt(input);
            Restaurant selectedRest = null;
            for (Restaurant r : restaurants) {
                if (r.getRestId() == id) {
                    selectedRest = r;
                    break;
                }
            }

            if (selectedRest == null) {
                System.out.println(RED + "Restaurant not found." + RESET);
                pressEnterToContinue();
                return;
            }

            viewRestaurantMenu(selectedRest);

        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid ID format." + RESET);
            pressEnterToContinue();
        }
    }

    private static void viewRestaurantMenu(Restaurant r) {
        System.out.printf(YELLOW + "\n--- Menu for %s ---%n" + RESET, r.getName());
        List<Menu> menuList = r.getMenu();
        for (int i = 0; i < menuList.size(); i++) {
            Menu item = menuList.get(i);
            System.out.printf("  [%d] Code: %s | %s - $%.2f%n", i + 1, item.getCode(), item.getName(), item.getPrice());
        }

        System.out.print("\nEnter the number (e.g., 1) to add item to your Cart (or press Enter to return): ");
        String choice = scanner.nextLine().trim();
        if (choice.isEmpty()) return;

        try {
            int index = Integer.parseInt(choice) - 1;
            if (index < 0 || index >= menuList.size()) {
                System.out.println(RED + "Invalid choice." + RESET);
            } else {
                Menu selectedItem = menuList.get(index);
                try {
                    currentUser.getCart().addItem(selectedItem, r);
                    System.out.printf(GREEN + "Successfully added %s to your cart!%n" + RESET, selectedItem.getName());
                } catch (IllegalStateException e) {
                    System.out.println(RED + "Error: " + e.getMessage() + RESET);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid index input." + RESET);
        }
        pressEnterToContinue();
    }

    private static void clearCart() {
        currentUser.getCart().clear();
        System.out.println(GREEN + "Your active cart has been cleared." + RESET);
        pressEnterToContinue();
    }

    private static void checkoutAndPlaceOrder() {
        Cart cart = currentUser.getCart();
        if (cart.isEmpty()) {
            System.out.println(RED + "\nCannot checkout: your cart is empty." + RESET);
            pressEnterToContinue();
            return;
        }

        System.out.println(YELLOW + "\n--- Checkout Simulator ---" + RESET);
        System.out.printf("Merchant: %s%n", cart.getRestaurant().getName());
        System.out.printf("Items: %d%n", cart.getItems().size());
        System.out.printf("Order Subtotal: $%.2f%n", cart.countTotal());
        System.out.println("-------------------------");

        // Step 1: Select Order Creator Factory (Now vs Scheduled)
        System.out.println("1. Order NOW (Immediate fulfillment)");
        System.out.println("2. SCHEDULE Order (Future fulfillment)");
        System.out.print("Choose order scheduling (1-2): ");
        String scheduleChoice = scanner.nextLine().trim();

        IOrderFactory factory;
        if (scheduleChoice.equals("2")) {
            System.out.print("Enter delivery/pickup time (e.g., '19:30', 'Tomorrow 13:00'): ");
            String time = scanner.nextLine().trim();
            factory = new ScheduleOrder(time.isEmpty() ? "Scheduled Later" : time);
            System.out.println(BLUE + "[LLD Trace] Configured factory: ScheduleOrder" + RESET);
        } else {
            factory = new NowOrder();
            System.out.println(BLUE + "[LLD Trace] Configured factory: NowOrder" + RESET);
        }

        // Step 2: Select Delivery Type (Delivery vs Pickup)
        System.out.println("\n1. Delivery Order (Deliver to customer address)");
        System.out.println("2. Self-Pickup Order (Customer picks up at restaurant)");
        System.out.print("Choose order type (1-2): ");
        String typeChoice = scanner.nextLine().trim();
        String orderType = typeChoice.equals("2") ? "pickup" : "delivery";
        System.out.printf(BLUE + "[LLD Trace] Order inheritance target: %sOrder%n" + RESET, 
            orderType.substring(0, 1).toUpperCase() + orderType.substring(1));

        // Step 3: Select Payment Strategy
        System.out.println("\nSelect Payment Strategy:");
        System.out.println("1. UPI");
        System.out.println("2. Credit Card");
        System.out.println("3. Net Banking");
        System.out.print("Choose payment option (1-3): ");
        String paymentChoice = scanner.nextLine().trim();

        IPaymentStrategy paymentStrategy;
        switch (paymentChoice) {
            case "2":
                System.out.print("Enter Credit Card Number (16 digits): ");
                String cc = scanner.nextLine().trim();
                System.out.print("Enter CVV (3 digits): ");
                String cvv = scanner.nextLine().trim();
                paymentStrategy = new CreditCardPayment(cc.isEmpty() ? "4111222233334444" : cc, cvv.isEmpty() ? "123" : cvv);
                System.out.println(BLUE + "[LLD Trace] Initialized Strategy: CreditCardPayment" + RESET);
                break;
            case "3":
                System.out.print("Enter Bank Name: ");
                String bank = scanner.nextLine().trim();
                paymentStrategy = new NetBankingPayment(bank.isEmpty() ? "State Bank of India" : bank);
                System.out.println(BLUE + "[LLD Trace] Initialized Strategy: NetBankingPayment" + RESET);
                break;
            case "1":
            default:
                System.out.print("Enter UPI ID (e.g. user@okhdfc): ");
                String upi = scanner.nextLine().trim();
                paymentStrategy = new UpiPayment(upi.isEmpty() ? "ramesh@ybl" : upi);
                System.out.println(BLUE + "[LLD Trace] Initialized Strategy: UpiPayment" + RESET);
        }

        System.out.println(YELLOW + "\nProcessing Checkout..." + RESET);

        // Execute LLD Flow
        // 1. Factory builds the order object polymorphically
        Order order = factory.createOrder(orderType, currentUser, cart.getRestaurant(), cart.getItems(), paymentStrategy);
        System.out.printf(BLUE + "[LLD Trace] Polymorphic Order Instantiation success: %s (ID: #%d)%n" + RESET, 
            order.getClass().getSimpleName(), order.getId());

        // 2. Delegate payment execution to PaymentManager Singleton (SRP compliance)
        System.out.println(BLUE + "[LLD Trace] Delegating payment execution to PaymentManager Singleton..." + RESET);
        boolean paid = PaymentManager.getInstance().processPayment(order.getTotalAmount(), order.getPaymentStrategy());

        if (paid) {
            // 3. Add to Singleton list
            OrderManager.getInstance().addOrder(order);
            System.out.println(BLUE + "[LLD Trace] Order registered in Singleton OrderManager" + RESET);

            // 4. Dispatch notification through polymorphic NotificationManager (SRP & OCP compliance)
            NotificationService notificationService = new NotificationManager(order);
            System.out.println(BLUE + "[LLD Trace] Dispatching notifications via NotificationManager..." + RESET);
            notificationService.notifyUser();

            // 5. Clear Cart (SRP)
            cart.clear();
            System.out.println(GREEN + "Order Placed Successfully! Cart has been reset." + RESET);
        } else {
            System.out.println(RED + "Payment failed. Order placement aborted." + RESET);
        }
        
        pressEnterToContinue();
    }

    private static void viewOrderHistory() {
        System.out.println(YELLOW + "\n--- Order History (OrderManager Singleton) ---" + RESET);
        List<Order> orders = OrderManager.getInstance().listOrder();
        if (orders.isEmpty()) {
            System.out.println("No orders found in the global registry.");
        } else {
            for (Order order : orders) {
                System.out.println(order.toString());
                System.out.println("--------------------------------------------------");
            }
        }
        pressEnterToContinue();
    }

    private static void changeUserProfile() {
        System.out.println(YELLOW + "\n--- Switch / Manage User Profiles ---" + RESET);
        System.out.println("Select a demo user profile to switch to, or edit the current user details:");
        for (int i = 0; i < demoUsers.size(); i++) {
            User u = demoUsers.get(i);
            String prefix = (u == currentUser) ? "-> " : "   ";
            System.out.printf("%s[%d] %s (ID: %d) - Address: %s%n", 
                prefix, i + 1, u.getName(), u.getUid(), u.getAddress());
        }
        System.out.println("   [6] Edit Current User Profile Details");
        System.out.print("Enter choice (1-6, or press Enter to return): ");
        String choice = scanner.nextLine().trim();
        if (choice.isEmpty()) return;

        try {
            int idx = Integer.parseInt(choice);
            if (idx >= 1 && idx <= 5) {
                currentUser = demoUsers.get(idx - 1);
                System.out.printf(GREEN + "Successfully switched session to user: %s%n" + RESET, currentUser.getName());
            } else if (idx == 6) {
                System.out.print("Enter New Name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter New Address: ");
                String address = scanner.nextLine().trim();

                if (!name.isEmpty()) currentUser.setName(name);
                if (!address.isEmpty()) currentUser.setAddress(address);

                System.out.println(GREEN + "Current user profile updated successfully." + RESET);
            } else {
                System.out.println(RED + "Invalid option." + RESET);
            }
        } catch (NumberFormatException e) {
            System.out.println(RED + "Invalid number selection." + RESET);
        }
        pressEnterToContinue();
    }

    private static void explainDesignPatterns() {
        System.out.println(CYAN + "\n==================================================");
        System.out.println("       DESIGN PATTERNS & SOLID PRINCIPLES         ");
        System.out.println("==================================================" + RESET);
        
        System.out.println(BOLD + "1. Creational Patterns:" + RESET);
        System.out.println("   - " + BOLD + "Singleton Pattern" + RESET + ": Applied in `RestaurantManager` and `OrderManager` to ensure");
        System.out.println("     a single, global registry instance exists for database/state management.");
        System.out.println("   - " + BOLD + "Factory Method Pattern" + RESET + ": `IOrderFactory` and its implementations (`NowOrder`,");
        System.out.println("     `ScheduleOrder`) decouple order creation details. The main app isn't tied to concrete");
        System.out.println("     order types, enhancing extensibility.");
        
        System.out.println(BOLD + "\n2. Behavioral Patterns:" + RESET);
        System.out.println("   - " + BOLD + "Strategy Pattern" + RESET + ": `IPaymentStrategy` allows dynamically switching between");
        System.out.println("     `UPI`, `CreditCard`, and `NetBanking` payment algorithms at runtime without changing");
        System.out.println("     the `Order` class logic.");
        
        System.out.println(BOLD + "\n3. OO Design Principles (SOLID):" + RESET);
        System.out.println("   - " + BOLD + "SRP (Single Responsibility)" + RESET + ": Every model has exactly one duty (e.g. `Cart` manages");
        System.out.println("     items and sum totals; `NotificationService` handles dispatching alerts).");
        System.out.println("   - " + BOLD + "OCP (Open/Closed)" + RESET + ": New payment methods can be added by implementing");
        System.out.println("     `IPaymentStrategy` without modifying existing checkout code.");
        System.out.println("   - " + BOLD + "LSP (Liskov Substitution)" + RESET + ": Subclasses `DeliverOrder` and `PickupOrder` can safely");
        System.out.println("     be treated as `Order` parents without causing unexpected side-effects.");
        System.out.println("   - " + BOLD + "ISP (Interface Segregation)" + RESET + ": We use granular interfaces like `IPaymentStrategy` and");
        System.out.println("     `IOrderFactory` rather than bloated multi-purpose interfaces.");
        System.out.println("   - " + BOLD + "DIP (Dependency Inversion)" + RESET + ": `Order` depends on abstract `IPaymentStrategy` and");
        System.out.println("     `IOrderFactory` interfaces rather than concrete implementations.");
        
        System.out.println(BOLD + "\n4. Structural Relations:" + RESET);
        System.out.println("   - " + BOLD + "Composition" + RESET + ": `User` and `Cart` demonstrate a tight composition relationship;");
        System.out.println("     the cart is instantiated in the `User` constructor, matching its lifecycle.");
        System.out.println("   - " + BOLD + "Association" + RESET + ": `Order` holds references to `User`, `Restaurant`, and `Menu` items.");
        System.out.println("==================================================\n");

        pressEnterToContinue();
    }

    private static void pressEnterToContinue() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
}
