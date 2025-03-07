package ru.nsu.kolodina.pizza;

/**
 * class of order.
 */
public class Order {
    int orderId;
    STATUS status;

    /**
     * init it.
     *
     * @param orderId order id
     */
    public Order(int orderId) {
        this.orderId = orderId;
        this.status = STATUS.ORDERED;
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
    enum STATUS{
        ORDERED,
        TAKEN,
        BAKING,
        READY,
        DELIVERING,
        DELIVERED;
    }
}
