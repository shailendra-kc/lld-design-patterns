# 6. Behavioral Design Patterns

Behavioral patterns organize communication and algorithms.

## Strategy

Encapsulates interchangeable algorithms.

```java
interface PricingStrategy {
    Money calculate(Trip trip);
}
```

Examples: regular pricing, surge pricing, weekend pricing.

Use Strategy when behavior varies independently of the context.

## Observer

One-to-many notification when state changes.

Examples:

- Domain events
- UI listeners
- Stock price subscribers

Be careful about memory leaks, exception isolation, and notification ordering.

## Command

Represents a request as an object.

Benefits:

- Queuing
- Logging
- Retry
- Undo
- Scheduling

A job scheduler naturally stores commands or command descriptors.

## State

Moves state-specific behavior into separate objects.

Example elevator states:

- `IdleState`
- `MovingUpState`
- `MovingDownState`
- `DoorOpenState`

Use when large conditionals depend on an object's current state.

## Chain of Responsibility

Passes a request through ordered handlers.

Examples:

- Servlet filters
- Validation pipeline
- Authentication pipeline
- Approval workflow

Each handler decides whether to process, transform, reject, or forward.

## Template Method

Defines an algorithm skeleton while subclasses customize selected steps.

Useful when the workflow is fixed but certain steps differ.

Prefer composition with Strategy when runtime flexibility is required.

## Mediator

Centralizes communication among many collaborating objects.

Example: an air traffic controller coordinates aircraft so aircraft do not communicate pairwise.

## Iterator

Provides traversal without exposing collection internals.

## Memento

Captures state for restoration, commonly used for undo.

## Visitor

Adds operations to a stable object structure without modifying element classes.

Trade-off: adding new element types becomes harder.

## Pattern comparison

| Pattern | Best signal |
|---|---|
| Strategy | Interchangeable algorithm |
| Observer | Event subscribers |
| Command | Request as data |
| State | Behavior changes by state |
| Chain | Ordered processing pipeline |
| Template Method | Fixed workflow with variable steps |
| Mediator | Many-to-many coordination |
