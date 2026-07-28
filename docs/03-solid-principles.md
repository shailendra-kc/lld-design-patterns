# 3. SOLID Principles

## S — Single Responsibility Principle

A module should have one primary reason to change.

Bad:

```java
class InvoiceService {
    void calculate() {}
    void saveToDatabase() {}
    void sendEmail() {}
}
```

Better:

- `InvoiceCalculator`
- `InvoiceRepository`
- `InvoiceNotifier`

SRP does not mean every class should have one method. It means related responsibilities that change together should stay together.

## O — Open/Closed Principle

Software should be open for extension but closed for modification.

Bad discount logic:

```java
if (type == REGULAR) { ... }
else if (type == PREMIUM) { ... }
```

Better:

```java
public interface DiscountPolicy {
    Money discount(Order order);
}
```

New policies can be introduced without modifying existing calculation logic.

## L — Liskov Substitution Principle

A subtype must preserve the behavioral contract of its base type.

Classic violation:

```text
Square extends Rectangle
```

If callers expect width and height to change independently, `Square` breaks that expectation.

LSP covers more than method signatures:

- Do not strengthen preconditions.
- Do not weaken postconditions.
- Preserve invariants.
- Do not introduce surprising exceptions.

## I — Interface Segregation Principle

Clients should not depend on methods they do not use.

Bad:

```java
interface Machine {
    void print();
    void scan();
    void fax();
}
```

Better:

```java
interface Printer { void print(); }
interface Scanner { void scan(); }
interface Fax { void fax(); }
```

## D — Dependency Inversion Principle

High-level policy should not depend directly on low-level details. Both should depend on abstractions.

```java
public final class CheckoutService {
    private final PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
}
}
```

The high-level checkout flow does not know whether payment uses Stripe, PayPal, or a fake test implementation.

## SOLID trade-off

Blindly applying SOLID can create excessive abstractions. Introduce interfaces when there is meaningful variation, an external dependency, or a testing boundary—not merely because an interface is possible.
