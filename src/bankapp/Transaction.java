package bankapp;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;

public class Transaction {

	private String type; 
	private double amount;
	private LocalDateTime timestamp;

	public Transaction(String type, double amount) {
		this.type = type;
		this.amount = amount;
		this.timestamp = LocalDateTime.now(); 
	}

	public String getType() {
		return type;
	}

	public double getAmount() {
		return amount;
	}

	public String getFormattedTimestamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return timestamp.format(formatter);
	}

	@Override
	public String toString() {
		return getFormattedTimestamp() + " - " + type + ": $" + amount;
	}
}
