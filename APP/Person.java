
// Person.java
import java.io.Serializable;

public abstract class Person implements Serializable {
    protected String name;
    protected int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract void viewDashboard();
    
    public String getName() { 
        return name; 
    }
    
    public int getId() { 
        return id; 
    }
}