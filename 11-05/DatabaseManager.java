public class DatabaseManager {
    private static DatabaseManager instance;

    // Private constructor prevents instantiation from other classes
    private DatabaseManager() {}

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    public void connect() { 
        System.out.println("Connected to DB successfully!"); 
    }

    public static void main(String[] args) {
        // You cannot do: DatabaseManager db = new DatabaseManager();
        // You must use getInstance():
        DatabaseManager db = DatabaseManager.getInstance();
        db.connect();
        
        DatabaseManager anotherDb = DatabaseManager.getInstance();
        System.out.println("Are both instances the same? " + (db == anotherDb));
    }
}