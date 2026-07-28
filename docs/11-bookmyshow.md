# 11. BookMyShow LLD

## Requirements

- Search movies and shows by city.
- View seat availability.
- Temporarily lock selected seats.
- Pay and confirm booking.
- Release locks after timeout or payment failure.

## Domain model

```mermaid
classDiagram
    class Movie
    class Theatre
    class Screen
    class Show
    class Seat
    class ShowSeat {
      -SeatStatus status
      -Money price
      -Instant lockExpiry
    }
    class Booking
    class Payment

    Theatre "1" *-- "1..*" Screen
    Screen "1" o-- "0..*" Show
    Show "1" *-- "1..*" ShowSeat
    ShowSeat --> Seat
    Booking --> Show
    Booking "1" *-- "1..*" ShowSeat
    Booking --> Payment
```

## Why `ShowSeat` is separate from `Seat`

A physical seat belongs to a screen, but price and availability vary by show. `ShowSeat` models show-specific state.

## Booking sequence

```mermaid
sequenceDiagram
    actor User
    participant BookingService
    participant SeatLockProvider
    participant PaymentService
    participant BookingRepository

    User->>BookingService: reserve(show, seats)
    BookingService->>SeatLockProvider: lock(seats, ttl)
    BookingService-->>User: payment pending
    User->>BookingService: pay(booking)
    BookingService->>PaymentService: charge(amount)
    PaymentService-->>BookingService: success
    BookingService->>BookingRepository: confirm(booking)
```

## Concurrency

The critical problem is double booking.

Possible safeguards:

- Unique database constraint on confirmed `show_id + seat_id`
- Optimistic locking with version field
- Redis lock with TTL for temporary holds
- Database transaction during confirmation

A Redis lock alone is not the final source of truth. The database must enforce uniqueness.

## Patterns

- Strategy: pricing and payment provider.
- State: booking lifecycle.
- Adapter: external payment gateway.
- Observer: confirmation notifications.
