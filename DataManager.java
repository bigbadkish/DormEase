package DormEase;
// DataManager.java
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataManager {
    public static void saveData(HashMap<Integer, Resident> residents, 
                                HashMap<String, Room> rooms,
                                ArrayList<Request> requests) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("dormease_data.dat"))) {
            oos.writeObject(residents);
            oos.writeObject(rooms);
            oos.writeObject(requests);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static Object[] loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("dormease_data.dat"))) {
            HashMap<Integer, Resident> residents = (HashMap<Integer, Resident>) ois.readObject();
            HashMap<String, Room> rooms = (HashMap<String, Room>) ois.readObject();
            ArrayList<Request> requests = (ArrayList<Request>) ois.readObject();
            System.out.println("Data loaded successfully!");
            return new Object[]{residents, rooms, requests};
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return null;
        }
    }
}