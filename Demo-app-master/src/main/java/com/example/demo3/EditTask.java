package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.Task;

import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class EditTask {
    private final HelloApplication helloApplication = HelloApplication.getInstance();
    int id;
    int pressed = 0;
    @FXML
    private Button Confirm_button;

    @FXML
    private Button Delete_button1;

    @FXML
    private DatePicker DueDate2;

    @FXML
    private Button Exist_button2;

    @FXML
    private Text Pomodoro_Quntity2;

    @FXML
    private Text Pomodoro_Quntity21;

    @FXML
    private TextField TasksDuration2;

    @FXML
    private TextField TasksName2;

    @FXML
    void Btn_2ME(MouseEvent event) {

    }

    @FXML
    void Confirm_button_action(ActionEvent event) throws IOException {
        List<Task> tasks =  MainTimer.mainTimer.tasks;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskItem.fxml"));
        Pane taskItemPane = loader.load();

        TaskItem controller = loader.getController();//to set prop
        controller.updateTitle(TasksName2.getText());
        MainTimer.mainTimer.TasksPane.set(id, taskItemPane);

        MainTimer.mainTimer.modifyTask(TasksName2.getText(),id);
        helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));
        java.time.LocalDate deadine = DueDate2.getValue();
        Date dateDeadline = Date.from(deadine.atStartOfDay(ZoneId.systemDefault()).toInstant());

        tasks.get(id).setName(TasksName2.getText());
        tasks.get(id).resetTimeSessions(Integer.parseInt(TasksDuration2.getText()));
        tasks.get(id).reschedule(dateDeadline);


        AnchorPane.setRightAnchor(helloApplication.initailPane, 0.0);
        helloApplication.MainAnchor.getChildren().add(helloApplication.initailPane);
        System.out.println("new Main Pane has opened");

        MainTimer.mainTimer.reload(id);

    }

    @FXML
    void Delete_button_action(ActionEvent event) {
        MainTimer.mainTimer.remove(id);
        helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));
        AnchorPane.setRightAnchor(helloApplication.initailPane, 0.0);
        helloApplication.MainAnchor.getChildren().add(helloApplication.initailPane);
    }

    @FXML
    void Exist_button_action(ActionEvent event) {
        helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));

        // Add the LoginPane to MainAnchor
        AnchorPane.setRightAnchor(helloApplication.initailPane, 0.0);
        helloApplication.MainAnchor.getChildren().add(helloApplication.initailPane);
        System.out.println("new Main Pane has opened");
    }
    void Edit( int id){
        this.id = id;
    }
    @FXML
    void btn_2Hover(MouseEvent event) {

    }

}
