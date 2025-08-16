The transcript explains the **Interface Segregation Principle (ISP)**, the "I" in the SOLID principles, which emphasizes designing focused interfaces to avoid forcing classes to depend on methods they don't use.

-----

### **Understanding the Interface Segregation Principle (ISP)**

The **Interface Segregation Principle (ISP)** states that **clients should not be forced to depend on interfaces they do not use**. 🧩

In essence, it means:

* **Break down large interfaces**: Instead of one large, "fat" interface with many methods, create multiple smaller, more specific interfaces.
* **Clients depend only on what they need**: Classes should only implement interfaces that contain methods relevant to their functionality. This prevents them from being "burdened" with methods they don't need or can't implement meaningfully.

Adhering to ISP leads to **improved maintainability, flexibility, and testability** of the codebase, as classes become more focused and have fewer, more relevant dependencies.

-----

### **Illustrative Example: Multifunctional Machine** 🖨️ Scan 📋 Copy

The transcript uses the example of a `Machine` that can print, scan, and copy.

#### **Bad Code Example**

Consider a single, large `Machine` interface with three methods: `print(Document doc)`, `scan(Document doc)`, and `copy(Document doc)`.

```java
interface Machine {
    void print(Document doc);
    void scan(Document doc);
    void copy(Document doc);
}

class SimplePrinter implements Machine {
    @Override
    public void print(Document doc) {
        System.out.println("Printing document.");
    }

    @Override
    public void scan(Document doc) {
        throw new UnsupportedOperationException("Scan not supported on SimplePrinter.");
    }

    @Override
    public void copy(Document doc) {
        throw new UnsupportedOperationException("Copy not supported on SimplePrinter.");
    }
}
```

* A `MultiPurposeMachine` would implement all three methods naturally.
* However, a `SimplePrinter` that can *only* print would also be forced to implement all three methods because it implements the `Machine` interface.
* For the `scan()` and `copy()` methods, the `SimplePrinter` would either leave them blank (creating confusing, non-functional methods) or, more typically, throw an `UnsupportedOperationException`.

This is **bad code** because:

* The `SimplePrinter` is **burdened** with methods it doesn't need or support.
* Throwing `UnsupportedOperationException` indicates a design flaw, as it means the interface is forcing an irrelevant dependency.
* It reduces flexibility; if you have a class that only scans, it would still have to implement print and copy.
* It violates ISP by forcing clients (`SimplePrinter`) to depend on methods (`scan`, `copy`) they don't use.

-----

#### **Good Code Example (Applying ISP)**

To adhere to ISP, the large `Machine` interface is broken down into smaller, more specific interfaces, each representing a single capability:

1.  **Segregated Interfaces**:

    * `Printer` interface with `print(Document doc)` method.
    * `Scanner` interface with `scan(Document doc)` method.
    * `Copier` interface with `copy(Document doc)` method.

    <!-- end list -->

    ```java
    interface Printer {
        void print(Document doc);
    }

    interface Scanner {
        void scan(Document doc);
    }

    interface Copier {
        void copy(Document doc);
    }
    ```

2.  **Specific Implementations**:

    * **`SimplePrinter`**: Now only implements the `Printer` interface. It's no longer forced to implement `scan()` or `copy()`.

      ```java
      class SimplePrinter implements Printer {
          @Override
          public void print(Document doc) {
              System.out.println("Printing document.");
          }
      }
      ```

    * **`MultiPurposeMachine`**: This machine genuinely performs all three functions, so it implements all three segregated interfaces (`Printer`, `Scanner`, `Copier`).

      ```java
      class MultiPurposeMachine implements Printer, Scanner, Copier {
          @Override
          public void print(Document doc) {
              System.out.println("Printing document.");
          }

          @Override
          public void scan(Document doc) {
              System.out.println("Scanning document.");
          }

          @Override
          public void copy(Document doc) {
              System.out.println("Copying document.");
          }
      }
      ```

This refactored design demonstrates ISP by:

* Allowing classes like `SimplePrinter` to **only depend on the interface relevant to its functionality** (i.e., `Printer`).
* Providing **flexibility**: If you need a "Scan-only machine" or a "Copy-only machine", you can easily create classes that implement only the `Scanner` or `Copier` interface, respectively, without inheriting irrelevant methods.
* Improving **maintainability** and **testability** because each interface is smaller and more focused, and classes have fewer, more meaningful dependencies.