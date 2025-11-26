// DormManager.java - Demonstrates Inheritance and Polymorphism
public class DormManager extends Person {
    public DormManager(String name, int id) {
        super(name, id);
    }

    public void assignRoom(Resident resident, String roomNumber) {
        if (!resident.getRoom().equals("None")) {
            System.out.println("✗ " + resident.getName() + " already has a room.");
        } else {
            resident.setRoom(roomNumber);
            System.out.println("✓ Room " + roomNumber + " assigned to " + resident.getName());
        }
    }

    public void releaseRoom(Resident resident) {
        if (resident.getRoom().equals("None")) {
            System.out.println("✗ No room assigned to this resident.");
        } else {
            String room = resident.getRoom();
            resident.setRoom("None");
            System.out.println("✓ Room " + room + " released from " + resident.getName());
        }
    }

    @Override
    public void viewDashboard() {
        System.out.println("\n========== MANAGER DASHBOARD ==========");
        System.out.println("Manager: " + name + " | ID: " + id);
        System.out.println("Role: Manage rooms and residents");
        System.out.println("========================================\n");
    }
}