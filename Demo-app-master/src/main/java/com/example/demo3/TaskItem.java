package com.example.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.Console;
import java.io.IOException;

public class TaskItem {
    private final HelloApplication helloApplication = HelloApplication.getInstance();

    public static int index = 0;
    public int id;
    @FXML
    private Button Stats_button121;

    @FXML
    private Button Stats_button1211;

    @FXML
    private Text TaskTitle;

    @FXML
    private Pane taskpane;

    @FXML
    void Stats_button_action(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditTask.fxml"));
        AnchorPane AnchorEditItemPane = loader.load();
        Pane EditPane = helloApplication.getChildPane(AnchorEditItemPane,0);

        helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));
        AnchorPane.setRightAnchor(EditPane, 0.0);
        helloApplication.MainAnchor.getChildren().add(EditPane);
        System.out.println("rani nawsal han");
        EditTask contoller = loader.getController();
        index--;
        contoller.Edit(id);

    }
    @FXML
    void Play_button_action(ActionEvent event) {

    }
    @FXML
    void Done_button_action(ActionEvent event) {
        MainTimer.mainTimer.remove(id);
    }

    @FXML
    void StdBtnME(MouseEvent event) {

    }

    @FXML
    void hover(MouseEvent event) {

    }
    @FXML
    void TaskOnLClick(MouseEvent event) {
        MainTimer.mainTimer.setIdTask(id);
        MainTimer.mainTimer.setTitle(TaskTitle.getText());
    }
    public void updateTitle(String newTitle) {
        this.TaskTitle.setText(newTitle);
        this.id = index;
        index++;

    }
    public void print(){
        System.out.println("rani nsayi");
    }
}
