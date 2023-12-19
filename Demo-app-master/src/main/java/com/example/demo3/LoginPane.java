package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.Login;

public class LoginPane {

    @FXML
    private TextField EmailTextField;

    @FXML
    private Button Login_button;

    @FXML
    private PasswordField PassTextField;

    @FXML
    private Text SingupLinkText;

    @FXML
    private ImageView iconIMG;

    @FXML
    void Btn_2ME(MouseEvent event) {

    }

    @FXML
    void MainP_button_action(ActionEvent event) {
        String email = EmailTextField.getText();
        String password = PassTextField.getText();
// Check if email and password are not empty or blank
        if (email.isEmpty() || email.trim().isEmpty() || password.isEmpty() || password.trim().isEmpty()) {
            // Display an alert message to the user
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please enter your email and password");
            alert.showAndWait();
        } else {
            // Call the login method
            Login.loginUser(email, password);
        }

    }

    @FXML
    void btn_2Hover(MouseEvent event) {

    }

}