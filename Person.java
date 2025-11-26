// Person.java - Abstract class for Abstraction and Inheritance
public abstract class Person {
    protected String name;
    protected int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Abstract method for Polymorphism
    public abstract void viewDashboard();
    
    public String getName() { return name; }
    public int getId() { return id; }
}