### **Understanding the Single Responsibility Principle (SRP)**

The SRP states that a class should have **only one reason to change**, meaning it should have **only one responsibility**. This promotes cleaner, more maintainable code.

### **Illustrative Example: Bad Code vs. Good Code**

The transcript uses an `Invoice` class to demonstrate the principle:

#### **Bad Code Example**
Initially, an `Invoice` class might contain methods for:
* `generateInvoice()`: Generating and printing the invoice.
* `saveToDatabase()`: Saving the invoice data to a database.
* `sendEmailNotification()`: Sending the invoice via email.

This is considered **bad code** because the `Invoice` class has multiple responsibilities:
* **Invoice generation/printing**
* **Database persistence**
* **Email notification**

If the way emails are sent changes, or the database implementation is modified, the `Invoice` class needs to be altered. This violates SRP because the class is changing for reasons unrelated to its primary purpose (being an invoice). It also makes unit testing more complex, as a change in one functionality can break tests for others.

---

#### **Good Code Example (Applying SRP)**
To adhere to SRP, the responsibilities are separated into distinct classes:
1.  **`Invoice` Class**: This class remains responsible solely for invoice-related details, such as the amount and perhaps generating/printing the invoice.
2.  **`InvoiceRepository` Class**: This new class is created to handle all database-related operations for invoices, specifically moving the `saveToDatabase()` method here.
3.  **`EmailService` Class**: This third class is responsible for all email-related functionalities, including the `sendEmailNotification()` method.



By refactoring the code in this manner, each class has a clear, single responsibility:
* The `Invoice` class manages invoice objects.
* The `InvoiceRepository` manages data storage for invoices.
* The `EmailService` handles sending email notifications.

This separation makes the code more robust, easier to understand, test, and maintain, as changes in one area (e.g., email sending) won't require modifications to unrelated classes (e.g., the `Invoice` class).