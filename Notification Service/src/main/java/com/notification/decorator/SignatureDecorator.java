package com.notification.decorator;

import com.notification.Notification;

public class SignatureDecorator extends NotificationDecorator {
    public SignatureDecorator(Notification notification) {
        super(notification);
    }

  
    @Override
    public String getContent() {
        String content = super.getContent();
        if (content == null) {
            content = "";
        }
        return content + "\n-- Sent via Secure Notification Service (Verified Signature)";
    }
}
