
// Request.java
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Request implements Serializable {
    protected String description;
    protected String status; // "Pending", "In Progress", "Completed"
    protected Person requester;
    protected LocalDateTime submittedDate;
    protected LocalDateTime processedDate;

    public Request(String description, Person requester) {
        this.description = description;
        this.requester = requester;
        this.status = "Pending";
        this.submittedDate = LocalDateTime.now();
    }

    public abstract void processRequest();

    public String getDescription() { 
        return description; 
    }
    
    public String getStatus() { 
        return status; 
    }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    
    public Person getRequester() { 
        return requester; 
    }
    
    public LocalDateTime getSubmittedDate() { 
        return submittedDate; 
    }
    
    public void setProcessedDate() {
        this.processedDate = LocalDateTime.now();
    }

    public void viewRequestHistory() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Request Details:");
        System.out.println("  Description: " + description);
        System.out.println("  Submitted: " + submittedDate.format(formatter));
        System.out.println("  Status: " + status);
        if (processedDate != null) {
            System.out.println("  Processed: " + processedDate.format(formatter));
        }
    }
}