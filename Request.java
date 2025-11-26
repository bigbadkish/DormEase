// Request.java - Abstract class for Abstraction
public abstract class Request {
    protected String description;
    protected boolean isProcessed;
    protected Person requester;

    public Request(String description, Person requester) {
        this.description = description;
        this.requester = requester;
        this.isProcessed = false;
    }

    // Abstract method for Polymorphism
    public abstract void processRequest();

    public String getDescription() { return description; }
    public boolean isProcessed() { return isProcessed; }
    public Person getRequester() { return requester; }
}