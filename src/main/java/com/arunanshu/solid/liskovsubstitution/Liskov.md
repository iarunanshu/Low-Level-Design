The transcript discusses the **Liskov Substitution Principle (LSP)**, the "L" in the SOLID principles, which is crucial for building robust and correct object-oriented systems.

-----

### **Understanding the Liskov Substitution Principle (LSP)**

The **Liskov Substitution Principle (LSP)** states that **objects of a superclass should be replaceable with objects of a subclass without altering the correctness of a program.** 🔄

In simpler terms, if you have a base class (superclass) and a derived class (subclass), you should be able to use an object of the derived class wherever an object of the base class is expected, and the program should still behave correctly without unexpected errors or incorrect behavior. Subclasses should extend the capabilities of the superclass without violating its core behavior.

-----

### **Illustrative Example 1: The Bird and Ostrich Dilemma** 🐦

A common analogy used for LSP is the "Bird-Ostrich" problem:

* Imagine a `Bird` class with a `fly()` method.
* Now, create an `Ostrich` class that extends `Bird`.

Since ostriches don't fly, an `Ostrich` object, when substituted for a `Bird` object, would either:

* Have a `fly()` method that does nothing, potentially leading to silent incorrect behavior.
* Throw an exception (e.g., "Ostrich cannot fly"), which indicates a runtime error and violates the expectation that a `Bird` can fly.

This scenario violates LSP because an `Ostrich` is not a suitable substitute for a `Bird` if the expectation is that all `Bird` objects can fly. The client code expecting a `Bird` (and its `fly` behavior) would break when an `Ostrich` is provided.

-----

### **Illustrative Example 2: File System Operations (Read-Write)** 📂

The transcript uses a more relevant software example involving file system operations.

#### **Bad Code Example**

Consider a base `File` class (or interface) that includes both `read()` and `write()` methods.

```java
class File {
    public void read() {
        System.out.println("Reading from file.");
    }

    public void write() {
        System.out.println("Writing to file.");
    }
}

class ReadOnlyFile extends File {
    // This violates LSP
    @Override
    public void write() {
        throw new UnsupportedOperationException("Cannot write to a read-only file.");
    }
}
```

If you then create a `ReadOnlyFile` class that extends `File`, the `ReadOnlyFile` is forced to inherit the `write()` method. Since a read-only file shouldn't be writable, the `write()` method in `ReadOnlyFile` might throw an `UnsupportedOperationException`.

This is **bad code** because:

* When a `ReadOnlyFile` object is treated as a `File` object (e.g., `File file = new ReadOnlyFile();`), calling `file.write()` will lead to a **runtime exception**.
* The client code expecting a `File` (and its `write` behavior) is surprised by an error when a `ReadOnlyFile` is used, thus violating LSP. The `ReadOnlyFile` is not a "substitutable" `File`.

-----

#### **Good Code Example (Applying LSP)**

To fix this, the design is refactored by separating the distinct behaviors into smaller, more specific interfaces. This adheres to LSP by ensuring that subclasses only implement behaviors that are genuinely applicable.

1.  **Separate Interfaces**: Two interfaces are created: `Readable` and `Writable`.

    ```java
    interface Readable {
        void read();
    }

    interface Writable {
        void write();
    }
    ```

2.  **Base `ReadableFile` Class**: A base class for all readable files is created, implementing only the `Readable` interface.

    ```java
    class ReadableFile implements Readable {
        @Override
        public void read() {
            System.out.println("Reading from file.");
        }
    }
    ```

3.  **Specific File Classes**:

    * **`ReadOnlyFile`**: This class can extend `ReadableFile` but does **not** implement `Writable`. It inherently only supports `read()`.

      ```java
      class ReadOnlyFile extends ReadableFile {
          // Inherits read() from ReadableFile. No write() method is present.
      }
      ```

    * **`WritableFile`**: This class extends `ReadableFile` (since writable files are typically also readable) and also implements the `Writable` interface.

      ```java
      class WritableFile extends ReadableFile implements Writable {
          @Override
          public void write() {
              System.out.println("Writing to file.");
          }
      }
      ```

By using this design:

* The `read()` method is part of `ReadableFile`, which `ReadOnlyFile` and `WritableFile` inherit.
* The `write()` method is only implemented by `WritableFile`.
* A `ReadOnlyFile` object simply **does not have** a `write()` method, preventing compilation errors at design time rather than runtime exceptions.
* Client code expecting a `Readable` object can safely accept either a `ReadOnlyFile` or a `WritableFile` because both adhere to the `Readable` contract.
* Client code expecting a `Writable` object would only accept `WritableFile`, ensuring correctness.

This refactored design ensures that objects of subclasses can always replace objects of their superclass (or interface types) without causing unexpected behavior, thus upholding the Liskov Substitution Principle. This leads to more predictable and robust software.