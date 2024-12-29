package ui.gui;

import domain.Customer;
import domain.Flower;
import domain.Orders;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.FlowerService;
import service.IOService;
import util.AlertUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlowerController {
    private Customer customer;

    @FXML
    private Label loggedInCustomer;

    @FXML
    private ListView<Flower> flowers;

    @FXML
    private ListView<String> orders;

    @FXML
    private TextField amountOfFlowers;

    @FXML
    private Label totalAmount;

    public void setCustomer(Customer customer) {
        loggedInCustomer.setText("Welcome " + customer.getLogin());
        this.customer = customer;
        showOrders();
    }

    @FXML
    void onGenerate(ActionEvent event) {
        try {
            int amount = Integer.parseInt(amountOfFlowers.getText());
            if (amount < 5 || amount > 25) {
                AlertUtil.showErrorAlert("Amount", "The number of flowers must be between 5 and 25.");
            } else {
                generateFlowers(amount);
            }
        } catch (NumberFormatException e) {
            AlertUtil.showErrorAlert("Input Error", "Please enter a valid number.");
        }
    }

    private void generateFlowers(int amount) {
        FlowerService flowerService = new FlowerService();
        List<Flower> availableFlowers = flowerService.getFlowers();
        List<Flower> selectedFlowers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            selectedFlowers.add(availableFlowers.get(random.nextInt(availableFlowers.size())));
        }
        flowers.getItems().setAll(selectedFlowers);

        totalAmount.setText(String.format("%.2f", selectedFlowers.stream().mapToDouble(Flower::getPrice).sum()) + " €");
    }

    @FXML
    void onOrder(ActionEvent event) {
        try {
            List<Flower> selectedFlowers = new ArrayList<>(flowers.getItems());
            if (selectedFlowers.isEmpty()) {
                AlertUtil.showInfoAlert("Order", "No bouquet configured to order.");
                return;
            }

            double totalPrice = calculateTotalPrice(selectedFlowers);
            Orders order = new Orders(customer.getLogin(), selectedFlowers, totalPrice);
            IOService ioService = new IOService();
            ioService.saveOrder(customer.getLogin(), order);
            showOrders();
        } catch (Exception e) {
            AlertUtil.showInfoAlert("Order", "Failed to place the order.");
        }
    }

    private void showOrders() {
        IOService ioService = new IOService();
        List<Orders> customerOrders = ioService.loadOrders(customer.getLogin());
        orders.getItems().clear();
        for (Orders order : customerOrders) {
            orders.getItems().add(order.toString());
        }
    }

    private double calculateTotalPrice(List<Flower> flowers) {
        return flowers.stream().mapToDouble(Flower::getPrice).sum();
    }

    @FXML
    void onClose(ActionEvent event) {
        loggedInCustomer.getScene().getWindow().hide();
    }
}