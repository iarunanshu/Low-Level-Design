### **Understanding the Open/Closed Principle (OCP)**

The **Open/Closed Principle (OCP)** states that **software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification**.

This means:

* **Open for Extension**: You should be able to add new functionality to the system.
* **Closed for Modification**: You should *not* have to change existing, tested code to add new features. Modifying existing code can introduce new bugs and requires re-testing.

This principle is typically achieved through **abstraction**, using techniques like **inheritance** or **composition**.

-----

### **Illustrative Example: Payment Processing System**

The transcript demonstrates OCP using a payment processing system.

#### **Bad Code Example**

Initially, a `PaymentProcessor` class might have a `processPayment` method that takes a `String paymentMethod` and uses a series of `if-else` (or `switch`) statements to determine how to process the payment based on the method (e.g., "credit card," "debit card," "PayPal").

```java
class PaymentProcessor {
    public void processPayment(String paymentMethod, double amount) {
        if (paymentMethod.equals("credit card")) {
            // Logic for credit card payment
            System.out.println("Making payment via credit card: " + amount);
        } else if (paymentMethod.equals("debit card")) {
            // Logic for debit card payment
            System.out.println("Making payment via debit card: " + amount);
        } else if (paymentMethod.equals("PayPal")) {
            // Logic for PayPal payment
            System.out.println("Making payment via PayPal: " + amount);
        } else {
            throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }
}
```

This is **bad code** because adding a new payment method (like "UPI") would require **modifying** the `processPayment` method within the `PaymentProcessor` class. This violates OCP, as a core, potentially well-tested piece of code would need to be changed.

-----

#### **Good Code Example (Applying OCP)**

To adhere to OCP, an **abstraction** is introduced, typically an **interface** or an **abstract class**, that defines a common contract for all payment methods.

1.  **`PaymentMethod` Interface**: An interface is created with a `pay(double amount)` method.

    ```java
    interface PaymentMethod {
        void pay(double amount);
    }
    ```

2.  **Concrete Payment Method Classes**: Each specific payment method (e.g., `CreditCard`, `DebitCard`, `PayPal`, `UPI`) implements the `PaymentMethod` interface and provides its own `pay` implementation.

    ```java
    class CreditCard implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.println("Making payment via credit card: " + amount);
        }
    }

    class DebitCard implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.println("Making payment via debit card: " + amount);
        }
    }

    class PayPal implements PaymentMethod {
        @Override
        public void pay(double amount) {
            System.out.println("Making payment via PayPal: " + amount);
        }
    }
    ```

3.  **Refactored `PaymentProcessor`**: The `processPayment` method in `PaymentProcessor` now accepts an object of type `PaymentMethod` (the interface) and simply calls its `pay` method. This leverages **runtime polymorphism**.

    ```java
    class PaymentProcessor {
        public void processPayment(PaymentMethod paymentMethod, double amount) {
            paymentMethod.pay(amount); // Calls the specific pay method based on the object type
        }
    }
    ```

By doing this:

* The `PaymentProcessor` class is **closed for modification**: You don't need to change its `processPayment` method when a new payment type is introduced.
* The system is **open for extension**: To add a new payment method like UPI, you simply create a new `UPI` class that implements `PaymentMethod`. No existing code needs to be touched.

This approach makes the system more flexible, easier to maintain, and less prone to errors when new features are added.