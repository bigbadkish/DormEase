
// PaymentTransaction.java
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentTransaction implements Serializable {
    private double amount;
    private LocalDateTime date;
    private String description;

    public PaymentTransaction(double amount, String description) {
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.description = description;
    }

    public double getAmount() { 
        return amount; 
    }
    
    public LocalDateTime getDate() { 
        return date; 
    }
    
    public String getDescription() { 
        return description; 
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("PHP %.2f | %s | %s", amount, date.format(formatter), description);
    }
}