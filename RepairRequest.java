// RepairRequest.java - Demonstrates Inheritance and Polymorphism
public class RepairRequest extends Request {
    public RepairRequest(String description, Person requester) {
        super(description, requester);
    }

    @Override
    public void processRequest() {
        System.out.println("✓ Repair request processed: " + description);
        isProcessed = true;
    }
}