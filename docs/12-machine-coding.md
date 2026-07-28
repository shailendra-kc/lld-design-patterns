# 12. Machine Coding and Testing

## Recommended layering

```text
api/
domain/
application/
infrastructure/
```

- `domain`: entities, value objects, policies, invariants.
- `application`: use cases and orchestration.
- `infrastructure`: database, external APIs, messaging.
- `api`: controllers and request/response DTOs.

## Testing pyramid

### Unit tests

Test domain behavior without Spring or a database.

```java
@Test
void occupiedSpotCannotAcceptAnotherVehicle() {
    ParkingSpot spot = new ParkingSpot(SpotType.CAR);
    spot.park(new Car("A"));
    assertThrows(IllegalStateException.class,
            () -> spot.park(new Car("B")));
}
```

### Integration tests

Test repositories, transactions, serialization, and external adapters.

### End-to-end tests

Validate major user workflows.

## Machine-coding checklist

- Requirements and assumptions are documented.
- Code compiles and runs.
- Domain rules are encapsulated.
- Interfaces exist at meaningful variation points.
- Exceptions are explicit.
- Tests cover success and failure paths.
- Thread safety is considered.
- README contains API examples and design decisions.

## Avoid

- Framework-heavy setup before core logic.
- Public setters for every field.
- Static mutable state.
- Catching generic `Exception` silently.
- Pattern names without real value.
