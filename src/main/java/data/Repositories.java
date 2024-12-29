package data;

public class Repositories {

    private static final CustomerRepository CUSTOMER_REPO = new MySqlCustomerRepository();
    private static final FlowerRepository FLOWER_REPO = new MySqlFlowerRepository();

    private Repositories() {}

    public static CustomerRepository getCustomerRepository() {
        return CUSTOMER_REPO;
    }

    public static FlowerRepository getFlowerRepository() {
        return FLOWER_REPO;
    }
}