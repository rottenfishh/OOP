package ru.nsu.kolodina.pizza;

/**
 * class of order.
 */
public class Order {
    int orderId;
    Status status;

    /**
     * init it.
     *
     * @param orderId order id
     */
    public Order(int orderId) {
        this.orderId = orderId;
        this.status = Status.ORDERED;
    }

    /**
     * print status of order.
     */
    public void printStatus() {
        System.out.println(orderId + " " + status.toString() + "\n");
    }

    /**
     * enum of order's status.
     */
    enum Status {
        ORDERED,
        TAKEN,
        BAKING,
        READY,
        DELIVERING,
        DELIVERED;
    }
}
