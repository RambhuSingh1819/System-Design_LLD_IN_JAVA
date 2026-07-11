package com.notification.strategy;


public class PopupStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String content) {
        System.out.println("\u001B[32m" + "🖥️  [Popup] Displaying Popup Modal Alert..." + "\u001B[0m");
        System.out.println("   ====================================");
        String[] lines = content.split("\n");
        for (String line : lines) {
            System.out.println("   ‖ " + line);
        }
        System.out.println("   ====================================");
        System.out.println("   ✓ Popup displayed successfully!\n");
    }
}
