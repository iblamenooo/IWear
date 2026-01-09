public class Order {
    private int orderId;
    private String customerName;
    private double totalAmount;
    private String status;

    public Order(int orderId, String customerName, double totalAmount, String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        setTotalAmount(totalAmount);
        this.status = status;
    }

    public void setTotalAmount(double totalAmount) {
        if (totalAmount >= 0) {
            this.totalAmount = totalAmount;
        } else {
            this.totalAmount = 0;
            System.out.println("Warning: Order amount cannot be negative! [cite: 19]");
        }
    }

    public void completeOrder() {
        this.status = "Completed";
    }

    @Override
    public String toString() {
        return "Order #" + orderId + " | Customer: " + customerName +
                " | Total: " + totalAmount + " KZT | Status: " + status;
    }
}