package model;

public abstract class Wear {
    protected int id;          // maps to item_id
    protected String name;
    protected double price;

    public Wear(int id, String name, double price) {
        setId(id);
        setName(name);
        setPrice(price);
    }

    public void displayInfo() {
        System.out.println(id + " | " + getType() + " | " + name + " | " + price + " KZT");
    }

    public abstract String getType();

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // setters (Week 6: throw exceptions)
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("ID cannot be negative");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }
}
