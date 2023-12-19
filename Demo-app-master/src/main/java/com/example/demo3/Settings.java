package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.Task;

//import static jdk.internal.agent.Agent.getText;

public class Settings {

    @FXML
    private RadioButton AS_Break;

    @FXML
    private RadioButton AS_Long_Breaks;

    @FXML
    private RadioButton AS_Pomodoro;

    @FXML
    private Button Confirm_button;

    @FXML
    private TextField Long_Break_After;

    @FXML
    private TextField Long_Break_Length;

    @FXML
    private TextField Pomodoro_Length;

    @FXML
    private TextField Short_Break_After;

    @FXML
    private TextField Short_Break_Length;

    @FXML
    void Btn_2ME(MouseEvent event) {

    }

    @FXML
    void Confirm_button_action(ActionEvent event) {
        Task.inFocusTime = Integer.parseInt(Pomodoro_Length.getText());
        Task.longBreakTime = Integer.parseInt(Long_Break_Length.getText());
        Task.shortBreakTime = Integer.parseInt(Short_Break_Length.getText());
        Task.inFocusTimes = Integer.parseInt(Long_Break_After.getText());
    }

    @FXML
    void btn_2Hover(MouseEvent event) {

    }

}
