public class SavingsAccount extends Account {
 private final double interestRateAnnual;
 
 public SavingsAccount(long accNumber, String holderName, String pin, double initialDeposit, double interestRateAnnual) {
  super(accNumber, holderName, pin, initialDeposit);
  if (interestRateAnnual < 0) throw new IllegalArgumentException("Interest rate cannot be negative.");
  this.interestRateAnnual = interestRateAnnual;
 }

 public double getInterestRateAnnual() {
  return interestRateAnnual;
 }

 public void applyMonthlyInterest() {
  double monthlyRate = interestRateAnnual/12.0;
  double interest = getBalance()*monthlyRate;
  if (interest > 0) {
   deposit(interest, "Monthly interest");
  }
 }
 
 @Override
 protected boolean canWithdraw(double amount) {
  return getBalance() >= amount;
 }
}