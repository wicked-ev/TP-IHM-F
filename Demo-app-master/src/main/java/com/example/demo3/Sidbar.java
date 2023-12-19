package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import java.io.IOException;

public class Sidbar {
    // Get a reference to the HelloApplication instance
    private final HelloApplication helloApplication = HelloApplication.getInstance();
    AnchorPane AnchorloginPane = helloApplication.loadPaneFromFXML("LoginPane.fxml");;
    Pane loginPane = helloApplication.getChildPane(AnchorloginPane,0);  ;
    AnchorPane AnchorStatPane =  helloApplication.loadPaneFromFXML("StatsPane.fxml");;
    Pane StatPane = helloApplication.getChildPane(AnchorStatPane,0);
    AnchorPane AnchorSettPane = helloApplication.loadPaneFromFXML("Settings.fxml");;
    Pane SettPane = helloApplication.getChildPane(AnchorSettPane,0);;
    @FXML
    private Button MainPomodoro_button;

    @FXML
    private Button Profile_button;

    @FXML
    private Button Stats_button;

    @FXML
    private Button settings_button;

    @FXML
    void Btn_1ME(MouseEvent event) {

    }

    @FXML
    void Btn_2ME(MouseEvent event) {

    }

    @FXML
    public void MainP_button_action(ActionEvent event) {
        if (!helloApplication.MainAnchor.getChildren().contains(helloApplication.initailPane)) {

            // Remove the initialPane from MainAnchor
            helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));

            // Add the LoginPane to MainAnchor
            AnchorPane.setRightAnchor(helloApplication.initailPane, 0.0);
            helloApplication.MainAnchor.getChildren().add(helloApplication.initailPane);
            System.out.println("new Main Pane had opened");
        }
    }

    @FXML
    void Profile_buttonAction(ActionEvent event) {
        if (!helloApplication.MainAnchor.getChildren().contains(loginPane)) {

            // Remove the initialPane from MainAnchor
            helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));

            // Add the LoginPane to MainAnchor
            AnchorPane.setRightAnchor(loginPane, 0.0);
            helloApplication.MainAnchor.getChildren().add(loginPane);
            System.out.println("new Profile Pane had opened");
        }
    }


    @FXML
    void Stats_button_action(ActionEvent event) {
        if (!helloApplication.MainAnchor.getChildren().contains(StatPane)) {

            // Remove the initialPane from MainAnchor
            helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));

            // Add the LoginPane to MainAnchor
            AnchorPane.setRightAnchor(StatPane, 0.0);
            helloApplication.MainAnchor.getChildren().add(StatPane);
            System.out.println("new Stat Pane had opened");
        }

    }
    @FXML
    void Sett_button_action(ActionEvent event) {
        if (!helloApplication.MainAnchor.getChildren().contains(SettPane)) {

            // Remove the initialPane from MainAnchor
            helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));

            // Add the LoginPane to MainAnchor
            AnchorPane.setRightAnchor(SettPane, 0.0);
            helloApplication.MainAnchor.getChildren().add(SettPane);
            System.out.println("new Settings Pane had opened");
        }
    }
    @FXML
    void StdBtnME(MouseEvent event) {

    }

    @FXML
    void btn_1Hover(MouseEvent event) {

    }

    @FXML
    void btn_2Hover(MouseEvent event) {

    }

    @FXML
    void hover(MouseEvent event) {

    }

}
