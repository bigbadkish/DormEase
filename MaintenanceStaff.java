// MaintenanceStaff.java - Demonstrates Inheritance and Polymorphism
public class MaintenanceStaff extends Person {
    public MaintenanceStaff(String name, int id) {
        super(name, id);
    }

    public void processRequest(Request request) {
        if (!request.isProcessed()) {
            request.processRequest();
        } else {
            System.out.println("✗ Request already processed.");
        }
    }

    @Override
    public void viewDashboard() {
        System.out.println("\n======== MAINTENANCE DASHBOARD ========");
        System.out.println("Staff: " + name + " | ID: " + id);
        System.out.println("Role: Process maintenance requests");
        System.out.println("========================================\n");
    }
}