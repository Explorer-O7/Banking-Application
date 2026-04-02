import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
 public enum Type {CREDIT, DEBIT};
 
 private final Type type;
 private final double amount;
 private final String note;
 private final LocalDateTime timestamp;

 private Transaction(Type type, double amount, String note, LocalDateTime timestamp) {
  this.type = type;
  this.amount = amount;
  this.note = note;
  this.timestamp = timestamp;
 }

 public static Transaction credit(double amount, String note, LocalDateTime when) {
  return new Transaction(Type.CREDIT, amount, note, when);
 }
 
 public static Transaction debit(double amount, String note, LocalDateTime when) {
  return new Transaction(Type.DEBIT, amount, note, when);
 }

 public Type getType() {
  return type;
 }

 public double getAmount() {
  return amount;
 }

 public String getNote() {
  return note;
 }

 public LocalDateTime getTimestamp() {
  return timestamp;
 }

 @Override
 public String toString() {
  return String.format("[%s] %s %.2f | %s", timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), type, amount, note);
 }
}