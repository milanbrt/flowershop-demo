package ui.gui;

import domain.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import service.LoginService;
import util.AlertUtil;

import javafx.event.ActionEvent;

public class LoginController {
    private final LoginService loginService = new LoginService();

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    void onLogin(ActionEvent event) {
        try {
            Customer customer = loginService.login(username.getText(), password.getText());
            AlertUtil.showInfoAlert("Login", "Login successful, welcome " + customer.getLogin());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Bouquets.fxml"));
            Parent root = loader.load();

            FlowerController flowerController = loader.getController();
            flowerController.setCustomer(customer);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Login", e.getMessage());
        }
    }

    @FXML
    void onRegister(ActionEvent event) {
        try {
            Customer customer = loginService.register(username.getText(), password.getText());
            AlertUtil.showInfoAlert("Register", "Registration successful, welcome " + customer.getLogin());
        } catch (Exception e) {
            AlertUtil.showErrorAlert("Register", e.getMessage());
        }
    }

}
