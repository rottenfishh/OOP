package ru.nsu.kolodina.pizza;

/**
 * class of order.
 */
public class Order {
    int orderId;
    String status;

    /**
     * init it.
     *
     * @param orderId order id
     */
    public Order(int orderId) {
        this.orderId = orderId;
        this.status = "ORDERED"; // ordered, taken, ready, delivering, delivered
    }

    /**
     * print status of order.
     */
    public void printStatus() {
        System.out.println(orderId + " " + status + "\n");
    }
}
