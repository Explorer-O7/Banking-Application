import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {
 private final long accNumber;
 private final String holderName;
 private final String pinHash;
 protected double balance;
 private final List<Transaction> transactions = new ArrayList<>();
 
 protected Account(long accNumber, String holderName, String pin, double initialDeposit) {
  if (initialDeposit < 0) throw new IllegalArgumentException("Initial deposit cannot be negative.");
  this.accNumber = accNumber;
  this.holderName = holderName;
  this.pinHash = hashPin(pin);
  this.balance = initialDeposit;
  if (initialDeposit > 0) {
   transactions.add(Transaction.credit(initialDeposit, "Initial deposit", LocalDateTime.now()));
  }
 }

 public long getAccNumber() {
  return accNumber;
 }

 public String getHolderName() {
  return holderName;
 }

 public double getBalance() {
  return balance;
 }

 public boolean verifyPin(String pin) {
  return pinHash.equals(hashPin(pin));
 }

 public void deposit(double amount, String note) {
  if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
  balance += amount;
  transactions.add(Transaction.credit(amount, note, LocalDateTime.now()));
 }

 public void withdraw(double amount, String note) {
  if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive.");
  if (!canWithdraw(amount)) throw new IllegalStateException("Insufficient funds or overdraft limit exceeded.");
  balance -= amount;
  transactions.add(Transaction.debit(amount, note, LocalDateTime.now()));
 }

 protected abstract boolean canWithdraw(double amount);
 public List<Transaction> getTransactions() {
  return Collections.unmodifiableList(transactions);
 }

 public String getDetails() {
  return String.format("Account #%d | Holder: %s | Type: %s | Balance: %.2f", accNumber, holderName, this.getClass().getSimpleName(), balance);
 }
 
 private String hashPin(String pin) {
  // Simple, deterministic hash for demo purposes. Do not use in production
  return Integer.toHexString(("salt:" + pin).hashCode());
 }
}