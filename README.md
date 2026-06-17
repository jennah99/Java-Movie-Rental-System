# Java Movie Rental System

**Course:** Software Architecture (SE4352) — The University of Texas at Dallas

**Team Members:** Jennah Shahein, Noah Ferenczhalmy, Jason Lehman

## Overview

This project implements a movie rental management system in Java using object-oriented design principles and software architecture patterns. The system allows customers to rent movies, process transactions, calculate rental prices, and earn bonus points through configurable pricing and rewards strategies.

The project emphasizes extensibility and maintainability through the use of design patterns including Strategy and Decorator, allowing pricing rules and rewards programs to be modified without changing core business logic.

## System Components

### 1. Customer Management

* Create and manage customer accounts
* Track customer rental history
* Maintain accumulated bonus points

### 2. Movie Management

* Store movie information
* Associate movies with rental transactions
* Support different pricing strategies

### 3. Rental Processing

* Create movie rental records
* Track rental duration
* Calculate rental charges

### 4. Transaction Management

* Process customer transactions
* Calculate final rental costs
* Apply discounts and promotions

## Design Patterns

### Strategy Pattern

The Strategy Pattern was used to separate pricing and rewards calculations from the core business objects.

Implemented Strategies:

* PriceStrategy
* BonusPointsStrategy
* TransactionPriceStrategy
* TransactionBonusPointsStrategy

Benefits:

* Supports multiple pricing models
* Simplifies future business rule changes
* Improves maintainability

### Decorator Pattern

The Decorator Pattern was used to dynamically modify pricing and rewards behavior without altering existing classes.

Implemented Decorators:

* PriceDecorator
* BonusPointsDecorator
* TransactionPriceDecorator
* TransactionBonusPointsDecorator

Example Promotions:

* 50% discount pricing
* Dollar-off discounts
* Bonus rewards programs

Benefits:

* Extensible promotion system
* Reusable pricing components
* Reduced code duplication

## UML Design

A UML class diagram was created to model system relationships and architecture.

Included Classes:

* Customer
* Movie
* Rental
* Transaction
* Strategy Classes
* Decorator Classes

## Project Structure

```text
Customer.java
Movie.java
Rental.java
Transaction.java

PriceStrategy.java
BonusPointsStrategy.java
TransactionPriceStrategy.java
TransactionBonusPointsStrategy.java

PriceDecorator.java
BonusPointsDecorator.java
TransactionPriceDecorator.java
TransactionBonusPointsDecorator.java

Main.java
```

## Key Features

* Object-oriented system design
* Modular pricing architecture
* Configurable rewards system
* Dynamic discount application
* Transaction processing
* UML-based architecture design
* Strategy Pattern implementation
* Decorator Pattern implementation

## Technologies

* Java
* Object-Oriented Programming (OOP)
* UML Modeling
* Strategy Pattern
* Decorator Pattern

## Files

* Main.java — Application entry point
* Customer.java — Customer management
* Movie.java — Movie data model
* Rental.java — Rental processing
* Transaction.java — Transaction management
* PriceStrategy.java — Pricing strategy interface
* BonusPointsStrategy.java — Rewards strategy interface
* SE4352 MovieRental Class diagram.png — UML class diagram

