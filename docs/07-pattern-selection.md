# 7. Pattern Selection Guide

Do not begin an interview by naming patterns. First identify the pressure in the design.

| Design pressure | Possible pattern |
|---|---|
| Multiple algorithms | Strategy |
| Complex object construction | Builder |
| Implementation selected by input | Factory |
| Many subscribers need updates | Observer |
| Behavior changes by current state | State |
| Request passes through stages | Chain of Responsibility |
| Add behavior without subclassing | Decorator |
| Integrate incompatible API | Adapter |
| Simplify complex subsystem | Facade |
| Control or intercept access | Proxy |
| Queue, retry, or undo requests | Command |
| Tree of individual and grouped objects | Composite |

## Strategy vs State

Both use composition and polymorphism.

- Strategy is usually selected by the client or configuration.
- State is selected through internal transitions.
- Strategy represents how to perform work.
- State represents behavior valid in the current lifecycle state.

## Adapter vs Facade

- Adapter changes an interface for compatibility.
- Facade simplifies multiple interfaces.

## Decorator vs Proxy

Both wrap another object.

- Decorator adds responsibilities.
- Proxy controls access.

## Factory vs Builder

- Factory chooses which concrete object to create.
- Builder controls how a complex object is assembled.

## Pattern warning signs

A pattern may be unnecessary when:

- Only one implementation exists and variation is unlikely.
- The abstraction has no domain meaning.
- The design becomes harder to navigate than the original problem.
- A simple method or composition would solve it.
