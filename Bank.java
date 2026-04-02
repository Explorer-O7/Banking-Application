import java.util.*;

public class Bank {
 private final Map<Long, Account> accounts = new HashMap<>();
 private final Map<String, Customer> customersById = new HashMap<>();
 private long nextAccNo = 1000L;

 public synchronized long generateAccountNumber() {
  return nextAccNo++;
 }

 public Customer registerCustomer(String customerId, String name, String address) {
  if (customersById.containsKey(customerId)) {
   throw new IllegalArgumentException("Customer ID already exist.");
  }
  Customer customer = new Customer(customerId, name, address);
  customersById.put(customerId, customer);
  return customer;
 }

 public Customer getCustomer(String customerId) {
  return customersById.get(customerId);
 }

 public Account openSavingsAccount(Customer customer, String holderName, String pin, double initialDeposit, double interestRate) {
  long accNo = generateAccountNumber();
  Account acc = new SavingsAccount(accNo, holderName, pin, initialDeposit, interestRate);
  accounts.put(accNo, acc);
  customer.addAccount(acc);
  return acc;
 }

 public Account openCurrentAccount(Customer customer, String holderName, String pin, double initialDeposit, double overdraftLimit) {
  long accNo = generateAccountNumber();
  Account acc = new CurrentAccount(accNo, holderName, pin, initialDeposit, overdraftLimit);
  accounts.put(accNo, acc);
  customer.addAccount(acc);
  return acc;
 }

 public Account getAccount(long accNo) {
  return accounts.get(accNo);
 }

 public boolean closeAccount(long accNo, String pin) {
  Account acc = accounts.get(accNo);
  if (acc==null) return false;
  if (!acc.verifyPin(pin)) {
   throw new SecurityException("Invalid PIN.");
  }
  Customer owner = findOwner(acc);
  if (owner!=null) owner.removeAccount(accNo);
  accounts.remove(accNo);
  return true;
 }
 
 private Customer findOwner(Account account) {
    for (Customer c : customersById.values()) {
        if (c.getAccounts().stream().anyMatch(a -> a.getAccNumber() == account.getAccNumber())) {
            return c;
        }
    }
    return null;
 }

 public void transfer(long fromAccNo, String fromPin, long toAccNo, double amount) {
  if (amount<=0) throw new IllegalArgumentException("Amount must be positive.");
  Account from = accounts.get(fromAccNo);
  Account to = accounts.get(toAccNo);
  if (from==null || to==null) throw new NoSuchElementException("Account not found.");
  if (!from.verifyPin(fromPin)) throw new SecurityException("Invalid PIN.");
  from.withdraw(amount, "Transfer to " + toAccNo);
  to.deposit(amount, "Transfer from " + fromAccNo);
 }

 public Collection<Account> getAllAccounts() {
  return Collections.unmodifiableCollection(accounts.values());
 }

 public Collection<Customer> getAllCustomers() {
    return Collections.unmodifiableCollection(customersById.values());
 }
}