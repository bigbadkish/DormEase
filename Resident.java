package DormEase;
// Resident.java
import java.util.ArrayList;

public class Resident extends Person {
    private Room room;
    private double balance;
    private ArrayList<PaymentTransaction> paymentHistory;

    public Resident(String name, int id) {
        super(name, id);
        this.balance = 0;
        this.paymentHistory = new ArrayList<>();
    }

    public void deposit(double amount) throws InvalidPaymentException {
        if (amount <= 0) {
            throw new InvalidPaymentException("Amount must be positive.");
        }
        balance += amount;
        paymentHistory.add(new PaymentTransaction(amount, "Deposit"));
        System.out.println("Added PHP " + String.format("%.2f", amount) + " to " + name + "'s balance.");
    }

    public void viewPaymentHistory() {
        System.out.println("\n=====================================");
        System.out.println("       Payment History - " + name);
        System.out.println("=====================================");
        if (paymentHistory.isEmpty()) {
            System.out.println("No payment history available.");
        } else {
            for (int i = 0; i < paymentHistory.size(); i++) {
                System.out.println((i + 1) + ". " + paymentHistory.get(i));
            }
        }
        System.out.println("Current Balance: PHP " + String.format("%.2f", balance));
        System.out.println("=====================================");
    }

    public Room getRoom() { 
        return room; 
    }
    
    public void setRoom(Room room) { 
        this.room = room; 
    }
    
    public double getBalance() { 
        return balance; 
    }

    @Override
    public void viewDashboard() {
        System.out.println("=====================================");
        System.out.println("         Resident Dashboard          ");
        System.out.println("=====================================");
        System.out.println("Name: " + name + " | ID: " + id);
        System.out.println("Room: " + (room != null ? room.getRoomNumber() + " (" + room.getType() + ")" : "None"));
        System.out.println("Balance: PHP " + String.format("%.2f", balance));
        System.out.println("Total Payments: " + paymentHistory.size());
        System.out.println("=====================================");
    }
}