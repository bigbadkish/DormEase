package DormEase;
// DormManager.java
public class DormManager extends Person {
    public DormManager(String name, int id) {
        super(name, id);
    }

    public void assignRoom(Resident resident, Room room) throws RoomAlreadyAssignedException {
        if (resident.getRoom() != null) {
            throw new RoomAlreadyAssignedException("Resident " + resident.getName() + 
                " already has room " + resident.getRoom().getRoomNumber());
        }
        if (room.isOccupied()) {
            throw new RoomAlreadyAssignedException("Room " + room.getRoomNumber() + 
                " is already occupied.");
        }
        resident.setRoom(room);
        room.setOccupied(true);
        System.out.println("Assigned Room " + room.getRoomNumber() + " to " + 
            resident.getName() + ".");
    }

    public void releaseRoom(Resident resident) {
        if (resident.getRoom() != null) {
            Room room = resident.getRoom();
            room.setOccupied(false);
            resident.setRoom(null);
            System.out.println("Room " + room.getRoomNumber() + " released from " + 
                resident.getName() + ".");
        } else {
            System.out.println("No room assigned to this resident.");
        }
    }

    @Override
    public void viewDashboard() {
        System.out.println("=====================================");
        System.out.println("       Dorm Manager Dashboard        ");
        System.out.println("=====================================");
        System.out.println("Name: " + name + " | ID: " + id);
        System.out.println("Role: Manage room assignments and system overview.");
        System.out.println("=====================================");
    }
}