# 5. Structural Design Patterns

Structural patterns organize classes and objects into larger structures.

## Adapter

Converts one interface into another expected by the client.

```java
final class LegacyPaymentAdapter implements PaymentGateway {
    private final LegacyPaymentClient client;

    public PaymentResult pay(Money money) {
        return client.makePayment(money.amount().doubleValue())
                ? PaymentResult.success()
                : PaymentResult.failure();
    }
}
```

Use for legacy systems and third-party SDKs.

## Decorator

Adds behavior dynamically while preserving the same interface.

```text
Notifier
  -> EmailNotifier
  -> LoggingNotifierDecorator
  -> RetryNotifierDecorator
```

Decorators are composable. Inheritance fixes behavior at compile time.

## Facade

Provides a simplified interface over a complex subsystem.

```java
class CheckoutFacade {
    void checkout(Cart cart) {
        inventory.reserve(cart);
        payment.charge(cart.total());
        shipping.schedule(cart);
    }
}
```

A facade simplifies usage but should not become a god object.

## Proxy

Controls access to another object.

Common uses:

- Authorization
- Lazy loading
- Remote calls
- Caching
- Logging

Spring AOP and transaction management frequently use proxies.

## Composite

Treats individual objects and groups uniformly.

Examples:

- File and directory trees
- Organization hierarchy
- UI component trees

## Bridge

Separates an abstraction from its implementation so both can vary independently.

Example: `Notification` abstraction and `DeliveryChannel` implementation.

## Flyweight

Shares immutable intrinsic state among many objects to reduce memory usage.

Examples:

- Character glyphs
- Game particles
- Cached metadata

## Pattern comparison

| Pattern | Purpose |
|---|---|
| Adapter | Interface compatibility |
| Decorator | Add behavior dynamically |
| Facade | Simplify a subsystem |
| Proxy | Control access |
| Composite | Uniform tree operations |
| Bridge | Separate two varying dimensions |
| Flyweight | Share memory-heavy state |
