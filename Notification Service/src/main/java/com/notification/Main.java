package com.notification;

import com.notification.decorator.DecryptDecorator;
import com.notification.decorator.SignatureDecorator;
import com.notification.observer.Logger;
import com.notification.observer.NotificationEngine;
import com.notification.observer.NotificationObservable;
import com.notification.service.NotificationService;
import com.notification.strategy.EmailStrategy;
import com.notification.strategy.SMSStrategy;
import com.notification.strategy.PopupStrategy;
import com.notification.strategy.NotificationStrategy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.List;

public class Main {

    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        // Initialize Core Components
        NotificationService service = NotificationService.getInstance();
        NotificationObservable observable = service.getObservable();

        // Initialize and register Observers
        Logger logger = new Logger(observable);
        NotificationEngine engine = new NotificationEngine(observable);
        observable.add(logger);
        observable.add(engine);

        // Pre-create strategies (Plug-and-play components)
        NotificationStrategy emailStrategy = new EmailStrategy();
        NotificationStrategy smsStrategy = new SMSStrategy();
        NotificationStrategy popupStrategy = new PopupStrategy();

        // Register all strategies by default
        engine.addStrategy(emailStrategy);
        engine.addStrategy(smsStrategy);
        engine.addStrategy(popupStrategy);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Welcome Header
        printBanner();

        while (true) {
            try {
                printMenu(engine);
                System.out.print(BOLD + "Select an option (1-5): " + RESET);
                String choiceStr = reader.readLine().trim();
                int choice;
                try {
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException e) {
                    System.out.println(RED + "Invalid choice! Please enter a number between 1 and 5." + RESET);
                    continue;
                }

                switch (choice) {
                    case 1:
                        composeAndSend(reader, service, emailStrategy, smsStrategy, popupStrategy);
                        break;
                    case 2:
                        toggleStrategies(reader, engine, emailStrategy, smsStrategy, popupStrategy);
                        break;
                    case 3:
                        viewHistory(service);
                        break;
                    case 4:
                        runAutomatedTests();
                        break;
                    case 5:
                        System.out.println(GREEN + "\nThank you for using the Secure Notification System. Goodbye!" + RESET);
                        System.exit(0);
                        break;
                    default:
                        System.out.println(RED + "Invalid choice! Please enter a number between 1 and 5." + RESET);
                }
            } catch (Exception e) {
                System.out.println(RED + "An error occurred: " + e.getMessage() + RESET);
                e.printStackTrace();
            }
        }
    }

    private static void printBanner() {
        System.out.println(PURPLE + BOLD + "==========================================================" + RESET);
        System.out.println(PURPLE + BOLD + "   🔔 SECURE PLUG-AND-PLAY NOTIFICATION SYSTEM LLD CLI" + RESET);
        System.out.println(PURPLE + BOLD + "==========================================================" + RESET);
        System.out.println("Powered by Gemini 3.5 Flash | Java 8+ Implementation");
        System.out.println("Patterns Demonstrated: Singleton, Strategy, Observer, Decorator\n");
    }

    private static void printMenu(NotificationEngine engine) {
        System.out.println(BLUE + BOLD + "\n>>> MAIN MENU" + RESET);
        System.out.println("1)  Compose and Dispatch Notification");
        System.out.println("2)  Toggle Delivery Channels (Plug & Play)");
        System.out.println("3)  View Notification Send History");
        System.out.println("4)  Run Self-Diagnostic Unit Tests");
        System.out.println("5)  Exit Application");

        // Display current active strategies
        List<NotificationStrategy> active = engine.getStrategies();
        System.out.print(YELLOW + "Active Channels: " + RESET);
        if (active.isEmpty()) {
            System.out.println(RED + "NONE (Notifications will not be routed!)" + RESET);
        } else {
            StringBuilder sb = new StringBuilder();
            for (NotificationStrategy s : active) {
                String name = s.getClass().getSimpleName().replace("Strategy", "");
                sb.append(name).append(", ");
            }
            String channels = sb.toString();
            System.out.println(GREEN + channels.substring(0, channels.length() - 2) + RESET);
        }
        System.out.println();
    }

    private static void composeAndSend(BufferedReader reader, NotificationService service,
                                       NotificationStrategy email, NotificationStrategy sms, NotificationStrategy popup) throws Exception {
        System.out.println(CYAN + BOLD + "\n--- COMPOSE NEW NOTIFICATION ---" + RESET);
        System.out.print("Enter notification text: ");
        String text = reader.readLine();
        if (text == null || text.trim().isEmpty()) {
            System.out.println(RED + "Notification content cannot be empty!" + RESET);
            return;
        }

        // Prompt for decorators
        System.out.print("Encrypt notification payload? (y/n): ");
        boolean encrypt = reader.readLine().trim().equalsIgnoreCase("y");

        System.out.print("Apply verified sender signature? (y/n): ");
        boolean sign = reader.readLine().trim().equalsIgnoreCase("y");

        // Dynamic construction
        Notification notification;
        if (encrypt) {
            String base64Enc = Base64.getEncoder().encodeToString(text.getBytes());
            System.out.println(YELLOW + "[Encrypter] Payload encrypted to Base64: [ENCRYPTED]: " + base64Enc + RESET);
            notification = new SimpleNotification("[ENCRYPTED]: " + base64Enc);
        } else {
            notification = new SimpleNotification(text);
        }

        // Ask for Decryption Decorator if encrypted, so we can demo decoding
        if (encrypt) {
            System.out.print("Add Decrypt Decorator to recipient wrapper? (y/n): ");
            boolean decryptDecorator = reader.readLine().trim().equalsIgnoreCase("y");
            if (decryptDecorator) {
                notification = new DecryptDecorator(notification);
                System.out.println(GREEN + "[Decorator] Added DecryptDecorator to pipeline." + RESET);
            }
        }

        if (sign) {
            notification = new SignatureDecorator(notification);
            System.out.println(GREEN + "[Decorator] Added SignatureDecorator to pipeline." + RESET);
        }

        System.out.println(BLUE + BOLD + "\nDispatching notification to system..." + RESET);
        service.sendNotification(notification);
        System.out.println(GREEN + "✓ Dispatch completed!" + RESET);
    }

    private static void toggleStrategies(BufferedReader reader, NotificationEngine engine,
                                         NotificationStrategy email, NotificationStrategy sms, NotificationStrategy popup) throws Exception {
        System.out.println(CYAN + BOLD + "\n--- PLUG & PLAY CHANNEL MANAGER ---" + RESET);
        
        List<NotificationStrategy> active = engine.getStrategies();
        boolean hasEmail = active.contains(email);
        boolean hasSms = active.contains(sms);
        boolean hasPopup = active.contains(popup);

        System.out.println("1) Email Channel: " + (hasEmail ? GREEN + "[ENABLED]" : RED + "[DISABLED]") + RESET);
        System.out.println("2) SMS Channel:   " + (hasSms ? GREEN + "[ENABLED]" : RED + "[DISABLED]") + RESET);
        System.out.println("3) Popup Channel: " + (hasPopup ? GREEN + "[ENABLED]" : RED + "[DISABLED]") + RESET);
        System.out.println("4) Back to Main Menu");
        System.out.print("Select a channel to toggle (1-4): ");
        
        String input = reader.readLine().trim();
        switch (input) {
            case "1":
                if (hasEmail) {
                    engine.removeStrategy(email);
                    System.out.println(YELLOW + "Email channel disabled." + RESET);
                } else {
                    engine.addStrategy(email);
                    System.out.println(GREEN + "Email channel enabled!" + RESET);
                }
                break;
            case "2":
                if (hasSms) {
                    engine.removeStrategy(sms);
                    System.out.println(YELLOW + "SMS channel disabled." + RESET);
                } else {
                    engine.addStrategy(sms);
                    System.out.println(GREEN + "SMS channel enabled!" + RESET);
                }
                break;
            case "3":
                if (hasPopup) {
                    engine.removeStrategy(popup);
                    System.out.println(YELLOW + "Popup channel disabled." + RESET);
                } else {
                    engine.addStrategy(popup);
                    System.out.println(GREEN + "Popup channel enabled!" + RESET);
                }
                break;
            case "4":
                break;
            default:
                System.out.println(RED + "Invalid option." + RESET);
        }
    }

    private static void viewHistory(NotificationService service) {
        System.out.println(CYAN + BOLD + "\n--- NOTIFICATION HISTORY ---" + RESET);
        List<Notification> list = service.getNotifications();
        if (list.isEmpty()) {
            System.out.println(YELLOW + "No notifications sent in this session." + RESET);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Notification n = list.get(i);
            System.out.println(BOLD + "Notification #" + (i + 1) + RESET);
            System.out.println("Class: " + n.getClass().getName());
            System.out.println("Content Preview:");
            System.out.println("----------------------------------------");
            System.out.println(n.getContent());
            System.out.println("----------------------------------------\n");
        }
    }

    private static void runAutomatedTests() {
        System.out.println(CYAN + BOLD + "\n--- RUNNING DIAGNOSTIC TEST RUNNER ---" + RESET);
        com.notification.test.LldTestRunner.main(new String[0]);
    }
}
