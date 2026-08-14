# Payment Gateway System

A Java-based Low-Level Design project that simulates a modular payment processing system with multiple payment gateways and banking systems.

## Features

- Multiple payment gateways
- Gateway selection using Factory Pattern
- Common payment workflow using Template Method Pattern
- Retry handling using Proxy Pattern
- Banking system abstraction
- Payment validation
- Failure simulation and recovery
- Automated testing

## Payment Flow

    Client
       |
       v
    PaymentController
       |
       v
    GatewayFactory
       |
       v
    PaymentGatewayProxy
       |
       v
    PaymentService
       |
       v
    PaymentGateway
       |
       v
    BankingSystem
       |
       v
    Payment Result

## Payment Processing
    Payment Request
          |
          v
    Validate Payment
          |
          v
    Initialize Payment
          |
          v
    Process Payment
          |
          v
    Banking System
          |
          v
    Confirm Payment

## Design Patterns
- Singleton
- Factory
- Template Method
- Proxy
- Strategy
- Dependency Injection

## Project Structure
    src/
    └── main/
        └── java/
            └── com/
                └── payment/
                    ├── Main.java
                    ├── controller/
                    ├── service/
                    ├── gateway/
                    ├── banking/
                    └── test/

## Supported Gateways
- Paytm
- Razorpay

The architecture allows new payment gateways to be added without changing the core payment processing flow.

## Tech Stack
- Java
- Object-Oriented Programming
- SOLID Principles
- Design Patterns
- Low-Level Design


## Run
    chmod +x run.sh
    ./run.sh

 ### Or compile manually:
       rm -rf bin
        mkdir -p bin
        javac -d bin $(find src -name "*.java")
        java -cp bin com.payment.Main

## Testing
    java -cp bin com.payment.test.LldTestRunner

## Learning Objectives

This project demonstrates practical implementation of Low-Level Design concepts, SOLID principles, design patterns, modular architecture, and retry-based fault handling.

## License

This project is intended for educational and Low-Level Design interview preparation.
