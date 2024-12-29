package data;

import data.util.MySqlConnection;
import domain.Customer;
import util.SocialsException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MySqlCustomerRepository implements CustomerRepository {

    private static final Logger LOGGER = Logger.getLogger(MySqlCustomerRepository.class.getName());

    private static final String SQL_SELECT_CUSTOMER = "select * from customers where login = ?";
    private static final String SQL_INSERT_CUSTOMER = "insert into customers(login, password) values(?, ?)";

    @Override
    public Customer findCustomer(String login, String password) {
        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_SELECT_CUSTOMER)) {

            stmt.setString(1, login);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(rs.getString("login"), rs.getString("password"));
                } else {
                    return null;
                }
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to retrieve user from database.", ex);
            throw new SocialsException("Unable to retrieve login");
        }
    }

    @Override
    public void addCustomer(String login, String password) {
        if (findCustomer(login, password) != null) {
            throw new SocialsException("Customer with login '" + login + "' already exists");
        }

        try (Connection con = MySqlConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(SQL_INSERT_CUSTOMER)) {

            stmt.setString(1, login);
            stmt.setString(2, password);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Unable to add user to database.", ex);
            throw new SocialsException("Unable to add login");
        }
    }
}
