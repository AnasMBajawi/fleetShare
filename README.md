# FleetShare 🚗⚡

FleetShare is a simple ride-sharing simulation written in **Java**,
built with a clean domain-driven design structure.\
It demonstrates entities like `User`, `Vehicle`, `Trip`, and flexible
pricing strategies (distance, time, hybrid).

------------------------------------------------------------------------

## 📂 Project Structure

    com.anas.fleetshare
     ├── domain
     │   ├── user/{User, MembershipTier}
     │   ├── vehicle/{Vehicle, Car, EBike, Scooter, VehicleState}
     │   ├── trip/{Trip, TripId, TripState}
     │   ├── pricing/{PricingStrategy, DistanceBasedPricing, TimeBasedPricing, HybridPricing}
     │   └── common/{Money}
     │
     ├── application
     │   ├── service/{UserService, VehicleService, TripService}
     │   ├── repositories/{UserRepository, VehicleRepository, TripRepository}
     │   └── exceptions/{...}
     │
     ├── infrastructure/{InMemoryUserRepository, InMemoryVehicleRepository, InMemoryTripRepository}
     └── ui/{ConsoleApp}

------------------------------------------------------------------------

## ▶️ How to Run

1.  Clone the repository or copy the project folder.

2.  Open the project in **IntelliJ IDEA** (or any Java IDE).

3.  Make sure JDK **17+** is installed.

4.  Run the main class:

    ``` bash
    src/main/java/com/anas/fleetshare/ui/ConsoleApp.java
    ```

You should see a simulation of registering a user, adding a car,
starting and stopping a trip, and printing the calculated price.

------------------------------------------------------------------------

## 🏗️ Design Decisions

-   **Domain-Driven Design (DDD):**\
    Entities like `User`, `Vehicle`, and `Trip` are modeled explicitly.

-   **Value Objects:**\
    `Money` is immutable and encapsulates `BigDecimal` for safe
    calculations.

-   **Strategy Pattern for Pricing:**\
    Flexible switching between `TimeBasedPricing`,
    `DistanceBasedPricing`, and `HybridPricing` at runtime.

-   **Repositories (In-Memory):**\
    Simple persistence layer for testing. Could be replaced with a real
    DB later.

-   **Services Layer:**\
    Encapsulates use cases (`UserService`, `VehicleService`,
    `TripService`). Keeps domain logic clean.

------------------------------------------------------------------------

## ⚖️ Trade-offs

-   **In-memory Repositories** → Simple but no persistence. Restarting
    clears all data.\
-   **Console UI** → Minimalistic; good for demo, but not
    production-ready.\
-   **No Validation Library** → Validation done with basic checks +
    exceptions.

------------------------------------------------------------------------

## 📊 UML Diagram

![UML Diagram](Screenshot%202025-09-23%20195430.png)

------------------------------------------------------------------------
