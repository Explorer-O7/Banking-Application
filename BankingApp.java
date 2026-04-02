import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BankingApp {
 private static final Scanner sc = new Scanner(System.in);
 private static final Bank bank = new Bank();

 public static void main(String []args) {
  System.out.println("Welcome to Java Bank!");
  boolean running = true;
  while (running) {
   printMenu();
   int choice = readInt("Enter choice: ");
   try {
    switch (choice) {
     case 1 -> registerCustomer();
     case 2 -> openAccount();
     case 3 -> deposit();
     case 4 -> withdraw();
     case 5 -> transfer();
     case 6 -> viewAccount();
     case 7 -> viewCustomer();
     case 8 -> applyInterestToSavings();
     case 9 -> closeAccount();
     case 10 -> listAll();
     case 0 -> running = false;
     default -> System.out.println("Invalid choice.");
    }
   } catch (Exception e) {
    System.out.println("Error: " + e.getMessage());
   }
   System.out.println("Goodbye!");
  }
 }

  private static void printMenu() {
   System.out.println("---------------------");
   System.out.println("1. Register Customer");
   System.out.println("2. Open Account");
   System.out.println("3. Deposit Money");
   System.out.println("4. Withdraw Money");
   System.out.println("5. Transfer Money");
   System.out.println("6. View Account Details");
   System.out.println("7. View Customer Details");
   System.out.println("8. Apply Monthly Interest (Savings)");
   System.out.println("9. Close Account");
   System.out.println("10. List All Customers & Accounts");
   System.out.println("0. Exit");
  }

  private static void registerCustomer() {
   String id = readString("Enter Customer ID: ");
   String name = readString("Enter name: ");
   String address = readString("Enter address: ");
   Customer c = bank.registerCustomer(id, name, address);
   System.out.println("Registered: " + c);
  }

  private static void openAccount() {
   String customerId = readString("Enter customer ID: ");
   Customer c = bank.getCustomer(customerId);
   if (c==null) throw new NoSuchElementException("Customer not found.");

   System.out.println("Select account type:");
   System.out.println("1. Savings");
   System.out.println("2. Current");
   int type = readInt("Choice: ");
   String holderName = readString("Account holder name: ");
   String pin = readString("Set 4-6 digit PIN: ");
   double initial = readDouble("Initial deposit: ");

   Account acc;
   if (type==1) {
    double rate = readDouble("Annual interest rate (e.g., 0.04 for 4%): ");
    acc = bank.openSavingsAccount(c, holderName, pin, initial, rate);
   } else if (type==2) {
    double od = readDouble("Overdraft limit: ");
    acc = bank.openCurrentAccount(c, holderName, pin, initial, od);
   } else {
    throw new IllegalArgumentException("Invalid account type.");
   }
   System.out.println("Account created: " + acc.getDetails());
  }

  private static void deposit() {
   long accNo = readLong("Account number: ");
   Account acc = bank.getAccount(accNo);
   if (acc == null) throw new NoSuchElementException("Account not found.");
   double amount = readDouble("Amount: ");
   acc.deposit(amount, "Cash deposit");
   System.out.println("New balance: " + String.format("%.2f", acc.getBalance()));
  }

  private static void withdraw() {
   long accNo = readLong("Account number: ");
   Account acc = bank.getAccount(accNo);
   if (acc==null) throw new NoSuchElementException("Account not found.");
   String pin = readString("PIN: ");
   if (!acc.verifyPin(pin)) throw new SecurityException("Invalid PIN.");
   double amount = readDouble("Amount: " );
   acc.withdraw(amount, "Cash withdrawal");
   System.out.println("New balance: " + String.format("%.2f", acc.getBalance()));
  }

  private static void transfer() {
   long fromNo = readLong("From account number: ");
   Account from = bank.getAccount(fromNo);
   if (from==null) throw new NoSuchElementException("From account not found.");
   String pin = readString("PIN for source account: ");
   long toNo = readLong("To account number: ");
   double amount = readDouble("Amount: ");
   bank.transfer(fromNo, pin, toNo, amount);
   System.out.println("Transfer successful.");   
  }

  private static void viewAccount() {
   long accNo = readLong("Account number: ");
   Account acc = bank.getAccount(accNo);
   if (acc==null) throw new NoSuchElementException("Account not found.");
   System.out.println(acc.getDetails());
   List<Transaction> txs = acc.getTransactions();
   if (txs.isEmpty()) {
    System.out.println("No transaction yet.");
   } else {
    System.out.println("Transactions:");
    txs.forEach(t -> System.out.println(" - " + t));
   }
   if (acc instanceof SavingsAccount sa) {
    System.out.println("Annual interest rate: " + sa.getInterestRateAnnual());
   } else if (acc instanceof CurrentAccount ca) {
    System.out.println("Overdraft limit: " + ca.getOverdraftLimit());
   }
  }

  private static void viewCustomer() {
   String id = readString("Enter customer ID: ");
   Customer c = bank.getCustomer(id);
   if (c==null) throw new NoSuchElementException("Customer not found.");
   System.out.println(c);
   if (c.getAccounts().isEmpty()) {
    System.out.println("No accounts for this customer.");
   } else {
    System.out.println("Accounts:");
    c.getAccounts().forEach(a -> System.out.println(" - " + a.getDetails()));
   }
  }

  private static void applyInterestToSavings() {
   long accNo = readLong("Savings account number: ");
   Account acc = bank.getAccount(accNo);
   if (!(acc instanceof SavingsAccount)) throw new IllegalArgumentException("Not a savings account.");
   ((SavingsAccount) acc).applyMonthlyInterest();
   System.out.println("Interest applied. New balance: " + String.format("%.2f", acc.getBalance()));
  }

  private static void closeAccount() {
   long accNo = readLong("Account number: ");
   String pin = readString("PIN: ");
   boolean ok = bank.closeAccount(accNo, pin);
   System.out.println(ok ? "Account closed." : "Account not found.");
  }

  private static void listAll() {
   System.out.println("Customers:");
   bank.getAllCustomers().forEach(c -> System.out.println(" - " + c));
   System.out.println("Accounts:");
   bank.getAllAccounts().forEach(a -> System.out.println(" - " + a.getDetails()));
  }

  private static int readInt(String prompt) {
   System.out.print(prompt);
   while (!sc.hasNextInt()) {
    sc.nextLine();
    System.out.print("Enter a valid integer: ");
   }
   int val = sc.nextInt();
   sc.nextLine();
   return val;
  }

  private static long readLong(String prompt) {
   System.out.print(prompt);
   while (!sc.hasNextLong()) {
    sc.nextLine();
    System.out.print("Enter a valid number: ");
   }
   long val = sc.nextLong();
   sc.nextLine();
   return val;
  }

  private static double readDouble(String prompt) {
   System.out.print(prompt);
   while (!sc.hasNextDouble()) {
    sc.nextLine();
    System.out.print("Enter a valid amount: ");
   }
   double val = sc.nextDouble();
   sc.nextLine();
   return val;
  }

  private static String readString(String prompt) {
   System.out.print(prompt);
   return sc.nextLine().trim();
  }
}