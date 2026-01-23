package model;

public class Pants extends Wear {

    private String fit;
    private int length;

    public Pants(int id, String name, double price, String fit, int length) {
        super(id, name, price);
        setFit(fit);
        setLength(length);
    }

    @Override
    public String getType() {
        return "Pants";
    }

    public void setFit(String fit) {
        if (fit == null || fit.trim().isEmpty()) {
            throw new IllegalArgumentException("Fit cannot be empty");
        }
        this.fit = fit;
    }

    public void setLength(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive");
        }
        this.length = length;
    }

    public String getFit() {
        return fit;
    }

    public int getLength() {
        return length;
    }
}
