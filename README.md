# Java Movie Rental System

**Course:** Software Architecture (SE4352) — The University of Texas at Dallas

**Team Members:** Jennah Shahein, Noah Ferenczhalmy, Jason Lehman

## Overview

The Java Movie Rental System is an object-oriented application designed to simulate the operations of a movie rental business. The system manages customers, movies, rentals, and transactions while supporting flexible pricing and rewards programs.

A primary focus of this project was applying software architecture principles and design patterns to create a system that is maintainable, extensible, and easy to modify as business requirements evolve.

## System Functionality

### Customer Management

* Create and manage customer records
* Track customer rental activity
* Maintain customer reward points

### Movie Rentals

* Store and manage movie information
* Create rental records
* Track rental durations and associated charges

### Transaction Processing

* Process rental transactions
* Calculate rental costs
* Apply promotional discounts and pricing rules

### Rewards Program

* Award bonus points for rentals
* Support customizable rewards strategies
* Extend rewards behavior without modifying core classes

## Software Architecture & Design Patterns

### Strategy Pattern

The Strategy Pattern was implemented to separate pricing and reward calculations from the core business objects. This allows pricing policies and bonus point calculations to be changed independently without affecting the rest of the system.

Implemented strategies include:

* PriceStrategy
* BonusPointsStrategy
* TransactionPriceStrategy
* TransactionBonusPointsStrategy

### Decorator Pattern

The Decorator Pattern was used to dynamically extend pricing and rewards functionality. This approach enables promotional discounts and bonus programs to be added without modifying existing business logic.

Implemented decorators include:

* PriceDecorator
* BonusPointsDecorator
* TransactionPriceDecorator
* TransactionBonusPointsDecorator

Example use cases include percentage discounts, fixed-price reductions, and bonus reward promotions.

## UML Design

A UML class diagram was developed to model the relationships between customers, rentals, transactions, pricing strategies, and decorators. The diagram was used throughout development to guide system architecture and maintain clear separation of responsibilities between components.

## Key Features

* Object-oriented system design
* Modular pricing architecture
* Flexible rewards program
* Dynamic discount application
* Transaction processing workflow
* UML-based software design
* Strategy Pattern implementation
* Decorator Pattern implementation

## Technologies

* Java
* Object-Oriented Programming (OOP)
* UML Modeling
* Strategy Design Pattern
* Decorator Design Pattern
