# 💎 SOLID Principles – Illustrated & Explained

A professional yet easy-to-understand walkthrough of all **five SOLID principles** with Java examples. Each principle is separated into its own folder and commit for clarity.

---

## 🧭 Project Overview
**Goal:** Learn how to design software that’s *easy to maintain, extend, and test* using SOLID design principles.

**Structure:**
```
solid-sample/
├─ srp/  → 🧩 Single Responsibility Principle
├─ ocp/  → 🧱 Open/Closed Principle
├─ lsp/  → 🦅 Liskov Substitution Principle
├─ isp/  → 🖨️ Interface Segregation Principle
└─ dip/  → ⚙️ Dependency Inversion Principle
```

---

## 1️⃣ 🧩 SRP – Single Responsibility Principle
> **One class = one reason to change.**

**Concept:** Each class should handle only one responsibility. Mixing logic (printing, saving, calculating) leads to tangled code.

**Example:**
- `Invoice` → calculates totals and taxes.
- `FileInvoiceRepository` → saves invoices.
- `ConsoleInvoicePrinter` → displays invoices.

**✅ Benefit:**
Simplifies maintenance — a change in printing doesn’t affect tax logic.

---

## 2️⃣ 🧱 OCP – Open/Closed Principle
> **Open for extension, closed for modification.**

**Concept:** You can extend behavior without editing existing classes.

**Example:**
- `Shape` defines `area()`.
- `Circle`, `Rectangle`, or `Triangle` can be added later.
- `AreaCalculator` works for all, without modification.

**✅ Benefit:**
Stable, extensible design — new features don’t break old code.

---

## 3️⃣ 🦅 LSP – Liskov Substitution Principle
> **Subclasses should behave like their base classes.**

**Concept:** A subclass must be safely replaceable anywhere its parent is expected.

**Example:**
- `Bird` is a general type.
- `Eagle` can `fly()` → implements `Flyer`.
- `Penguin` cannot fly → does **not** implement `Flyer`.

**✅ Benefit:**
Prevents broken inheritance (e.g., forcing penguins to fly).

---

## 4️⃣ 🖨️ ISP – Interface Segregation Principle
> **Small, focused interfaces are better than one big one.**

**Concept:** Don’t force classes to implement methods they don’t use.

**Example:**
- `Printer` → `print()` only.
- `ScannerDevice` → `scan()` only.
- `MultiFunctionMachine` implements both.
- `SimplePrinter` implements just `Printer`.

**✅ Benefit:**
Cleaner code, flexible combinations, no unused methods.

---

## 5️⃣ ⚙️ DIP – Dependency Inversion Principle
> **Depend on abstractions, not concrete classes.**

**Concept:** High-level modules shouldn’t depend on low-level details. Both should depend on abstractions.

**Example:**
- `MessageSender` → interface for sending messages.
- `EmailSender` / `SmsSender` → implementations.
- `NotificationService` depends on `MessageSender` (not on specific senders).

**✅ Benefit:**
You can switch message channels or mock them for tests without changing `NotificationService`.

---

## 🧩 Quick Summary Table
| 🔤 Principle | 💡 Focus | 🎯 Benefit |
|--------------|-----------|-------------|
| **SRP** | One reason to change | Simpler maintenance |
| **OCP** | Extend without modify | Safer feature growth |
| **LSP** | Valid substitutions | Reliable inheritance |
| **ISP** | Smaller interfaces | Cleaner design |
| **DIP** | Abstraction over concrete | Flexible, testable code |

---

## ▶️ How to Run
```bash
javac src/main/java/solid/**/*.java -d out
java -cp out solid.Main
```

---

## 🧠 Final Thought
Applying **SOLID** makes your architecture:
- ✅ Easier to change and extend
- ✅ Safer against regressions
- ✅ More readable and professional

> “Good software is grown, not built — SOLID is how it stays alive.”

