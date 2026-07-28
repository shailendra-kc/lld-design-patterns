# 1. Low-Level Design Fundamentals

## What is LLD?

Low-Level Design converts product requirements into implementable software components: classes, interfaces, methods, objects, state transitions, and collaborations.

HLD answers **which services and infrastructure exist**. LLD answers **how the code inside a component is organized**.

| HLD | LLD |
|---|---|
| Services, databases, queues | Classes, interfaces, methods |
| Scaling and availability | Responsibilities and interactions |
| Network communication | Object collaboration |
| Deployment topology | State and behavior |

## Goals of good LLD

- High cohesion: one class has a focused responsibility.
- Low coupling: classes depend on stable abstractions.
- Encapsulation: invalid state cannot be created easily.
- Extensibility: common changes require adding code, not rewriting working code.
- Testability: business rules can be tested without infrastructure.
- Readability: names and structure communicate intent.

## Interview approach

### 1. Clarify requirements

Ask about scope, actors, constraints, concurrency, persistence, and out-of-scope features.

Example for a parking lot:

- Which vehicle types are supported?
- Are multiple entrances supported?
- How is pricing calculated?
- Is spot reservation needed?
- Can two gates assign the same spot concurrently?

### 2. Identify use cases

Write verbs before classes:

- Park a vehicle
- Allocate a spot
- Generate a ticket
- Calculate fees
- Process payment
- Release a spot

### 3. Extract domain objects

Nouns become candidate entities, but not every noun deserves a class.

- `Vehicle`, `ParkingSpot`, `Ticket`, `Floor`, `Payment`

### 4. Separate entity, value object, service, and repository

- **Entity:** identity changes over time, such as `Ticket`.
- **Value object:** defined by values and usually immutable, such as `Money`.
- **Domain service:** behavior that does not naturally belong to one entity.
- **Repository:** abstraction for persistence.

### 5. Define invariants

An invariant must always remain true.

Examples:

- One parking spot cannot hold two vehicles.
- A closed ticket cannot be closed again.
- A confirmed seat cannot be booked by another user.

### 6. Model behavior, not data containers

Avoid anemic models where objects contain only getters and setters.

```java
public final class ParkingSpot {
    private Vehicle parkedVehicle;

    public boolean isAvailable() {
        return parkedVehicle == null;
    }

    public void park(Vehicle vehicle) {
        if (!isAvailable()) {
            throw new IllegalStateException("Spot is occupied");
        }
        this.parkedVehicle = vehicle;
    }
}
```

The object protects its own invariant.

## Composition over inheritance

Use inheritance only for a true and stable **is-a** relationship. Prefer composition when behavior can vary independently.

Instead of:

```text
PremiumEmailNotification extends EmailNotification
```

prefer:

```text
Notification has DeliveryChannel
Notification has RetryPolicy
Notification has TemplateRenderer
```

This prevents deep inheritance hierarchies and makes behavior replaceable.

## Common interview mistakes

- Starting with patterns before understanding requirements.
- Creating a class for every noun.
- Using one god class for all behavior.
- Exposing mutable collections.
- Ignoring concurrency and idempotency.
- Mixing controllers, domain rules, and persistence logic.
- Not discussing trade-offs.
