package com.anguriga.anguriga;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Homepage");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/icon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}