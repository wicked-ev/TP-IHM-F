package com.example.demo3;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.Task;


import java.io.IOException;
//import java.time.Duration;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainTimer {
    public static MainTimer mainTimer;
    private final HelloApplication helloApplication = HelloApplication.getInstance();

    AnchorPane AnchorAddPane;
    Pane AddPane;
    List<Pane> TasksPane ;
    List<Task> tasks = new ArrayList<>();
    Task runningTask ;
    int index = 1;
    int idTask;
    private Timeline timeline;
    private int secondsElapsed;
    //private long startTime;
    private boolean isPaused = false;
    private static final String PLAY_ICON_PATH = "play.png";
    private static final String PAUSE_ICON_PATH = "pause-button.png";
    private static String FS = "Focus Session";

    private static String BS = "Short Break";

    private static String LBS = "Long Break";
    private  String SessionName = FS;



    private int ShortBreaksNum = 0;
    private int ifocusNum = 1;

    @FXML
    private Text displaySessionName;
    @FXML
    private ImageView SPImG;

    @FXML
    private Pane TimerPan;

    @FXML
    private Text TimerText;
    // Add the following fields for the Timer
    private Timeline timerTimeline;
    private Duration timerDuration;

    @FXML
    private VBox VboxTasks;

    @FXML
    private ImageView addTask;

    @FXML
    private Button addTask_button;

    @FXML
    private Button play_button;

    @FXML
    private Button reset_button;
    @FXML
    private Text displayTasksName;
    @FXML
    private Button skip_button;

    @FXML
    private Pane textTasks;

    @FXML
    private Text textprogressbar;

    @FXML
    private ProgressBar timerProgressbar;

    @FXML
    void Stats_button_action(ActionEvent event) {
        if (runningTask != null) {
            System.out.println("manich null");
            runningTask.startTask();
            // Start the timer when the "Stats" button is clicked
            timerTimeline.play();
        }
    }

    @FXML
    void StdBtnME(MouseEvent event) {

    }
    public MainTimer(){
        mainTimer = this;
        this.TasksPane = new ArrayList<Pane>();
//        setupTimer();
    }
    private void setupTimer() {
        // Set up the Timeline for updating the timer text every second
        timerTimeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(1), this::updateTimerText)
        );
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
    }
    private void updateTimerText(ActionEvent event) {
        // Update the TimerText every second
        if (runningTask != null) {
//            TimerText.setText(runningTask.getElapsedTimeAsString());
        }
    }
    @FXML
    void addTaskButton(ActionEvent event) throws IOException {
        AnchorAddPane = helloApplication.loadPaneFromFXML("AddTasks.fxml");;
        AddPane = helloApplication.getChildPane(AnchorAddPane,0);
        helloApplication.MainAnchor.getChildren().remove(helloApplication.getChildPane(helloApplication.MainAnchor,1));
        // Add the LoginPane to MainAnchor
        AnchorPane.setRightAnchor(AddPane, 0.0);
        helloApplication.MainAnchor.getChildren().add(AddPane);
        System.out.println("new Add Pane has opened");
    }
    void update(Pane task){
        TasksPane.add(task);

        VBox vbox = VboxTasks;
        vbox.getChildren().add(index, task);
        index++;
    }

    void reload(int idToChange) {
        VBox vbox = VboxTasks;
        int panes = 1;
        System.out.println("id:" + idToChange);
        for (Pane pane : TasksPane) {
            System.out.println("panes"+panes);
            if (panes == idToChange+1){
                System.out.println("dkhalt");
                vbox.getChildren().remove(idToChange+1);
                System.out.println("removed");
                vbox.getChildren().add(idToChange+1, pane);
            }
            panes++;
        }
    }
    void remove(int idToChange) {
        Platform.runLater(() -> {
            VBox vbox = VboxTasks;

            vbox.getChildren().remove(idToChange + 1);
            tasks.remove(idToChange);
            idTask = tasks.size();
            index = TasksPane.size();
            vbox.requestLayout();
        });
    }


    public void setTimerText(String timerText) {

        TimerText.setText(timerText);
    }

    public static MainTimer getMaininstance(){

        return mainTimer;
    }
    @FXML
    void hover(MouseEvent event) {

    }

    public void setTitle(String timerText) {
        runningTask = getTask();
        displayTasksName.setText("Task name: " + timerText);
//        setTimerText(runningTask.getElapsedTimeAsString()); // Update TimerText when setting title
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }
    public void addTask(Task task ){
        tasks.add(task);
    }


    public Task getTask() {
        int taskCoun = 0;
        for (Task task : tasks) {
            if (taskCoun == idTask) {
                return task;
            }
            taskCoun++;
        }
        return null;
    }

    void modifyTask(String name,int id){
        System.out.println("name:" + name);
        tasks.get(id).setName(name);
        System.out.println("modified");
        System.out.println(tasks.get(id).getName());
    }
    @FXML
    void reset_button_action(ActionEvent event) {
        stopTimer();
    }
    @FXML
    void play_button_action(ActionEvent event) {
        if(runningTask != null){
        System.out.println("before"+isPaused);
        if (timeline == null || timeline.getStatus() == Animation.Status.STOPPED || timeline.getStatus() == Animation.Status.PAUSED) {
            // Start or resume the timer
            //playPauseButton.setGraphic(new ImageView(pauseIcon));
            System.out.println(isPaused);
            if (isPaused) {
                resumeTimer();
            } else {
                startTimer();
            }
        } else {
            System.out.println("rni fi pause timer ndore");
            pauseTimer();
        }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No Task Selected");
            alert.setContentText("Please choose a task before starting the timer.");
            alert.showAndWait();
        }
    }
    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            secondsElapsed++;
            System.out.println(secondsElapsed+'\n');
            updateButtonText();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        secondsElapsed = 0;
        updateButtonText();
    }
    private void StopTimerToSwitch(){
        if (timeline != null) {
            timeline.stop();
        }
        secondsElapsed = 0;
    }
    private void pauseTimer() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.pause();
            isPaused = true;
        }
    }

    private void resumeTimer() {
        if (timeline != null && timeline.getStatus() == Animation.Status.PAUSED || timeline.getStatus() == Animation.Status.STOPPED) {
            timeline.play();
            isPaused = false;
        }
    }
    private void updateButtonText() {
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;

        String formattedTime = String.format("%02d:%02d", minutes, seconds);

        System.out.println(formattedTime);
        System.out.println("Seconds atm"+secondsElapsed);
        System.out.println(SessionName);
        System.out.println("Num of SB:"+ShortBreaksNum);
        TimerText.setText(formattedTime);
        System.out.println("nbr pmodoro" +runningTask.getNumberOfPomodoro() + " nbr of info" + ifocusNum );

        if(minutes == Task.inFocusTime && SessionName == FS && ShortBreaksNum == Task.inFocusTimes -1){
            if (runningTask.getNumberOfPomodoro() > ifocusNum){
                ifocusNum++;
                System.out.println("we finished 2 short Breaks");
                StopTimerToSwitch();
                TimerText.setText("00:00");
                ShortBreaksNum = 0;
                SessionName = LBS;
                displaySessionName.setText(SessionName);
            }else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Info");
                    alert.setContentText("Task completed");
                    alert.showAndWait();
                });
                stopTimer();
                displaySessionName.setText("Done");
            }

        }
        else if( minutes == Task.inFocusTime && (SessionName == FS)){
            if (runningTask.getNumberOfPomodoro() > ifocusNum) {
                ifocusNum++;
                System.out.println("we finished focus");
                StopTimerToSwitch();
                TimerText.setText("00:00");
                SessionName = BS;
                displaySessionName.setText(SessionName);
            }else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Info");
                    alert.setContentText("Task completed");
                    alert.showAndWait();
                });
                stopTimer();
                displaySessionName.setText("Done");
            }
        }
        else if(minutes == Task.shortBreakTime && SessionName == BS){
            if (runningTask.getNumberOfPomodoro() > ifocusNum) {

            System.out.println("we finished short Breaks");
            StopTimerToSwitch();
            TimerText.setText("00:00");
            ShortBreaksNum++;
            SessionName = FS;
            displaySessionName.setText(SessionName);
            }else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Info");
                    alert.setContentText("Task completed");
                    alert.showAndWait();
                });
                stopTimer();
                displaySessionName.setText("Done");
            }
        }
        else if(minutes == Task.longBreakTime && SessionName == LBS ){
            if (runningTask.getNumberOfPomodoro() > ifocusNum) {
                System.out.println("we finished Long Break");
                StopTimerToSwitch();
                TimerText.setText("00:00");
                SessionName = FS;
                displaySessionName.setText(SessionName);
            }else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Info");
                    alert.setContentText("Task completed");
                    alert.showAndWait();
                });
                stopTimer();
                displaySessionName.setText("Done");
            }
        }
        else if(minutes == 5){
            System.out.println("thats checkes");
        }
        else {
            System.out.println("Nothing was right");
        }


    }

}