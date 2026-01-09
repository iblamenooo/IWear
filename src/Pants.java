public class Pants extends ClothingItem {
    private String fit;

    public Pants(String name, double price, String size, int stock, String fit) {
        super(name, price, size, stock); // Call parent constructor [cite: 901]
        this.fit = fit;
    }

    @Override
    public void describeItem() { // Polymorphic behavior [cite: 922]
        System.out.println("Pants " + name + " have a " + fit + " fit.");
    }

    @Override
    public String toString() {
        return super.toString() + " | Fit: " + fit;
    }
}