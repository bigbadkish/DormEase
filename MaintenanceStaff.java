package DormEase;
// MaintenanceStaff.java
public class MaintenanceStaff extends Person {
    public MaintenanceStaff(String name, int id) {
        super(name, id);
    }

    public void processRequest(Request request) {
        if (request.getStatus().equals("Completed")) {
            System.out.println("Request already processed.");
        } else {
            request.setStatus("In Progress");
            System.out.println("Processing request...");
            request.processRequest();
        }
    }

    @Override
    public void viewDashboard() {
        System.out.println("=====================================");
        System.out.println("    Maintenance Staff Dashboard     ");
        System.out.println("=====================================");
        System.out.println("Name: " + name + " | ID: " + id);
        System.out.println("Role: Handle maintenance requests.");
        System.out.println("=====================================");
    }
}