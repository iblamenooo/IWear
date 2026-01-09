import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<ClothingItem> inventory = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inventory.add(new ClothingItem("Hoodie", 15000.0, "L", 10));
        customers.add(new Customer(1001, "HarryGoat", "+777567676767", 67));

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addClothingItem(); break;
                case 2: viewInventory(); break;
                case 3: addCustomer(); break;
                case 4: viewCustomers(); break;
                case 0:
                    System.out.println("\nGoodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice!");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("   IWEAR CLOTHING STORE SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Clothing Item");
        System.out.println("2. View All Items");
        System.out.println("3. Add Customer");
        System.out.println("4. View All Customers");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addClothingItem() {
        System.out.println("\n--- ADD CLOTHING ITEM ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price (KZT): ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter size: ");
        String size = scanner.nextLine();

        System.out.print("Enter stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        ClothingItem item = new ClothingItem(name, price, size, stock);
        inventory.add(item);
        System.out.println("\n✅ Item added successfully!");
    }

    private static void viewInventory() {
        System.out.println("\n--- ALL CLOTHING ITEMS ---");
        if (inventory.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ". " + inventory.get(i));
        }
    }

    private static void addCustomer() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Points: ");
        int points = scanner.nextInt();
        scanner.nextLine();

        customers.add(new Customer(id, name, phone, points));
        System.out.println("✅ Customer added!");
    }

    private static void viewCustomers() {
        System.out.println("\n--- ALL CUSTOMERS ---");
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
}