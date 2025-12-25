

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Clothing Store Management System ===\n");
        ClothingItem item1 = new ClothingItem(1, "Jacket", "L", 45000, "Nika");
        ClothingItem item2 = new ClothingItem(2, "T-Shirt", "M", 12000, "Abibas");
        ClothingItem item3 = new ClothingItem(3, "Jeans", "XXXL", 15000, "Bershka");

        Customer customer1 = new Customer(101, "HarryGoat", "L", 80);
        Customer customer2 = new Customer(102, "Franklin", "M", 150);
        Order order1 = new Order(1001,"HarryGoat",0,"Pending");

        System.out.println("--- CLOTHING ITEMS ---");
        System.out.println(item1);
        System.out.println(item2);
        System.out.println(item3);

        System.out.println("\n--- CUSTOMERS ---");
        System.out.println(customer1);
        System.out.println(customer2);

        System.out.println("\n--- ORDER ---");
        System.out.println(order1);

        System.out.println("\n--- TESTING METHODS ---");

        item1.applyDiscount(10);
        System.out.println("After discount: "+item1);
        System.out.println("Is item1 premium? "+item1.isPremium());
        customer1.addPoints(30);
        System.out.println("Customer1 VIP: "+customer1.isVIP());

        order1.addToTotal(item1.getPrice());
        order1.addToTotal(item2.getPrice());
        order1.completeOrder();

        System.out.println("\nUpdated order:");
        System.out.println(order1);
        System.out.println("\n=== Program Complete ===");
    }
}
