## Food Delivery System (Low Level Design in Java)

A Low Level Design (LLD) implementation of a Food Delivery System built using Java and Object-Oriented Design Principles.

This project demonstrates how real-world food delivery platforms like **Swiggy** and **Zomato** can be modeled using clean object-oriented design and design patterns.

---

# Features

- User Registration
- Restaurant Management
- Menu Management
- Shopping Cart
- Place Order
- Pickup Order
- Home Delivery
- Scheduled Delivery
- Payment Management
- Notification System
- Order Management

---

# Design Patterns Used

| Pattern | Usage |
|----------|-------|
| Factory Pattern | Create different order types |
| Strategy Pattern | Payment & Delivery Strategy |
| Singleton Pattern | Managers (Restaurant, Order, Payment) |
| Interface Segregation | Notification Channels |
| Dependency Injection | Loose Coupling |

---

# Project Structure

```
src/
│
├── Main.java
│
├── model/
│   ├── User.java
│   ├── Restaurant.java
│   ├── Menu.java
│   ├── Cart.java
│   └── Order.java
│
├── manager/
│   ├── RestaurantManager.java
│   ├── OrderManager.java
│   ├── PaymentManager.java
│   └── NotificationManager.java
│
├── factory/
│   ├── IOrderFactory.java
│   ├── PickupOrder.java
│   ├── DeliverOrder.java
│   └── ScheduleOrder.java
│
├── notification/
│   ├── INotificationChannel.java
│   ├── GmailChannel.java
│   ├── PhoneChannel.java
│   └── WhatsAppChannel.java
│
└── test/
    └── LldTestRunner.java
```

---

# Class Diagram

![Class Diagram](images/class-diagram.jpg)

---

# Functional Requirements

![Requirements](images/requirements.jpg)

---

# Flow Diagram

```text
User
   │
   ▼
RestaurantManager
   │
   ▼
Restaurant
   │
   ▼
Menu
   │
   ▼
Cart
   │
   ▼
OrderManager
   │
   ▼
Order Factory
   │
   ├── PickupOrder
   ├── DeliverOrder
   └── ScheduleOrder
   │
   ▼
PaymentManager
   │
   ▼
NotificationManager
   │
   ├── Gmail
   ├── Phone
   └── WhatsApp
```

---

# Order Processing Flow

1. User selects restaurant.
2. User adds food items to cart.
3. User places order.
4. Factory creates appropriate order.
5. Payment is processed.
6. Restaurant accepts order.
7. Notification is sent.
8. Order delivered/picked up.

---

# Design Principles

- SOLID Principles
- High Cohesion
- Low Coupling
- Open/Closed Principle
- Dependency Inversion
- Interface Segregation

---

# Technologies Used

- Java
- OOP
- Design Patterns
- Collections Framework

---

# Future Improvements

- Multi Restaurant Support
- Coupon Engine
- Inventory Management
- Delivery Partner Allocation
- Live Order Tracking
- Rating & Review
- Recommendation System
- Multi-threaded Order Processing
- Database Integration
- REST APIs
- Spring Boot Version

---

# Author

**Rambhu Singh**

GitHub: https://github.com/RambhuSingh1819

---

⭐ If you found this project helpful, consider giving it a star.
