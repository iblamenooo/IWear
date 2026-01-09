public class Customer {
    private int customerId;
    private String name;
    private String phoneNumber;
    private int loyaltyPoints;

    public Customer(int customerId, String name, String phoneNumber, int loyaltyPoints) {
        this.customerId = customerId;
        setName(name);
        this.phoneNumber = phoneNumber;
        setLoyaltyPoints(loyaltyPoints);
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown Customer";
            System.out.println("Warning: Name cannot be empty! [cite: 59]");
        }
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        if (loyaltyPoints >= 0) {
            this.loyaltyPoints = loyaltyPoints;
        } else {
            this.loyaltyPoints = 0;
            System.out.println("Warning: Loyalty points cannot be negative!");
        }
    }

    public boolean isVIP() {
        return loyaltyPoints > 100;
    }

    @Override
    public String toString() {
        return "ID: " + customerId + " | Name: " + name + " | Phone: " + phoneNumber +
                " | Points: " + loyaltyPoints + (isVIP() ? " (VIP 🌟)" : "");
    }
}