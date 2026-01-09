public class ClothingItem {
    private String name;
    private double price;
    private String size;
    private int stock;

    public ClothingItem(String name, double price, String size, int stock) {
        setName(name);
        setPrice(price);
        this.size = size;
        setStock(stock);
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Item";
            System.out.println("Warning: Name cannot be empty!");
        }
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
            System.out.println("Warning: Price cannot be negative! Setting to 0.");
        }
    }

    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        } else {
            this.stock = 0;
            System.out.println("Warning: Stock cannot be negative!");
        }
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getSize() { return size; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return "Item: " + name + " | Price: " + price + " KZT | Size: " + size + " | Stock: " + stock;
    }
}