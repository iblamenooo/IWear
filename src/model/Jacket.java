package model;

import interfaces.Wearable;

public class Jacket extends Wear implements Wearable {
    private String material;
    private boolean hooded;

    public Jacket(int id, String name, double price, String material, boolean hooded) {
        super(id, name, price);
        setMaterial(material);
        setHooded(hooded);
    }

    @Override
    public String getType() { return "JACKET"; }

    @Override
    public void wear() {
        System.out.println("Wearing a " + material + " jacket" + (hooded ? " with a hood." : "."));
    }

    @Override
    public String getMaterial() { return material; }

    public boolean isHooded() { return hooded; }

    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty())
            throw new IllegalArgumentException("Material cannot be empty");
        this.material = material;
    }

    public void setHooded(boolean hooded) {
        this.hooded = hooded;
    }
}

