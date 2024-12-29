package domain;

public class Flower implements java.io.Serializable {
    private final int id;
    private final String name;
    private final Double price;

    public Flower(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {return price;}

    @Override
    public String toString() {
        return name + " - " + price + "€";
    }
}
