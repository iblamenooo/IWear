import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    private static ArrayList<ClothingItem> inventory = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initial Test Data [cite: 1909]
        inventory.add(new ClothingItem("Generic Scarf", 2500, "N/A", 10));
        inventory.add(new Shirt("Cotton Tee", 5000, "L", 20, "Cotton"));

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline [cite: 1930]

            switch (choice) {
                case 1: addGeneral(); break; // FIXED: Method added below
                case 2: addShirt(); break;
                case 3: viewAll(); break;
                case 4: demoPolymorphism(); break;
                case 0: running = false; break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== IWEAR MANAGEMENT SYSTEM ===");
        System.out.println("1. Add General Item");
        System.out.println("2. Add Shirt");
        System.out.println("3. View All Items");
        System.out.println("4. Describe All (Polymorphism)");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    // THE METHOD YOU WERE MISSING:
    private static void addGeneral() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        inventory.add(new ClothingItem(name, price, "M", 5));
        System.out.println("✅ General item added!");
    }

    private static void addShirt() {
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Price: "); double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Fabric: "); String fabric = scanner.nextLine();

        // Polymorphism: Adding a Shirt object to a ClothingItem list [cite: 2012]
        inventory.add(new Shirt(name, price, "L", 10, fabric));
    }

    private static void viewAll() {
        if (inventory.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        for (ClothingItem item : inventory) {
            System.out.println(item); // Calls toString() [cite: 1804]
        }
    }

    private static void demoPolymorphism() {
        System.out.println("\n--- Polymorphism Demo ---");
        for (ClothingItem item : inventory) {
            item.describeItem(); // Same call, different results [cite: 2012]
        }
    }
}