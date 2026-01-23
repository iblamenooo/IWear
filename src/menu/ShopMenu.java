package menu;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ShopMenu implements Menu {

    private ArrayList<Wear> items = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void displayMenu() {
        System.out.println("\n==== IWEAR SHOP ====");
        System.out.println("1. Add T-Shirt");
        System.out.println("2. View All Items");
        System.out.println("0. Exit");
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
                    case 2 -> viewAll();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format: " + e.getMessage());
            }
            catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    private void addTShirt() {
        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Size: ");
        String size = scanner.nextLine();

        items.add(new TShirt(id, name, price, size));
        System.out.println("T-Shirt added!");
    }

    private void viewAll() {
        for (Wear w : items) {
            w.displayInfo();
            System.out.println("Type: " + w.getType());
        }
    }
}
