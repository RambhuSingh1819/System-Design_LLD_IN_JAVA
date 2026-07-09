package com.notification.decorator;

import com.notification.Notification;
import java.util.Base64;

public class DecryptDecorator extends NotificationDecorator {

    public DecryptDecorator(Notification notification) {
        super(notification);
    }
    @Override
    public String getContent() {
        String baseContent = super.getContent();
        if (baseContent != null && baseContent.startsWith("[ENCRYPTED]: ")) {
            try {
                String encryptedPart = baseContent.substring("[ENCRYPTED]: ".length());
                byte[] decodedBytes = Base64.getDecoder().decode(encryptedPart);
                return new String(decodedBytes);
            } catch (IllegalArgumentException e) {
                return "[Decryption Failure] " + baseContent;
            }
        }
        return baseContent;
    }
}
