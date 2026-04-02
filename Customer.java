import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
 private final String customerId;
 private final String name;
 private final String address;
 private final List<Account> accounts = new ArrayList<>();

 public Customer(String customerId, String name, String address) {
  if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("Customer ID required.");
  this.customerId = customerId;
  this.name = name;
  this.address = address;
 }

 public String getCustomerId() {
  return customerId;
 }

 public String getName() {
  return name;
 }

 public String getAddress() {
  return address;
 }

 public void addAccount(Account account) {
  accounts.add(account);
 }

 public void removeAccount(long accNo) {
  accounts.removeIf(a -> a.getAccNumber() == accNo);
 }

 public List<Account> getAccounts() {
  return Collections.unmodifiableList(accounts);
 }
 
 @Override
 public String toString() {
  return String.format("Customer[%s] %s, %s, Accounts: %d", customerId, name, address, accounts.size());
 }
}