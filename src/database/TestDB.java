import database.DatabaseConnection;

public class TestDB {
    public static void main(String[] args) {
        System.out.println(DatabaseConnection.getConnection());
        System.out.println("CONNECTED SUCCESSFULLY!");
    }
}
