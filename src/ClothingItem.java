public class ClothingItem {
    protected String name; // protected allows child access [cite: 2012]
    protected double price;
    protected String size;
    protected int stock;

    public ClothingItem(String name, double price, String size, int stock) {
        setName(name); // Use setters for validation [cite: 1760]
        setPrice(price);
        this.size = size;
        setStock(stock);
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Item";
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) this.stock = stock;
        else this.stock = 0;
    }

    // This method will be overridden (Polymorphism) [cite: 2012]
    public void describeItem() {
        System.out.println("Standard clothing item: " + name);
    }

    @Override
    public String toString() {
        return "Item: " + name + " | Price: " + price + " KZT";
    }
}