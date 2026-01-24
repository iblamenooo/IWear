package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ShopMenu implements Menu {

    private final ArrayList<Wear> items;
    private final Scanner scanner;

    public ShopMenu() {
        items = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayMenu() {
        System.out.println("\n========= IWEAR SHOP =========");
        System.out.println("1. Add T-Shirt");
        System.out.println("2. Add Jacket");
        System.out.println("3. Add Pants");
        System.out.println("4. View All Items");
        System.out.println("0. Exit");
        System.out.println("==============================");
        System.out.print("Choose option: ");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addTShirt();
                    case 2 -> addJacket();
                    case 3 -> addPants();
                    case 4 -> viewAllItems();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid menu option");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Exiting IWear. Goodbye!");
        scanner.close();
    }


    private void addTShirt() {
        System.out.println("\n--- Add T-Shirt ---");

        int id = readInt("ID: ");
        String name = readString("Name: ");
        double price = readDouble("Price: ");
        String size = readString("Size: ");

        items.add(new TShirt(id, name, price, size));
        System.out.println("T-Shirt added successfully!");
    }

    private void addJacket() {
        System.out.println("\n--- Add Jacket ---");

        int id = readInt("ID: ");
        String name = readString("Name: ");
        double price = readDouble("Price: ");
        String material = readString("Material: ");
        boolean hooded = readBoolean("Hooded (true/false): ");

        items.add(new Jacket(id, name, price, material, hooded));
        System.out.println("Jacket added successfully!");
    }

    private void addPants() {
        System.out.println("\n--- Add Pants ---");

        int id = readInt("ID: ");
        String name = readString("Name: ");
        double price = readDouble("Price: ");
        String fit = readString("Fit (slim/regular/wide): ");
        int length = readInt("Length (cm): ");

        items.add(new Pants(id, name, price, fit, length));
        System.out.println("Pants added successfully!");
    }


    private void viewAllItems() {
        System.out.println("\n--- All Wear Items ---");

        if (items.isEmpty()) {
            System.out.println("No items in shop.");
            return;
        }

        for (Wear wear : items) {
            wear.displayInfo();
            System.out.println("Type: " + wear.getType());
            System.out.println("--------------------");
        }
    }


    private int readInt(String message) {
        System.out.print(message);
        return Integer.parseInt(scanner.nextLine());
    }

    private double readDouble(String message) {
        System.out.print(message);
        return Double.parseDouble(scanner.nextLine());
    }

    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private boolean readBoolean(String message) {
        System.out.print(message);
        return Boolean.parseBoolean(scanner.nextLine());
    }
}












//for (Wear wear : items) {
//        wear.displayInfo();
//            System.out.println("Type: " + wear.getType());
//        System.out.println("--------------------");
//        }




//public void run() {
//    boolean running = true;
//
//    while (running) {
//        displayMenu();
//        try {
//            int choice = Integer.parseInt(scanner.nextLine());
//
//            switch (choice) {
//                case 1 -> addTShirt();
//                case 2 -> addJacket();
//                case 3 -> addPants();
//                case 4 -> viewAllItems();
//                case 0 -> running = false;
//                default -> System.out.println("Invalid menu option");
//            }
//
//        } catch (NumberFormatException e) {
//            System.out.println("Please enter a valid number.");
//        } catch (IllegalArgumentException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }