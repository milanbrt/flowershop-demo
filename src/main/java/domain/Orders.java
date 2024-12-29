package domain;

import java.util.List;
import java.util.UUID;

public class Orders implements java.io.Serializable {
    private final UUID orderID;
    private final String customerID;
    private final List<Flower> flowers;
    private final double totalPrice;

    public Orders(String customerID, List<Flower> flowers, double totalPrice) {
        this.orderID = UUID.randomUUID();
        this.customerID = customerID;
        this.flowers = flowers;
        this.totalPrice = totalPrice;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public List<Flower> getFlowers() {
        return flowers;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Bouquet: " + orderID + " " + customerID + " " + flowers + " " + totalPrice;
    }
}
