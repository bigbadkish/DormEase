import java.util.*;

public class DormEase {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Resident> residents = new ArrayList<>();
    private static ArrayList<Request> requests = new ArrayList<>();
    private static String[] availableRooms = {"A101", "A102", "B201", "B202", "C301"};
    private static Person currentUser = null;

    // ANSI color codes for console output
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String MAGENTA = "\u001B[35m";

    public static void main(String[] args) {
        System.out.println(CYAN + "╔═══════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║      Welcome to DormEase!         ║" + RESET);
        System.out.println(CYAN + "║   Dormitory Management System     ║" + RESET);
        System.out.println(CYAN + "╚═══════════════════════════════════╝" + RESET);

        boolean running = true;
        while (running) {
            showMenu();
            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: registerResident(); break;
                    case 2: login(); break;
                    case 3: assignRoom(); break;
                    case 4: releaseRoom(); break;
                    case 5: depositPayment(); break;
                    case 6: submitRequest(); break;
                    case 7: processRequest(); break;
                    case 8: viewDashboard(); break;
                    case 9: viewAllResidents(); break;
                    case 0:
                        running = false;
                        System.out.println("\n" + GREEN + "✓ Thank you for using DormEase!" + RESET + "\n");
                        break;
                    default:
                        System.out.println(RED + "✗ Invalid choice. Try again." + RESET);
                }
            } catch (Exception e) {
                System.out.println(RED + "✗ Invalid input. Please enter a number." + RESET);
                sc.nextLine();
            }
        }
        sc.close();
    }

    private static void showMenu() {
        System.out.println("\n" + CYAN + "==========================================" + RESET);
        System.out.println(CYAN + "          WELCOME TO DORMEASE!" + RESET);
        System.out.println(CYAN + "     Dormitory Management System" + RESET);
        System.out.println(CYAN + "==========================================" + RESET);
        if (currentUser != null) {
            System.out.println(BLUE + "Logged in: " + currentUser.getName() + 
                " (" + currentUser.getClass().getSimpleName() + ")" + RESET);
        }
        System.out.println("\n" + GREEN + "Please select an option:" + RESET);
        System.out.println("  1. Register Resident");
        System.out.println("  2. Login");
        System.out.println("  3. Assign Room (Manager)");
        System.out.println("  4. Release Room (Manager)");
        System.out.println("  5. Deposit Payment (Resident)");
        System.out.println("  6. Submit Request (Resident)");
        System.out.println("  7. Process Request (Staff)");
        System.out.println("  8. View Dashboard");
        System.out.println("  9. View All Residents");
        System.out.println("  0. Exit");
        System.out.println(CYAN + "==========================================" + RESET);
        System.out.print(YELLOW + "Choice: " + RESET);
    }

    private static void registerResident() {
        System.out.print("\nEnter Resident Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println(RED + "✗ Name cannot be empty." + RESET);
            return;
        }
        Resident newResident = new Resident(name, residents.size() + 1);
        residents.add(newResident);
        System.out.println(GREEN + "✓ Resident registered! ID: " + newResident.getId() + RESET);
    }

    private static void login() {
        System.out.println("\n" + YELLOW + "--- LOGIN ---" + RESET);
        System.out.println("1. Dorm Manager");
        System.out.println("2. Maintenance Staff");
        System.out.println("3. Resident");
        System.out.print("Select role: ");
        int role = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (role == 1) {
            currentUser = new DormManager(name, id);
            System.out.println(GREEN + "✓ Logged in as Dorm Manager" + RESET);
        } else if (role == 2) {
            currentUser = new MaintenanceStaff(name, id);
            System.out.println(GREEN + "✓ Logged in as Maintenance Staff" + RESET);
        } else if (role == 3) {
            if (id > 0 && id <= residents.size()) {
                Resident r = residents.get(id - 1);
                if (r.getName().equalsIgnoreCase(name)) {
                    currentUser = r;
                    System.out.println(GREEN + "✓ Logged in as Resident" + RESET);
                } else {
                    System.out.println(RED + "✗ Invalid name for this ID." + RESET);
                }
            } else {
                System.out.println(RED + "✗ Invalid Resident ID." + RESET);
            }
        } else {
            System.out.println(RED + "✗ Invalid role." + RESET);
        }
    }

    private static void assignRoom() {
        if (!(currentUser instanceof DormManager)) {
            System.out.println(RED + "✗ Access denied. Manager only." + RESET);
            return;
        }

        if (residents.isEmpty()) {
            System.out.println(RED + "✗ No residents registered." + RESET);
            return;
        }

        System.out.print("\nEnter Resident ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (id < 1 || id > residents.size()) {
            System.out.println(RED + "✗ Invalid Resident ID." + RESET);
            return;
        }

        Resident resident = residents.get(id - 1);
        
        System.out.println("\nAvailable Rooms:");
        for (int i = 0; i < availableRooms.length; i++) {
            System.out.println(i + ". " + availableRooms[i]);
        }
        System.out.print("Select room index: ");
        int roomIdx = sc.nextInt();
        sc.nextLine();

        if (roomIdx >= 0 && roomIdx < availableRooms.length) {
            ((DormManager) currentUser).assignRoom(resident, availableRooms[roomIdx]);
        } else {
            System.out.println(RED + "✗ Invalid room index." + RESET);
        }
    }

    private static void releaseRoom() {
        if (!(currentUser instanceof DormManager)) {
            System.out.println(RED + "✗ Access denied. Manager only." + RESET);
            return;
        }

        System.out.print("\nEnter Resident ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (id < 1 || id > residents.size()) {
            System.out.println(RED + "✗ Invalid Resident ID." + RESET);
            return;
        }

        ((DormManager) currentUser).releaseRoom(residents.get(id - 1));
    }

    private static void depositPayment() {
        if (!(currentUser instanceof Resident)) {
            System.out.println(RED + "✗ Access denied. Resident only." + RESET);
            return;
        }

        System.out.print("\nEnter amount (PHP): ");
        double amount = sc.nextDouble();
        sc.nextLine();

        ((Resident) currentUser).deposit(amount);
    }

    private static void submitRequest() {
        if (!(currentUser instanceof Resident)) {
            System.out.println(RED + "✗ Access denied. Resident only." + RESET);
            return;
        }

        System.out.print("\nRequest type (cleaning/repair): ");
        String type = sc.nextLine().trim().toLowerCase();
        System.out.print("Description: ");
        String desc = sc.nextLine().trim();

        if (type.equals("cleaning")) {
            requests.add(new CleaningRequest(desc, currentUser));
            System.out.println(GREEN + "✓ Cleaning request submitted!" + RESET);
        } else if (type.equals("repair")) {
            requests.add(new RepairRequest(desc, currentUser));
            System.out.println(GREEN + "✓ Repair request submitted!" + RESET);
        } else {
            System.out.println(RED + "✗ Invalid type. Use 'cleaning' or 'repair'." + RESET);
        }
    }

    private static void processRequest() {
        if (!(currentUser instanceof MaintenanceStaff)) {
            System.out.println(RED + "✗ Access denied. Maintenance Staff only." + RESET);
            return;
        }

        if (requests.isEmpty()) {
            System.out.println(RED + "✗ No requests to process." + RESET);
            return;
        }

        System.out.println("\n" + YELLOW + "--- PENDING REQUESTS ---" + RESET);
        for (int i = 0; i < requests.size(); i++) {
            Request req = requests.get(i);
            System.out.println(i + ". [" + (req.isProcessed() ? "Done" : "Pending") + "] " + 
                req.getDescription() + " (by " + req.getRequester().getName() + ")");
        }

        System.out.print("Select request index: ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx >= 0 && idx < requests.size()) {
            ((MaintenanceStaff) currentUser).processRequest(requests.get(idx));
        } else {
            System.out.println(RED + "✗ Invalid index." + RESET);
        }
    }

    private static void viewDashboard() {
        if (currentUser == null) {
            System.out.println(RED + "✗ Please login first." + RESET);
        } else {
            currentUser.viewDashboard();
        }
    }

    private static void viewAllResidents() {
        if (residents.isEmpty()) {
            System.out.println(RED + "✗ No residents registered." + RESET);
            return;
        }

        System.out.println("\n" + CYAN + "========== ALL RESIDENTS ==========" + RESET);
        System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "Name", "Room", "Balance");
        System.out.println("-----------------------------------");
        for (Resident r : residents) {
            System.out.printf("%-5d %-20s %-10s PHP %.2f%n",
                r.getId(), r.getName(), r.getRoom(), r.getBalance());
        }
        System.out.println(CYAN + "===================================" + RESET + "\n");
    }
}
