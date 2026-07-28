# 4. Creational Design Patterns

Creational patterns control object construction and hide complex creation logic.

## Factory Method

### Intent

Delegate object creation to a factory or subclass while returning a common abstraction.

### Use when

- The caller should not know the concrete implementation.
- The implementation depends on input or configuration.
- New implementations may be added.

```java
interface NotificationSender {
    void send(String message);
}

final class NotificationFactory {
    NotificationSender create(Channel channel) {
        return switch (channel) {
            case EMAIL -> new EmailSender();
            case SMS -> new SmsSender();
            case PUSH -> new PushSender();
        };
    }
}
```

### Interview discussion

A simple factory centralizes `new` decisions. Factory Method and Abstract Factory are formal GoF patterns with different extensibility mechanisms.

## Abstract Factory

Creates families of related objects.

Example: a cloud factory creates a compatible `StorageClient`, `QueueClient`, and `DatabaseClient` for AWS or Azure.

Use it when product families must not be mixed.

## Builder

### Intent

Construct a complex object step by step.

Useful when:

- Many optional fields exist.
- Constructor parameters are hard to read.
- The final object should be immutable.

```java
User user = User.builder()
        .name("Asha")
        .email("asha@example.com")
        .active(true)
        .build();
```

Builder does not automatically validate the object. Validation should occur in `build()` or the constructor.

## Singleton

Ensures one instance per class loader and provides global access.

Preferred Java implementation:

```java
enum ApplicationRegistry {
    INSTANCE;
}
```

Use cautiously. Singleton introduces hidden global state and can reduce testability. Dependency injection containers already manage scopes.

## Prototype

Creates objects by cloning an existing prototype.

Useful when construction is expensive and object structure is similar. Prefer explicit copy constructors over Java's fragile `Cloneable` mechanism.

## Pattern comparison

| Pattern | Main problem |
|---|---|
| Factory | Choose a concrete implementation |
| Abstract Factory | Create compatible object families |
| Builder | Construct a complex object clearly |
| Singleton | Control instance count |
| Prototype | Copy configured objects |
