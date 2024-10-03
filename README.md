# FSM Module (有限狀態機模組)

## Quick Start | 快速入門

This section provides a simple example to help you quickly understand how to use the FSM module. You'll see how to define states, add transitions, and use triggers to control the flow of your finite state machine.

以下提供了一個簡單的範例，幫助你快速了解如何使用這個FSM模組。你將學到如何定義狀態、增加轉移規則，以及使用觸發器來控制狀態機的流程。

### 1. Install the module | 安裝模組

安裝方法可以參考以下指令：

### 2. Initialize FSM | 初始化有限狀態機

範例展示如何設置一個基本的有限狀態機。

### 3. Key Concepts | 關鍵概念

- **States (狀態)**: 定義使用 `addState()` 來代表有限狀態機中的不同階段。
- **Transitions (轉移)**: 定義使用 `addTransition()` 來指定狀態之間的轉換規則。
- **Triggers (觸發器)**: 使用 `onTrigger()` 來定義觸發狀態轉移的條件或事件。
- **Actions (行為)**: 使用 `onAction()` 來定義進入或退出某個狀態時應該執行的操作。

這個範例展示了如何快速定義狀態機、設置狀態轉換，並使用觸發器來控制狀態的流動。您可以根據需求進行自定義，增加更多的狀態、轉移以及更複雜的邏輯。

此範例展示了如何快速定義狀態機、設置狀態轉換以及使用觸發器來控制狀態流。你可以根據需求擴展此範例，添加更多的狀態、轉移和複雜的邏輯。

---

## Introduction | 介紹

This FSM module is designed to maximize the flexibility of writing behaviors for social bots while enhancing developer productivity. It is built based on strict design principles and incorporates structural design patterns from the Gang of Four (GoF). The module follows a bottom-up approach, where design patterns are applied progressively according to specific needs and constraints.

此FSM模組旨在幫助開發者更靈活地撰寫行為，同時提升開發生產力。它基於一些嚴格的設計原則，並融合了Gang of Four設計模式中的結構型設計模式，採用bottom-up的方式設計，根據需求逐步套用合適的設計模式。

## Key Features | 主要功能

- **Open-Closed Principle (OCP)**: 
   - 模組遵循開放封閉原則 (OCP)，允許添加新狀態、轉移、觸發器、守衛以及行為，而無需修改現有程式碼。
   - 狀態、轉換、觸發器、守衛和行為的擴展可以在不修改既有程式碼的情況下完成。
  
- **Support for Sub-state Machines**: 
   - 支援狀態機嵌套，允許在狀態中包含其他子狀態機，並支援無限深度的嵌套。
   - 支援狀態機的嵌套結構，允許無限深度的子狀態機層次。
   
- **Plugin-based Architecture**:
   - 子狀態機的功能設計為插件，保持核心模組的輕量化，僅當需要時才會加入子狀態機的功能。
   - 子狀態機的功能設計為插件，可選擇性地添加，保持核心模組的輕量化與模組化。

- **Highly Extensible**: 
   - 模組具有高度的擴展性，開發者可以根據需求輕鬆地修改和擴展，同時遵循Gang of Four設計模式。
   - 模組具有高度擴展性，並使用Gang of Four的設計模式，靈活可調整。

## Components Overview | 元件概覽

This section provides an overview of each key component in the FSM module. The following components form the building blocks of the finite state machine system. Each component is explained in detail, with its responsibility and relationship to other components illustrated in class diagrams.

接下來的部分將提供FSM模組內各主要元件的概覽。這些元件構成有限狀態機系統的基礎，並將以類別圖講解每個元件的職責及其相互關係。

### 1. **State (狀態)**

   - **Description**: Represents a specific state in the FSM. Each state can have multiple transitions to other states.
   - **職責**: 狀態的主要職責是定義當前狀態的行為，並定義其與其他狀態的轉移條件。
   
### 2. **Transition (轉移)**

   - **Description**: Defines the logic for transitioning from one state to another. It can include triggers and guards.
   - **職責**: 管理狀態之間的轉換，包括觸發條件（Trigger）和守衛條件（Guard）。
   
### 3. **Trigger (觸發器)**

   - **Description**: Determines when a transition between states should be initiated.
   - **職責**: 觸發器決定何時啟動狀態間的轉換。
   
### 4. **Guard (守衛)**

   - **Description**: A condition that must be true for a transition to occur. Guards help refine the logic of state transitions.
   - **職責**: 守衛條件必須成立，才能允許狀態之間的轉換。
   
### 5. **Action (行為)**

   - **Description**: Defines what should happen when a transition occurs or when entering/exiting a state.
   - **職責**: 行為定義了在狀態轉移或進入/退出狀態時所執行的操作。
   
### 6. **Sub-state Machine (子狀態機)**

   - **Description**: A nested state machine that exists within another state, allowing for complex state hierarchies.
   - **職責**: 子狀態機是一個嵌套的狀態機，允許在主狀態內部定義更複雜的狀態層級。

## Conclusion | 結論

This FSM module offers a flexible and powerful solution for building finite state machines with extensible features, such as nested sub-state machines, and adheres to fundamental design principles like the Open-Closed Principle. Its modular design allows developers to inject specific behavior easily while maintaining a clean codebase.

此FSM模組提供了一個靈活且強大的解決方案，能構建具有擴展功能的有限狀態機（如支持嵌套的子狀態機），並遵循基本的設計原則如開放封閉原則。模組化的設計讓開發者能輕鬆注入自定義行為，同時保持乾淨的代碼結構。
