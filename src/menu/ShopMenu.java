package menu;

import database.WearItemDAO;
import model.*;

import java.util.List;
import java.util.Scanner;

public class ShopMenu implements Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final WearItemDAO dao = new WearItemDAO();

    @Override
    public void displayMenu() {
        System.out.println("\n========== IWEAR (ASSIGNMENT 4) ==========");
        System.out.println("1. Add T-Shirt");
        System.out.println("2. Add Jacket");
        System.out.println("3. Add Pants");
        System.out.println("4. View All Items");
        System.out.println("5. View T-Shirts Only");
        System.out.println("6. View Jackets Only");
        System.out.println("7. View Pants Only");
        System.out.println("8. Update Item");
        System.out.println("9. Delete Item");
        System.out.println("10. Search by Name");
        System.out.println("11. Search by Price Range");
        System.out.println("12. High-Priced Items (price >= X)");
        System.out.println("13. Polymorphism Demo (call displayInfo)");
        System.out.println("0. Exit");
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
                    case 4 -> printList(dao.getAllItems());
                    case 5 -> printList(dao.getByType("TSHIRT"));
                    case 6 -> printList(dao.getByType("JACKET"));
                    case 7 -> printList(dao.getByType("PANTS"));
                    case 8 -> updateItem();
                    case 9 -> deleteItemSafe();
                    case 10 -> searchByName();
                    case 11 -> searchByPriceRange();
                    case 12 -> searchByMinPrice();
                    case 13 -> polymorphismDemo();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
        System.out.println("Bye!");
    }

    // -------- CREATE ----------
    private void addTShirt() {
        System.out.println("\n--- Add T-Shirt ---");
        String name = readString("Name: ");
        double price = readDouble("Price: ");
        String size = readString("Size: ");

        boolean ok = dao.insertTShirt(new TShirt(0, name, price, size));
        System.out.println(ok ? "✅ Inserted!" : "❌ Insert failed!");
    }

    private void addJacket() {
        System.out.println("\n--- Add Jacket ---");
        String name = readString("Name: ");
        double price = readDouble("Price: ");
        String material = readString("Material: ");
        boolean hooded = readBoolean("Hooded (true/false): ");

        boolean ok = dao.insertJacket(new Jacket(0, name, price, material, hooded));
        System.out.println(ok ? "✅ Inserted!" : "❌ Insert failed!");
    }

    private void addPants() {
        System.out.println("\n--- Add Pants ---");
        String name = readString("Name: ");
        double price = readDouble("Price: ");
        String fit = readString("Fit (slim/regular/wide): ");
        int length = readInt("Length (cm): ");

        boolean ok = dao.insertPants(new Pants(0, name, price, fit, length));
        System.out.println(ok ? "✅ Inserted!" : "❌ Insert failed!");
    }

    // -------- UPDATE ----------
    private void updateItem() {
        int id = readInt("Enter item_id to update: ");

        Wear existing = dao.getItemById(id);
        if (existing == null) {
            System.out.println("❌ No item with ID: " + id);
            return;
        }

        System.out.println("Current:");
        existing.displayInfo();

        String newName = readStringAllowEmpty("New name [" + existing.getName() + "]: ");
        String priceStr = readStringAllowEmpty("New price [" + existing.getPrice() + "]: ");

        String finalName = newName.trim().isEmpty() ? existing.getName() : newName;
        double finalPrice = priceStr.trim().isEmpty() ? existing.getPrice() : Double.parseDouble(priceStr);

        boolean ok = false;

        if (existing instanceof TShirt tshirt) {
            String size = readStringAllowEmpty("New size [" + tshirt.getSize() + "]: ");
            String finalSize = size.trim().isEmpty() ? tshirt.getSize() : size;
            ok = dao.updateTShirt(new TShirt(id, finalName, finalPrice, finalSize));

        } else if (existing instanceof Jacket jacket) {
            String mat = readStringAllowEmpty("New material [" + jacket.getMaterial() + "]: ");
            String hood = readStringAllowEmpty("New hooded (true/false) [" + jacket.isHooded() + "]: ");

            String finalMat = mat.trim().isEmpty() ? jacket.getMaterial() : mat;
            boolean finalHood = hood.trim().isEmpty() ? jacket.isHooded() : Boolean.parseBoolean(hood);

            ok = dao.updateJacket(new Jacket(id, finalName, finalPrice, finalMat, finalHood));

        } else if (existing instanceof Pants pants) {
            String fit = readStringAllowEmpty("New fit [" + pants.getFit() + "]: ");
            String len = readStringAllowEmpty("New length [" + pants.getLengthCm() + "]: ");

            String finalFit = fit.trim().isEmpty() ? pants.getFit() : fit;
            int finalLen = len.trim().isEmpty() ? pants.getLengthCm() : Integer.parseInt(len);

            ok = dao.updatePants(new Pants(id, finalName, finalPrice, finalFit, finalLen));
        }

        System.out.println(ok ? "✅ Updated!" : "❌ Update failed!");
    }

    // -------- DELETE (safe confirm) ----------
    private void deleteItemSafe() {
        int id = readInt("Enter item_id to delete: ");
        Wear existing = dao.getItemById(id);

        if (existing == null) {
            System.out.println("❌ No item with ID: " + id);
            return;
        }

        System.out.println("Item to delete:");
        existing.displayInfo();

        String confirm = readString("Are you sure? (yes/no): ");
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("❌ Deletion cancelled.");
            return;
        }

        boolean ok = dao.deleteItem(id);
        System.out.println(ok ? "✅ Deleted!" : "❌ Delete failed!");
    }

    // -------- SEARCH ----------
    private void searchByName() {
        String q = readString("Name contains: ");
        printList(dao.searchByName(q));
    }

    private void searchByPriceRange() {
        double min = readDouble("Min price: ");
        double max = readDouble("Max price: ");
        printList(dao.searchByPriceRange(min, max));
    }

    private void searchByMinPrice() {
        double min = readDouble("Min price (>=): ");
        printList(dao.searchByMinPrice(min));
    }

    // -------- DEMO ----------
    private void polymorphismDemo() {
        List<Wear> list = dao.getAllItems();
        if (list.isEmpty()) {
            System.out.println("No items.");
            return;
        }
        System.out.println("\n--- Polymorphism Demo ---");
        for (Wear w : list) {
            w.displayInfo(); // calls abstract polymorphic type via getType()
        }
    }

    // -------- helpers ----------
    private void printList(List<Wear> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No results.");
            return;
        }
        for (Wear w : list) {
            if (w != null) w.displayInfo();
        }
    }

    private String readString(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private String readStringAllowEmpty(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    private int readInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(scanner.nextLine());
    }

    private double readDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(scanner.nextLine());
    }

    private boolean readBoolean(String msg) {
        System.out.print(msg);
        return Boolean.parseBoolean(scanner.nextLine());
    }
}













