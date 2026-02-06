package model;

public class Pants extends Wear {
    private String fit;
    private int lengthCm;

    public Pants(int id, String name, double price, String fit, int lengthCm) {
        super(id, name, price);
        setFit(fit);
        setLengthCm(lengthCm);
    }

    @Override
    public String getType() { return "PANTS"; }

    public String getFit() { return fit; }
    public int getLengthCm() { return lengthCm; }

    public void setFit(String fit) {
        if (fit == null || fit.trim().isEmpty())
            throw new IllegalArgumentException("Fit cannot be empty");
        this.fit = fit;
    }

    public void setLengthCm(int lengthCm) {
        if (lengthCm <= 0)
            throw new IllegalArgumentException("Length must be positive");
        this.lengthCm = lengthCm;
    }
}
