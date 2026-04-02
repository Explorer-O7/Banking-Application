public class CurrentAccount extends Account {
 private final double overdraftLimit;

 public CurrentAccount(long accNumber, String holderName, String pin, double initialDeposit, double overdraftLimit) {
  super(accNumber, holderName, pin, initialDeposit);
  if (overdraftLimit < 0) throw new IllegalArgumentException("Overdraft limit cannot be negative.");
  this.overdraftLimit = overdraftLimit;
 }

 public double getOverdraftLimit() {
  return overdraftLimit;
 }
 
 @Override
 protected boolean canWithdraw(double amount) {
  return (getBalance()-amount) >= -overdraftLimit;
 }
}