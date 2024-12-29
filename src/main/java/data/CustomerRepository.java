package data;

import domain.Customer;

public interface CustomerRepository {
    Customer findCustomer(String login, String password);
    void addCustomer(String username, String password);
}
