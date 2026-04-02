l# 🏦 Java Banking Application

![GitHub license](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/language-Java-orange)
![Version](https://img.shields.io/badge/version-1.0-green)

A simple yet robust console-based banking system built in Java. This project demonstrates core banking operations including customer management, account handling, and transaction processing.

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [How to Run](#how-to-run)
- [Usage Examples](#usage-examples)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

The Java Banking Application is designed to provide a foundational understanding of how banking systems work. It simulates real-world banking operations while keeping the codebase simple and educational. This project is ideal for:
- Computer Science students learning OOP concepts
- Developers practicing Java fundamentals
- Anyone interested in understanding banking logic and transaction management

**Why this project?**
- Demonstrates core OOP principles (inheritance, polymorphism, encapsulation)
- Implements secure PIN-based account access
- Tracks transaction history for audit purposes
- Supports multiple account types with different rules

## ✨ Features

### Customer Management
- **Register Customers:** Create new customer profiles with ID, name, and address
- **Customer Tracking:** View all registered customers and their associated accounts

### Account Operations
- **Savings Accounts:** Interest-bearing accounts with monthly interest calculation (no overdraft allowed)
- **Current Accounts:** Business accounts with configurable overdraft limits
- **Account Details:** View account balance, holder name, and account type

### Transactions
- **Deposits:** Add funds to any account (no limits enforced)
- **Withdrawals:** Secure withdrawals with PIN verification and balance/overdraft checks
- **Transfers:** Transfer funds between accounts with PIN authentication
- **Transaction History:** Full audit trail of all account transactions with timestamps

### Interest Management
- **Monthly Interest:** Automatically calculate and apply interest to savings accounts
- **Configurable Rates:** Set annual interest rates when creating savings accounts

### Security
- **PIN Protection:** 4-6 digit PIN hashing for secure account access
- **PIN Verification:** All sensitive operations require PIN authentication

## 🔧 Requirements

- **Java Version:** Java 11 or higher
- **Operating System:** Windows, macOS, or Linux
- **RAM:** Minimum 256 MB (typically less than 50 MB for this app)
- **No External Dependencies:** Uses only Java Standard Library

## 📦 Installation

### Step 1: Clone the Repository
```bash
git clone https://github.com/yourusername/Java_Banking_Application.git
cd Java_Banking_Application
```

### Step 2: Verify Java Installation
```powershell
java -version
javac -version
```
Ensure both return Java 11 or higher.

### Step 3: Compile All Files
```powershell
javac *.java
```
This generates `.class` files for all Java sources.

### Step 4: Run the Application
```powershell
java BankingApp
```

## ▶️ How to Run

**Windows (PowerShell):**
```powershell
cd 'C:\Users\YourUsername\Desktop\Java_Banking_Application'
javac *.java
java BankingApp
```

**macOS/Linux (Terminal):**
```bash
cd ~/Desktop/Java_Banking_Application
javac *.java
java BankingApp
```

## 📖 Usage Examples

### Example 1: Register a Customer & Open a Savings Account
```
Welcome to Java Bank!
---------------------
1. Register Customer
...
0. Exit

Enter choice: 1
Enter Customer ID: CUST001
Enter name: John Doe
Enter address: 123 Main St, Springfield

Registered: Customer[CUST001] John Doe, 123 Main St, Springfield, Accounts: 0

Enter choice: 2
Enter customer ID: CUST001
Select account type:
1. Savings
2. Current
Choice: 1
Account holder name: John Doe
Set 4-6 digit PIN: 1234
Initial deposit: 5000
Annual interest rate (e.g., 0.04 for 4%): 0.05

Account created: Account #1000 | Holder: John Doe | Type: SavingsAccount | Balance: 5000.00
```

### Example 2: Deposit & Withdraw Money
```
Enter choice: 3
Account number: 1000
Amount: 1000
New balance: 6000.00

Enter choice: 4
Account number: 1000
PIN: 1234
Amount: 500
New balance: 5500.00
```

### Example 3: Transfer Between Accounts
```
Enter choice: 5
From account number: 1000
PIN for source account: 1234
To account number: 1001
Amount: 1000
Transfer successful.
```

### Example 4: Apply Monthly Interest
```
Enter choice: 8
Savings account number: 1000
Interest applied. New balance: 5520.63
```

## 📁 Project Structure

```
Java_Banking_Application/
├── Account.java              # Abstract base class for all accounts
├── SavingsAccount.java       # Savings account implementation
├── CurrentAccount.java       # Current account implementation
├── Bank.java                 # Core banking logic
├── Customer.java             # Customer profile management
├── Transaction.java          # Transaction record class
├── BankingApp.java           # CLI user interface
├── README.md                 # Project documentation
├── .gitignore                # Git ignore file
└── LICENSE                   # MIT License
```

## 🏗️ Architecture Overview

```
BankingApp (User Interface)
    ↓
Bank (Business Logic)
    ├── Manages Customers
    ├── Manages Accounts
    └── Processes Transactions
        ↓
    Account (Abstract)
        ├── SavingsAccount
        └── CurrentAccount
            ├── Balance Management
            ├── Transaction Recording
            └── PIN Verification
```

## 🤝 Contributing

Contributions are welcome! To contribute to this project:

1. **Fork the Repository:** Click the "Fork" button on GitHub
2. **Create a Feature Branch:**
   ```bash
   git checkout -b feature/YourFeatureName
   ```
3. **Make Your Changes:** Implement your improvements
4. **Test Thoroughly:** Compile and run all test scenarios
5. **Commit Your Work:**
   ```bash
   git commit -m "Add: Brief description of your changes"
   ```
6. **Push to Your Fork:**
   ```bash
   git push origin feature/YourFeatureName
   ```
7. **Submit a Pull Request:** Describe your changes clearly

### Contribution Ideas
- Add account statement export to CSV
- Implement persistent storage (database integration)
- Add multi-user login system
- Enhance PIN security with bcrypt hashing
- Add unit tests using JUnit 5
- Create a GUI using JavaFX or Swing

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

You are free to use, modify, and distribute this project for educational and commercial purposes.

---

## 📞 Support

For questions or issues, please open an issue on GitHub or contact the project maintainer.

**Happy Banking! 🎉**