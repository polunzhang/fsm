# FSM Module (有限狀態機模組)

## Introduction | 介紹

This FSM module is designed to maximize the flexibility of writing behaviors for social bots while enhancing developer productivity. It provides a clean and modular approach to building finite state machines (FSM) without tying it to specific applications.

此FSM模組旨在提升開發者為社群機器人撰寫行為的靈活性，同時提高生產力。它提供了一個乾淨且模組化的方式來構建有限狀態機，而不與特定應用場景綁定。

## Features | 功能特點

- **Open-Closed Principle (OCP) Support**: The module follows the OCP principle, allowing new states, transitions, triggers, guards, and actions to be added without modifying the existing code.
- **Sub-state Machine Support**: Allows nesting of state machines within states, supporting infinite depth and complexity.
- **Plugin-based Sub-state Feature**: Sub-state machine functionality is optional and can be added via plugins, ensuring the core module stays lightweight.
- **Highly Extensible and Flexible**: Designed with patterns from Gang of Four, allowing easy customization and expansion as needed.
- **Decoupled and Separation of Concerns**: The FSM module is completely decoupled from specific business logic, allowing developers to inject their desired behavior with ease.

- **開放封閉原則（OCP）**: 此模組遵循開放封閉原則，允許在不修改既有代碼的情況下新增狀態、轉換、觸發器、守衛條件和行為。
- **子狀態機支援**: 允許狀態機內嵌狀態機，支持無限層級的深度和複雜度。
- **插件式子狀態功能**: 子狀態機功能設計為可選插件，確保核心模組保持輕量化。
- **高度擴展性與靈活性**: 採用Gang of Four設計模式，模組設計可輕鬆進行自訂和擴展。
- **解耦與分離關注點**: 模組與特定業務邏輯完全解耦，允許開發者輕鬆注入自定義行為。

## Usage | 使用方法

   ```java
   //TODO
