
// CleaningRequest.java
public class CleaningRequest extends Request {
    public CleaningRequest(String description, Person requester) {
        super(description, requester);
    }

    @Override
    public void processRequest() {
        System.out.println("Cleaning request processed: " + description);
        setStatus("Completed");
        setProcessedDate();
    }
}