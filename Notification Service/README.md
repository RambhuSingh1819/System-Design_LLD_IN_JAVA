## Secure Plug-and-Play Notification System (Low-Level Design)

![Java](https://img.shields.io/badge/Java-8%2B-orange)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-Singleton%20%7C%20Observer%20%7C%20Strategy%20%7C%20Decorator-blue)
![LLD](https://img.shields.io/badge/Low-Level%20Design-Project-success)

A console-based **Low-Level Design (LLD)** project built in **Java** demonstrating a secure and extensible notification system using multiple object-oriented design patterns.

The application supports plug-and-play notification channels, runtime message enhancement using decorators, and event-driven communication through the Observer Pattern.

---

#  Features

*  Send notifications through multiple channels
*  Email Notifications
*  SMS Notifications
*  Popup Notifications
*  Base64 Message Encryption
*  Verified Sender Signature
*  Runtime Channel Enable/Disable
*  Notification History
*  Built-in Diagnostic Test Runner
*  Clean Layered Architecture

---

#  Design Patterns Used

| Pattern   | Purpose                                                |
| --------- | ------------------------------------------------------ |
| Singleton | Maintains a single Notification Service instance       |
| Observer  | Notifies all subscribers when a notification is sent   |
| Strategy  | Supports multiple notification delivery channels       |
| Decorator | Dynamically adds encryption, decryption and signatures |

---

#  Project Structure

```text
Notification-Service/
│
|
├
├
│
└── src/
    └── main/
        └── java/
            └── com/
                └── notification/
                    ├── Main.java
                    ├── Notification.java
                    ├── SimpleNotification.java
                    │
                    ├── decorator/
                    │     ├── NotificationDecorator.java
                    │     ├── SignatureDecorator.java
                    │     └── DecryptDecorator.java
                    │
                    ├── observer/
                    │     ├── Observable.java
                    │     ├── Observer.java
                    │     ├── Logger.java
                    │     ├── NotificationEngine.java
                    │     └── NotificationObservable.java
                    │
                    ├── strategy/
                    │     ├── NotificationStrategy.java
                    │     ├── EmailStrategy.java
                    │     ├── SMSStrategy.java
                    │     └── PopupStrategy.java
                    │
                    ├── service/
                    │     └── NotificationService.java
```

---

# System Workflow

```
User
   │
   ▼
Notification Service (Singleton)
   │
   ▼
Notification Observable
   │
   ├────────► Logger
   │
   └────────► Notification Engine
                     │
                     ▼
           Strategy Pattern
        ┌──────┬────────┬────────┐
        │      │        │        │
      Email   SMS     Popup
```

# Console Demo

```
=============================
 SECURE NOTIFICATION SYSTEM
=============================

1. Compose Notification

2. Toggle Delivery Channels

3. View Notification History

4. Run Diagnostic Tests

5. Exit
```

---

#  Concepts Demonstrated

* Object-Oriented Programming
* SOLID Principles
* Low-Level Design
* Event Driven Architecture
* Interface-based Programming
* Loose Coupling
* Runtime Polymorphism
* Extensible Architecture

---

#  Technologies

* Java
* OOP
* Collections Framework
* Design Patterns
* CLI Application
* Bash

---

# Future Enhancements

* Push Notifications
* WhatsApp Integration
* Kafka Event Queue
* RabbitMQ Support
* REST API
* Spring Boot Version
* Database Persistence
* JWT Authentication
* Docker Deployment

---

#  Author

**Rambhu Singh**

Java Full Stack Developer | Software Engineer | Low-Level Design Enthusiast

---

 If you found this project useful, don't forget to **Star** the repository.
