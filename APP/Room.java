
// Room.java
import java.io.Serializable;

public class Room implements Serializable {
    private String roomNumber;
    private String type; // "Single", "Double", "Suite"
    private double monthlyRate;
    private boolean occupied;
    private int capacity;

    public Room(String roomNumber, String type, double monthlyRate, int capacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.monthlyRate = monthlyRate;
        this.capacity = capacity;
        this.occupied = false;
    }

    public String getRoomNumber() { 
        return roomNumber; 
    }
    
    public String getType() { 
        return type; 
    }
    
    public double getMonthlyRate() { 
        return monthlyRate; 
    }
    
    public int getCapacity() { 
        return capacity; 
    }
    
    public boolean isOccupied() { 
        return occupied; 
    }
    
    public void setOccupied(boolean occupied) { 
        this.occupied = occupied; 
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-8s | PHP %.2f/mo | Cap: %d | %s",
            roomNumber, type, monthlyRate, capacity, 
            (occupied ? "Occupied" : "Available"));
    }
}