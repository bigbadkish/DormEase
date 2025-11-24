import java.util.*;

// ===== Abstract Class for Abstraction and Inheritance =====
abstract class Person {
    protected String name;
    protected int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract void viewDashboard(); // Polymorphic method for role-specific dashboards
}

// ===== Abstract Class for Abstraction =====
abstract class Request {
    protected String description;
    protected boolean isProcessed;
    protected Person requester;

    public Request(String description, Person requester) {
        this.description = description;
        this.requester = requester;
        this.isProcessed = false;
    }

    public abstract void processRequest(); // Abstract method for processing requests

    public String getDescription() { return description; }
    public boolean isProcessed() { return isProcessed; }
    public void setProcessed(boolean processed) { this.isProcessed = processed; }
    public Person getRequester() { return requester; }
}

// ===== Concrete Request Classes (Polymorphism via processRequest) =====
class CleaningRequest extends Request {
    public CleaningRequest(String description, Person requester) {
        super(description, requester);
    }

    @Override
    public void processRequest() {
        System.out.println("Cleaning request processed: " + description);
        setProcessed(true);
    }
}

class RepairRequest extends Request {
    public RepairRequest(String description, Person requester) {
        super(description, requester);
    }

    @Override
    public void processRequest() {
        System.out.println("Repair request processed: " + description);
        setProcessed(true);
    }
}

// ===== Resident Class (Inheritance + Encapsulation) =====
class Resident extends Person {
    private Room room;
    private double balance;

    public Resident(String name, int id) {
        super(name, id);
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Added PHP " + String.format("%.2f", amount) + " to " + name + "'s balance.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
    public double getBalance() { return balance; }

    @Override
    public void viewDashboard() {
        System.out.println("=====================================");
        System.out.println("         Resident Dashboard          ");
        System.out.println("=====================================");
        System.out.println("Name: " + name + " | ID: " + id);
        System.out.println("Room: " + (room != null ? room.getRoomNumber() : "None"));
        System.out.println("Balance: PHP " + String.format("%.2f", balance));
        System.out.println("=====================================");
    }
}

// ===== MaintenanceStaff Class (Inheritance + Encapsulation) =====
class MaintenanceStaff extends Person {
    public MaintenanceStaff(String name, int id) {
        super(name, id);
    }

    public void processRequest(Request request) {
        if (!request.isProcessed()) {
            request.processRequest();
        } else {
            System.out.println("Request already processed.");
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

// ===== DormManager Class (Inheritance + Encapsulation) =====
class DormManager extends Person {
    public DormManager(String name, int id) {
        super(name, id);
    }

    public void assignRoom(Resident resident, Room room) {
        if (resident.getRoom() != null) {
            System.out.println("Resident " + resident.name + " already has a room assigned.");
        } else if (room.isOccupied()) {
            System.out.println("Room " + room.getRoomNumber() + " is already occupied.");
        } else {
            resident.setRoom(room);
            room.setOccupied(true);
            System.out.println("Assigned Room " + room.getRoomNumber() + " to " + resident.name + ".");
        }
    }

    public void releaseRoom(Resident resident) {
        if (resident.getRoom() != null) {
            Room room = resident.getRoom();
            room.setOccupied(false);
            resident.setRoom(null);
            System.out.println("Room " + room.getRoomNumber() + " released from " + resident.name + ".");
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

// ===== Room Class (Encapsulation) =====
class Room {
    private String roomNumber;
    private boolean occupied;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
        this.occupied = false;
    }

    public String getRoomNumber() { return roomNumber; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }
}

// ===== Main System Class =====
public class DormEase {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Resident> residents = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Request> requests = new ArrayList<>();
        Person currentUser = null; // Track the currently logged-in user

        // Preloaded sample rooms
        rooms.add(new Room("A101"));
        rooms.add(new Room("A102"));
        rooms.add(new Room("B201"));
        rooms.add(new Room("B202"));

        // Welcome message with simple ASCII art
        System.out.println("=====================================");
        System.out.println("         Welcome to DormEase         ");
        System.out.println("   Dormitory Management System       ");
        System.out.println("=====================================");
        System.out.println("Note: You must login to access role-specific features.");
        System.out.println("=====================================");

        boolean running = true;
        while (running) {
            System.out.println("\n=====================================");
            System.out.println("           DormEase Menu             ");
            System.out.println("=====================================");
            System.out.println("  0. Login");
            System.out.println("  1. Register Resident");
            System.out.println("  2. View All Residents");
            System.out.println("  3. Assign Room (Dorm Manager)");
            System.out.println("  4. Release Room (Dorm Manager)");
            System.out.println("  5. Deposit Payment (Resident)");
            System.out.println("  6. Submit Maintenance Request (Resident)");
            System.out.println("  7. Process Maintenance Request (Maintenance Staff)");
            System.out.println("  8. View Dashboard");
            System.out.println("  9. Exit");
            System.out.println("=====================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                switch (choice) {
                    case 0: // Login
                        System.out.println("\n=====================================");
                        System.out.println("              Login Menu             ");
                        System.out.println("=====================================");
                        System.out.println("  1. Dorm Manager");
                        System.out.println("  2. Maintenance Staff");
                        System.out.println("  3. Resident");
                        System.out.println("=====================================");
                        System.out.print("Select role to login: ");
                        int roleChoice = sc.nextInt();
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter Name: ");
                        String loginName = sc.nextLine().trim();
                        System.out.print("Enter ID: ");
                        int loginId = sc.nextInt();
                        sc.nextLine(); // clear buffer
                        if (roleChoice == 1) {
                            currentUser = new DormManager(loginName, loginId);
                            System.out.println("Logged in as Dorm Manager: " + loginName);
                        } else if (roleChoice == 2) {
                            currentUser = new MaintenanceStaff(loginName, loginId);
                            System.out.println("Logged in as Maintenance Staff: " + loginName);
                        } else if (roleChoice == 3) {
                            if (loginId < 1 || loginId > residents.size()) {
                                System.out.println("Invalid Resident ID or not registered.");
                                currentUser = null;
                            } else {
                                currentUser = residents.get(loginId - 1);
                                System.out.println("Logged in as Resident: " + loginName);
                            }
                        } else {
                            System.out.println("Invalid role choice.");
                            currentUser = null;
                        }
                        break;

                    case 1:
                        System.out.println("\n=====================================");
                        System.out.println("         Register Resident           ");
                        System.out.println("=====================================");
                        System.out.print("Enter Resident Name: ");
                        String rName = sc.nextLine().trim();
                        if (rName.isEmpty()) {
                            System.out.println("Resident name cannot be empty.");
                        } else {
                            Resident newResident = new Resident(rName, residents.size() + 1);
                            residents.add(newResident);
                            System.out.println("Resident registered successfully! ID: " + newResident.id);
                        }
                        break;

                    case 2:
                        System.out.println("\n=====================================");
                        System.out.println("         All Residents               ");
                        System.out.println("=====================================");
                        if (residents.isEmpty()) {
                            System.out.println("No residents registered yet.");
                        } else {
                            System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "Name", "Room", "Balance");
                            System.out.println("-------------------------------------");
                            for (Resident r : residents) {
                                System.out.printf("%-5d %-20s %-10s PHP %-10.2f%n",
                                        r.id, r.name,
                                        (r.getRoom() != null ? r.getRoom().getRoomNumber() : "None"),
                                        r.getBalance());
                            }
                        }
                        System.out.println("=====================================");
                        break;

                    case 3:
                        if (!(currentUser instanceof DormManager)) {
                            System.out.println("Access denied. Only Dorm Manager can assign rooms.");
                            break;
                        }
                        System.out.println("\n=====================================");
                        System.out.println("         Assign Room                 ");
                        System.out.println("=====================================");
                        if (residents.isEmpty()) {
                            System.out.println("Register a resident first.");
                            break;
                        }
                        System.out.print("Enter Resident ID: ");
                        int sid = sc.nextInt();
                        if (sid < 1 || sid > residents.size()) {
                            System.out.println("Invalid Resident ID.");
                            break;
                        }
                        System.out.println("Available Rooms:");
                        System.out.println("-------------------------------------");
                        for (int i = 0; i < rooms.size(); i++) {
                            System.out.printf("%d. %-10s %s%n", i, rooms.get(i).getRoomNumber(),
                                    (rooms.get(i).isOccupied() ? "(Occupied)" : "(Available)"));
                        }
                        System.out.println("-------------------------------------");
                        System.out.print("Select Room Index: ");
                        int rid = sc.nextInt();
                        if (rid < 0 || rid >= rooms.size()) {
                            System.out.println("Invalid Room Index.");
                        } else {
                            ((DormManager) currentUser).assignRoom(residents.get(sid - 1), rooms.get(rid));
                        }
                        break;

                    case 4:
                        if (!(currentUser instanceof DormManager)) {
                            System.out.println("Access denied. Only Dorm Manager can release rooms.");
                            break;
                        }
                        System.out.println("\n=====================================");
                        System.out.println("         Release Room                ");
                        System.out.println("=====================================");
                        if (residents.isEmpty()) {
                            System.out.println("No residents registered yet.");
                            break;
                        }
                        System.out.print("Enter Resident ID to release room: ");
                        int ridx = sc.nextInt();
                        if (ridx < 1 || ridx > residents.size()) {
                            System.out.println("Invalid Resident ID.");
                        } else {
                            ((DormManager) currentUser).releaseRoom(residents.get(ridx - 1));
                        }
                        break;

                    case 5:
                        if (!(currentUser instanceof Resident)) {
                            System.out.println("Access denied. Only Residents can deposit payments.");
                            break;
                        }
                        System.out.println("\n=====================================");
                        System.out.println("         Deposit Payment             ");
                        System.out.println("=====================================");
                        System.out.print("Enter Deposit Amount (PHP): ");
                        double amount = sc.nextDouble();
                        ((Resident) currentUser).deposit(amount);
                        break;

                    case 6:
                        if (!(currentUser instanceof Resident)) {
                            System.out.println("Access denied. Only Residents can submit requests.");
                            break;
                        }
                        System.out.println("\n=====================================");
                        System.out.println("     Submit Maintenance Request      ");
                        System.out.println("=====================================");
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter Request Type (Cleaning/Repair): ");
                        String type = sc.nextLine().trim().toLowerCase();
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine().trim();
                        if (type.equals("cleaning")) {
                            requests.add(new CleaningRequest(desc, (Resident) currentUser));
                        } else if (type.equals("repair")) {
                            requests.add(new RepairRequest(desc, (Resident) currentUser));
                        } else {
                            System.out.println("Invalid request type.");
                        }
                        System.out.println("Request submitted!");
                        break;

                    case 7:
                        if (!(currentUser instanceof MaintenanceStaff)) {
                            System.out.println("Access denied. Only Maintenance Staff can process requests.");
                            break;
                        }
                        System.out.println("\n=====================================");
                        System.out.println("   Process Maintenance Request       ");
                        System.out.println("=====================================");
                        if (requests.isEmpty()) {
                            System.out.println("No requests to process.");
                            break;
                        }
                        System.out.println("Pending Requests:");
                        System.out.println("-------------------------------------");
                        for (int i = 0; i < requests.size(); i++) {
                            Request req = requests.get(i);
                            if (!req.isProcessed()) {
                                System.out.printf("%d. %-30s (By: %s)%n", i, req.getDescription(), req.getRequester().name);
                            }
                        }
                        System.out.println("-------------------------------------");
                        System.out.print("Select Request Index to Process: ");
                        int reqIdx = sc.nextInt();
                        if (reqIdx < 0 || reqIdx >= requests.size() || requests.get(reqIdx).isProcessed()) {
                            System.out.println("Invalid or already processed request.");
                        } else {
                            ((MaintenanceStaff) currentUser).processRequest(requests.get(reqIdx));
                        }
                        break;

                    case 8:
                        if (currentUser == null) {
                            System.out.println("Please login first to view dashboard.");
                        } else {
                            currentUser.viewDashboard();
                        }
                        break;

                    case 9:
                        running = false;
                        System.out.println("\n=====================================");
                        System.out.println("     Thank you for using DormEase!   ");
                        System.out.println("             Goodbye!                ");
                        System.out.println("=====================================");
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number where required.");
                sc.nextLine(); // clear scanner buffer
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                sc.nextLine(); // clear scanner buffer
            }
        }

        sc.close();
    }
}
