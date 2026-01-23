package model;

public class TShirt extends Wear {

    private String size;

    public TShirt(int id, String name, double price, String size) {
        super(id, name, price);
        setSize(size);
    }

    @Override
    public String getType() {
        return "T-Shirt";
    }

    public void setSize(String size) {
        if (size == null || size.isEmpty()) {
            throw new IllegalArgumentException("Size cannot be empty");
        }
        this.size = size;
    }
}
