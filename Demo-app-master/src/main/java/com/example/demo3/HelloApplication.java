package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloApplication extends Application {

    public AnchorPane MainAnchor;
    public AnchorPane AnchorBar;
    public Pane SideBar;
    public AnchorPane initialAnchorPane;
    public Pane initailPane;
    private static HelloApplication instance;



    public HelloApplication() {
        instance = this;
    }

    public static HelloApplication getInstance() {
        return instance;
    }


    @Override
    public void start(Stage stage) throws IOException {
        MainAnchor = loadPaneFromFXML("hello-view.fxml");
        AnchorBar = loadPaneFromFXML( "Sidbar.fxml");
        SideBar = getChildPane(AnchorBar,0);
        initialAnchorPane = loadPaneFromFXML("MainTimer.fxml");
        initailPane= getChildPane(initialAnchorPane,0);

        AnchorPane.setRightAnchor(initailPane, 0.0);
        MainAnchor.getChildren().add(SideBar);
        MainAnchor.getChildren().add(initailPane);
        System.out.println("new Main (initial) Pane had opened");
        Scene scene = new Scene(MainAnchor);
        stage.setTitle("PomoTask");
        stage.setScene(scene);
        stage.show();
    }

    AnchorPane loadPaneFromFXML(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            return loader.load();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("new null Pane has created");
            return new AnchorPane(); // Return an empty pane in case of an exception
        }
    }
    public void setContentPane(Pane newContent) {
        // Clear existing content and add the new content
        MainAnchor.getChildren().clear();
        AnchorPane.setRightAnchor(newContent, 0.0);
        MainAnchor.getChildren().add(SideBar);
        MainAnchor.getChildren().add(newContent);
    }


    Pane getChildPane(AnchorPane anchorPane, int index) {
        if (index >= 0 && index < anchorPane.getChildren().size()) {
            if (anchorPane.getChildren().get(index) instanceof Pane) {
                return (Pane) anchorPane.getChildren().get(index);
            }
        }
        System.out.println("rani nokhroj mna");
        return null;
    }

    public static void main(String[] args) {
    launch();

    }
}