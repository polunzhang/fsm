
# FSM Module

- This document explains the usage of a finite state machine (FSM) in Java. The methods below demonstrate how to create and handle different components of the FSM.

![ood.png](img%2Food.png)

---

# Finite State Machine Example

- This section provides a simple example to help you quickly understand how to use the FSM module. You'll learn how to define states, add transitions, and use triggers to control the flow of your finite state machine.

![fsm_flow.png](img%2Ffsm_flow.png)

---

## 1. Handle FSM Context

This method shows how to initialize the FSM and handle the provided context.

- The generic type S represents the ID of the State, and the generic type E represents the Event. Both types can be primitive types, enums, or objects.

```java
  private void finiteStateMachineHandleContext() {
    FiniteStateMachine<S, E> finiteStateMachine = buildFiniteStateMachine();
    finiteStateMachine.handle(context);
  }
```

---

## 2. Define the Finite State Machine

This method is used to construct the FSM with its initial state, possible states, and transitions.

```java
  private FiniteStateMachine<S, E> buildFiniteStateMachine() {
    return new FiniteStateMachineBuilder<S, E>()
        // Set the initial state of the finite state machine
        .initialState(stateId)
        // Define all possible states
        .states(state1, state2, state3)
        // Define all transitions between states
        .transitions(transition1, transition2, transition3)
        .build();
  }
```

---

## 3. Define Transition

The method defines the transition between states, triggered by a specific event, guarded by conditions, and optionally executes actions.
```java
  private Transition<S, E> buildTransition() {
    return Transition.<S, E>builder()
        // The source state ID of the transition rule
        .source(sourceStateId)
        // The target state ID of the transition rule
        .target(targetStateId)
        // The event that triggers the transition
        .event(triggerEvent)
        // A guard that checks if the transition condition is met when context.getEvent() matches
        .guard(guard)
        // The action to execute when the transition is triggered (nullable)
        .action(action)
        .build();
  }
```
---

## 3. Define Trigger

The method defines the trigger for transitions between states, specifying the event that the FSM listens to, guarded by conditions, and executing a required action when the trigger is activated.

```java
  private Trigger<S, E> buildTriggers() {
        return new TriggerBuilder<S, E>()
        // The event that the FSM listens to when handling the context
        .event(event)
        // A nullable guard to validate the transition
        .guard(guard)
        // A non-null action to execute
        .action(action)
        .build();
        }
```

---

## 4. Define Guard

The method defines a guard for transitions in the FSM, which evaluates the context to determine if the transition should proceed based on specific conditions.


```java
  private Guard<S, E> buildGuard() {
        return (context) -> {

        // Use context.getPayload(Class<T>) to retrieve the assigned payload
        Optional<String> payload = context.getPayload(String.class);

        // Check if the context meets the required condition
        return true;
        };
  }
```

---

## 5. Define Context

This method creates a context that holds the event and an optional payload. This context is passed during the handling of the FSM.

```java
  private Context<S, E> buildContext() {
    return new ContextBuilder<S, E>()
        // The event associated with the context
        .withEvent(event)
        // Set any type of payload that can be accessed using context.getPayload(Class<T>) in actions or guards
        .withPayload(anyTypePayload)
        .build();
  }
```

---

## 6. Define Action

This method defines what happens when an action is triggered within a transition.

```java
  private Action<S, E> buildAction() {
    return (context) -> {

      // Use context.getPayload(Class<T>) to retrieve the assigned payload
      Optional<String> payload = context.getPayload(String.class);

      // Execute the desired action based on the context
    };
  }
```
---

## 8. Define Generic State

This method defines a generic state within the FSM.

```java
  private State<S, E> buildGenericState() {
    return new StateBuilder<S, E>()
        // The ID of the state; triggers will fire depending on this ID
        .id(currentStateId)
        // Entry action to execute when entering the state
        .entryAction(entryAction)
        // Exit action to execute when exiting the state
        .exitAction(exitAction)
        .build();
  }
```

---

## 9. Build Composite State

This method defines a composite state, which can contain sub-states and their transitions.

```java
  private State<S, E> buildCompositeState() {
    return new StateBuilder<S, E>()
        // Build the state (refer to buildGenericState())
        .id(stateId)
        .entryAction(entryAction)
        .exitAction(exitAction)

        // Set properties of the sub-state machine (refer to buildFiniteStateMachine())
        .initialState(initialStateId)
        .states(states)
        .transitions(transitions)

        // Build the composite sub-state machine
        .buildCompositeState();
  }
```
---

## Key Features

- **Support for Sub-state Machines**:
  - Supports nested state machines, allowing states to contain other sub-state machines, with unlimited depth for nesting.
  - Supports the hierarchical structure of state machines, enabling the inclusion of sub-state machines with unlimited depth.

- **Plugin-based Architecture**:
  - The functionality of sub-state machines is designed as a plugin, keeping the core module lightweight and only adding sub-state machine features when necessary.
  - The sub-state machine feature is designed as a plugin, allowing for selective inclusion, maintaining the modularity and lightweight nature of the core module.

- **Highly Extensible**:
  - The module is highly extensible, allowing developers to easily modify and extend it based on their requirements.
