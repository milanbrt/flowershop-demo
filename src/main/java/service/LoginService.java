package service;

import data.Repositories;
import domain.Customer;
import util.Crypto;
import util.SocialsException;

import java.util.Objects;

public class LoginService {
    public Customer login(String login, String password) {
        Customer customer = Repositories.getCustomerRepository().findCustomer(login, password);

        if (Objects.nonNull(customer) && (Crypto.getInstance().checkPassword(password, customer.getPassword()))){
            return customer;
        }else {
            throw new SocialsException("Invalid username or password");
        }
    }

    public Customer register(String login, String password) {
        Customer existingCustomer = Repositories.getCustomerRepository().findCustomer(login, password);

        if (Objects.nonNull(existingCustomer)) {
            throw new SocialsException("User already exists");
        } else {
            String hashedPassword = Crypto.getInstance().hashPassword(password);

            if (!passwordMeetsComplexity(hashedPassword)) {
                throw new SocialsException("Password does not meet complexity requirements");
            }

            Repositories.getCustomerRepository().addCustomer(login, hashedPassword);
            return Repositories.getCustomerRepository().findCustomer(login, hashedPassword);
        }
    }

    private boolean passwordMeetsComplexity(String password) {
        // Very naive and silly example of password complexity requirements
        // Validates as soon as password contains '$'

        return password.contains("$");
    }

}
