package ru.nsu.kolodina.pizza;


public class Order {
    int orderId;
    String status;
    public Order(int orderId) {
        this.orderId = orderId;
        this.status = "ORDERED"; // ordered, taken, ready, delivering, delivered
    }
    public void printStatus() {
        System.out.println(orderId + " " + status + "\n");
    }
}
