package DormEase;
// DormEase.java - Main Class
import java.util.*;

public class DormEase {
    private static Scanner sc = new Scanner(System.in);
    private static HashMap<Integer, Resident> residentsMap = new HashMap<>();
    private static HashMap<String, Room> roomsMap = new HashMap<>();
    private static ArrayList<Request> requests = new ArrayList<>();
    private static Person currentUser = null;
    private static int nextResidentId = 1;

    public static void main(String[] args) {
        initializeSystem();
        displayWelcome();

        boolean running = true;
        while (running) {
            displayMainMenu();
            
            try {
                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer

                switch (choice) {
                    case 0: handleLogin(); break;
                    case 1: handleRegisterResident(); break;
                    case 2: handleViewAllResidents(); break;
                    case 3: handleAssignRoom(); break;
                    case 4: handleReleaseRoom(); break;
                    case 5: handleDepositPayment(); break;
                    case 6: handleSubmitRequest(); break;
                    case 7: handleProcessRequest(); break;
                    case 8: handleViewDashboard(); break;
                    case 9: handleViewPaymentHistory(); break;
                    case 10: handleSearchResident(); break;
                    case 11: handleViewRoomStatistics(); break;
                    case 12: handleViewRequestHistory(); break;
                    case 13: handleSaveData(); break;
                    case 14: handleLoadData(); break;
                    case 15: 
                        running = false;
                        displayGoodbye();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sc.nextLine();
            }
        }

        sc.close();
    }

    private static void initializeSystem() {
        // Preload sample rooms
        roomsMap.put("A101", new Room("A101", "Single", 5000, 1));
        roomsMap.put("A102", new Room("A102", "Single", 5000, 1));
        roomsMap.put("B201", new Room("B201", "Double", 7500, 2));
        roomsMap.put("B202", new Room("B202", "Double", 7500, 2));
        roomsMap.put("C301", new Room("C301", "Suite", 12000, 4));
    }

    private static void displayWelcome() {
        System.out.println("=====================================");
        System.out.println("         Welcome to DormEase         ");
        System.out.println("   Dormitory Management System       ");
        System.out.println("=====================================");
        System.out.println("Note: You must login to access role-specific features.");
        System.out.println("=====================================");
    }

    private static void displayMainMenu() {
        System.out.println("\n=====================================");
        System.out.println("           DormEase Menu             ");
        System.out.println("=====================================");
        System.out.println("  0. Login");
        System.out.println("  1. Register Resident");
        System.out.println("  2. View All Residents");
        System.out.println("  3. Assign Room (Manager)");
        System.out.println("  4. Release Room (Manager)");
        System.out.println("  5. Deposit Payment (Resident)");
        System.out.println("  6. Submit Maintenance Request (Resident)");
        System.out.println("  7. Process Maintenance Request (Staff)");
        System.out.println("  8. View Dashboard");
        System.out.println("  9. View Payment History (Resident)");
        System.out.println(" 10. Search Resident");
        System.out.println(" 11. View Room Statistics");
        System.out.println(" 12. View Request History");
        System.out.println(" 13. Save Data");
        System.out.println(" 14. Load Data");
        System.out.println(" 15. Exit");
        System.out.println("=====================================");
        if (currentUser != null) {
            System.out.println("Logged in as: " + currentUser.getName() + 
                " (" + currentUser.getClass().getSimpleName() + ")");
        }
        System.out.print("Enter your choice: ");
    }

    private static void handleLogin() {
        System.out.println("\n=====================================");
        System.out.println("              Login Menu             ");
        System.out.println("=====================================");
        System.out.println("  1. Dorm Manager");
        System.out.println("  2. Maintenance Staff");
        System.out.println("  3. Resident");
        System.out.println("=====================================");
        System.out.print("Select role to login: ");
        
        int roleChoice = sc.nextInt();
        sc.nextLine();
        
        String loginName = getValidStringInput("Enter Name: ");
        System.out.print("Enter ID: ");
        int loginId = sc.nextInt();
        sc.nextLine();

        if (roleChoice == 1) {
            currentUser = new DormManager(loginName, loginId);
            System.out.println("Logged in as Dorm Manager: " + loginName);
        } else if (roleChoice == 2) {
            currentUser = new MaintenanceStaff(loginName, loginId);
            System.out.println("Logged in as Maintenance Staff: " + loginName);
        } else if (roleChoice == 3) {
            Resident resident = residentsMap.get(loginId);
            if (resident != null && resident.getName().equalsIgnoreCase(loginName)) {
                currentUser = resident;
                System.out.println("Logged in as Resident: " + loginName);
            } else {
                System.out.println("Invalid credentials or resident not found.");
                currentUser = null;
            }
        } else {
            System.out.println("Invalid role choice.");
            currentUser = null;
        }
    }

    private static void handleRegisterResident() {
        System.out.println("\n=====================================");
        System.out.println("         Register Resident           ");
        System.out.println("=====================================");
        
        String rName = getValidStringInput("Enter Resident Name: ");
        Resident newResident = new Resident(rName, nextResidentId);
        residentsMap.put(nextResidentId, newResident);
        System.out.println("Resident registered successfully! ID: " + nextResidentId);
        nextResidentId++;
    }

    private static void handleViewAllResidents() {
        System.out.println("\n=====================================");
        System.out.println("         All Residents               ");
        System.out.println("=====================================");
        
        if (residentsMap.isEmpty()) {
            System.out.println("No residents registered yet.");
        } else {
            System.out.printf("%-5s %-20s %-10s %-15s%n", "ID", "Name", "Room", "Balance");
            System.out.println("-----------------------------------------------------");
            for (Resident r : residentsMap.values()) {
                System.out.printf("%-5d %-20s %-10s PHP %-10.2f%n",
                    r.getId(), r.getName(),
                    (r.getRoom() != null ? r.getRoom().getRoomNumber() : "None"),
                    r.getBalance());
            }
        }
        System.out.println("=====================================");
    }

    private static void handleAssignRoom() {
        if (!(currentUser instanceof DormManager)) {
            System.out.println("Access denied. Only Dorm Manager can assign rooms.");
            return;
        }

        System.out.println("\n=====================================");
        System.out.println("         Assign Room                 ");
        System.out.println("=====================================");
        
        if (residentsMap.isEmpty()) {
            System.out.println("Register a resident first.");
            return;
        }

        System.out.print("Enter Resident ID: ");
        int residentId = sc.nextInt();
        sc.nextLine();

        Resident resident = residentsMap.get(residentId);
        if (resident == null) {
            System.out.println("Resident not found.");
            return;
        }

        System.out.println("\nAvailable Rooms:");
        System.out.println("-----------------------------------------------------");
        int index = 0;
        List<Room> roomList = new ArrayList<>(roomsMap.values());
        for (Room room : roomList) {
            System.out.println(index + ". " + room);
            index++;
        }
        System.out.println("-----------------------------------------------------");
        
        System.out.print("Select Room Index: ");
        int roomIndex = sc.nextInt();
        sc.nextLine();

        if (roomIndex < 0 || roomIndex >= roomList.size()) {
            System.out.println("Invalid room index.");
            return;
        }

        try {
            ((DormManager) currentUser).assignRoom(resident, roomList.get(roomIndex));
        } catch (RoomAlreadyAssignedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleReleaseRoom() {
        if (!(currentUser instanceof DormManager)) {
            System.out.println("Access denied. Only Dorm Manager can release rooms.");
            return;
        }

        System.out.println("\n=====================================");
        System.out.println("         Release Room                ");
        System.out.println("=====================================");
        
        System.out.print("Enter Resident ID: ");
        int residentId = sc.nextInt();
        sc.nextLine();

        Resident resident = residentsMap.get(residentId);
        if (resident == null) {
            System.out.println("Resident not found.");
            return;
        }

        ((DormManager) currentUser).releaseRoom(resident);
    }

    private static void handleDepositPayment() {
        if (!(currentUser instanceof Resident)) {
            System.out.println("Access denied. Only Residents can deposit payments.");
            return;
        }

        System.out.println("\n=====================================");
        System.out.println("         Deposit Payment             ");
        System.out.println("=====================================");
        System.out.print("Enter Deposit Amount (PHP): ");
        double amount = sc.nextDouble();
        sc.nextLine();

        try {
            ((Resident) currentUser).deposit(amount);
        } catch (InvalidPaymentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleSubmitRequest() {
        if (!(currentUser instanceof Resident)) {
            System.out.println("Access denied. Only Residents can submit requests.");
            return;
        }

        System.out.println("\n=====================================");
        System.out.println("     Submit Maintenance Request      ");
        System.out.println("=====================================");
        System.out.print("Enter Request Type (Cleaning/Repair): ");
        String type = sc.nextLine().trim().toLowerCase();
        
        String desc = getValidStringInput("Enter Description: ");

        if (type.equals("cleaning")) {
            requests.add(new CleaningRequest(desc, (Resident) currentUser));
            System.out.println("Cleaning request submitted!");
        } else if (type.equals("repair")) {
            requests.add(new RepairRequest(desc, (Resident) currentUser));
            System.out.println("Repair request submitted!");
        } else {
            System.out.println("Invalid request type.");
        }
    }

    private static void handleProcessRequest() {
        if (!(currentUser instanceof MaintenanceStaff)) {
            System.out.println("Access denied. Only Maintenance Staff can process requests.");
            return;
        }

        System.out.println("\n=====================================");
        System.out.println("   Process Maintenance Request       ");
        System.out.println("=====================================");
        
        if (requests.isEmpty()) {
            System.out.println("No requests to process.");
            return;
        }

        System.out.println("Pending Requests:");
        System.out.println("-----------------------------------------------------");
        for (int i = 0; i < requests.size(); i++) {
            Request req = requests.get(i);
            System.out.printf("%d. [%s] %-30s (By: %s)%n", 
                i, req.getStatus(), req.getDescription(), req.getRequester().getName());
        }
        System.out.println("-----------------------------------------------------");
        
        System.out.print("Select Request Index to Process: ");
        int reqIdx = sc.nextInt();
        sc.nextLine();

        if (reqIdx < 0 || reqIdx >= requests.size()) {
            System.out.println("Invalid request index.");
        } else {
            ((MaintenanceStaff) currentUser).processRequest(requests.get(reqIdx));
        }
    }

    private static void handleViewDashboard() {
        if (currentUser == null) {
            System.out.println("Please login first to view dashboard.");
        } else {
            currentUser.viewDashboard();
        }
    }

    private static void handleViewPaymentHistory() {
        if (!(currentUser instanceof Resident)) {
            System.out.println("Access denied. Only Residents can view payment history.");
            return;
        }
        ((Resident) currentUser).viewPaymentHistory();
    }

    private static void handleSearchResident() {
        System.out.println("\n=====================================");
        System.out.println("         Search Resident             ");
        System.out.println("=====================================");
        String keyword = getValidStringInput("Enter search keyword (name): ");
        
        ArrayList<Resident> results = searchResidents(keyword);
        
        if (results.isEmpty()) {
            System.out.println("No residents found matching '" + keyword + "'");
        } else {
            System.out.println("Search Results:");
            System.out.println("-----------------------------------------------------");
            for (Resident r : results) {
                System.out.printf("ID: %-5d | Name: %-20s | Room: %s%n",
                    r.getId(), r.getName(),
                    (r.getRoom() != null ? r.getRoom().getRoomNumber() : "None"));
            }
        }
        System.out.println("=====================================");
    }

    private static void handleViewRoomStatistics() {
        System.out.println("\n=====================================");
        System.out.println("       Room Statistics               ");
        System.out.println("=====================================");
        
        int totalRooms = roomsMap.size();
        int occupiedRooms = 0;
        double totalRevenue = 0;

        for (Room room : roomsMap.values()) {
            if (room.isOccupied()) {
                occupiedRooms++;
                totalRevenue += room.getMonthlyRate();
            }
        }

        System.out.println("Total Rooms: " + totalRooms);
        System.out.println("Occupied Rooms: " + occupiedRooms);
        System.out.println("Available Rooms: " + (totalRooms - occupiedRooms));
        System.out.println("Occupancy Rate: " + String.format("%.2f%%", 
            (occupiedRooms * 100.0 / totalRooms)));
        System.out.println("Monthly Revenue: PHP " + String.format("%.2f", totalRevenue));
        System.out.println("Total Residents: " + residentsMap.size());
        System.out.println("=====================================");
    }

    private static void handleViewRequestHistory() {
        System.out.println("\n=====================================");
        System.out.println("       Request History               ");
        System.out.println("=====================================");
        
        if (requests.isEmpty()) {
            System.out.println("No requests in history.");
            return;
        }

        System.out.print("Enter request index to view details (or -1 to see all): ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index == -1) {
            for (int i = 0; i < requests.size(); i++) {
                System.out.println("\n--- Request " + i + " ---");
                requests.get(i).viewRequestHistory();
            }
        } else if (index >= 0 && index < requests.size()) {
            requests.get(index).viewRequestHistory();
        } else {
            System.out.println("Invalid request index.");
        }
        System.out.println("=====================================");
    }

    private static void handleSaveData() {
        DataManager.saveData(residentsMap, roomsMap, requests);
    }

    @SuppressWarnings("unchecked")
    private static void handleLoadData() {
        Object[] data = DataManager.loadData();
        if (data != null) {
            residentsMap = (HashMap<Integer, Resident>) data[0];
            roomsMap = (HashMap<String, Room>) data[1];
            requests = (ArrayList<Request>) data[2];
            
            // Update nextResidentId
            if (!residentsMap.isEmpty()) {
                nextResidentId = Collections.max(residentsMap.keySet()) + 1;
            }
        }
    }

    private static void displayGoodbye() {
        System.out.println("\n=====================================");
        System.out.println("     Thank you for using DormEase!   ");
        System.out.println("             Goodbye!                ");
        System.out.println("=====================================");
    }

    // ===== Helper Methods =====
    private static String getValidStringInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static ArrayList<Resident> searchResidents(String keyword) {
        ArrayList<Resident> results = new ArrayList<>();
        for (Resident r : residentsMap.values()) {
            if (r.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(r);
            }
        }
        return results;
    }
}
