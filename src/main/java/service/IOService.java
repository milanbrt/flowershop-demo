package service;

import domain.Orders;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOService {
    private static final Logger LOGGER = Logger.getLogger(IOService.class.getName());
    private static final String DIRECTORY = "./bouquets/";

    public void saveOrder(String login, Orders order) {
        String filename = DIRECTORY + login + "_bouquets.dat";
        List<Orders> orders = loadOrders(login); // Load existing orders
        orders.add(order); // Add the new order
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(orders);
            LOGGER.log(Level.INFO, "Order saved: {0}", filename);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to save order", e);
        }
    }

    public List<Orders> loadOrders(String login) {
        String filename = DIRECTORY + login + "_bouquets.dat";
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Orders>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "No existing orders found for {0}", login);
            return new ArrayList<>(); // Return an empty list if no file exists
        }
    }
}
