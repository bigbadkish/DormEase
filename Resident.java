// Resident.java - Demonstrates Inheritance, Polymorphism, and Encapsulation
public class Resident extends Person {
    private String room;  // Encapsulation: private field
    private double balance;

    public Resident(String name, int id) {
        super(name, id);
        this.balance = 0;
        this.room = "None";
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✓ Added PHP " + String.format("%.2f", amount) + " to balance.");
        } else {
            System.out.println("✗ Amount must be positive.");
        }
    }

    // Encapsulation: getters and setters
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
    public double getBalance() { return balance; }

    @Override
    public void viewDashboard() {
        System.out.println("\n========== RESIDENT DASHBOARD ==========");
        System.out.println("Name: " + name + " | ID: " + id);
        System.out.println("Room: " + room);
        System.out.println("Balance: PHP " + String.format("%.2f", balance));
        System.out.println("========================================\n");
    }
}