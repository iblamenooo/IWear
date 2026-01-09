public class Shirt extends ClothingItem { // Inheritance [cite: 867]
    private String fabricType;

    public Shirt(String name, double price, String size, int stock, String fabricType) {
        super(name, price, size, stock); // super() must be the FIRST line [cite: 983]
        this.fabricType = fabricType;
    }

    @Override
    public void describeItem() { // Overriding for different behavior [cite: 910, 1151]
        System.out.println("Shirt " + name + " is made of " + fabricType + ".");
    }

    @Override
    public String toString() {
        return super.toString() + " | Fabric: " + fabricType; // Reuse parent toString [cite: 1109]
    }
}