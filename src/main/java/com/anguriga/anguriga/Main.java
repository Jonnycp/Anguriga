package com.anguriga.anguriga;

import com.anguriga.anguriga.classes.BankAccount;
import com.anguriga.anguriga.controllers.BorderlessModal;
import com.anguriga.anguriga.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private static final BankAccount conto = new BankAccount("Pinco", "Pallino", "00069420");
    public static int atmOpened = 0;

    @Override
    public void start(Stage stage) throws IOException {
        startATM(stage, 250);
        startATM(new Stage(), 1200);
    }

    public static void startATM(Stage stage, int x) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-window.fxml"));
        fxmlLoader.setClassLoader(Main.class.getClassLoader());
        fxmlLoader.setController(new MainController(conto));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Homepage");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/icon.png"));
        stage.setX(x);
        stage.setY(150);
        stage.show();
        atmOpened++;
    }

    public static void startModal(Stage currentStage, String type, int[] size, int[] padding, boolean draggable, BorderlessModal controller) {
        String fxml = type.equals("versamento") || type.equals("prelievo") ? "versamento-prelievo.fxml" : "bollettini.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        try{
            //Carica controller
            if(controller != null){
                fxmlLoader.setClassLoader(Main.class.getClassLoader());
                fxmlLoader.setController(controller);
            }

            Scene scene = new Scene(fxmlLoader.load(), size[0], size[1]);
            Stage stage = new Stage();
            stage.setTitle(type.equals("versamento") ? "Versamento" : "Prelievo");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/resources/icon.png"));

            //Spawn at center
            stage.setX((currentStage.getX() + (currentStage.getWidth() - size[0]) / 2)+padding[0]);
            stage.setY((currentStage.getY() + (currentStage.getHeight() - size[1]) / 2)+padding[1]);


            //As modal
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(currentStage);

            //Make Borderless
            makeBorderless(stage, scene, draggable);

            stage.show();
        }catch (IOException e){
            System.out.println("Impossibile aprire la finestra di "+type);
            System.out.println(e.getMessage());
        }
    }

    private static void makeBorderless(Stage stage, Scene scene, boolean draggable) {
        stage.initStyle(StageStyle.TRANSPARENT);
        if(draggable) {
            final double[] offset = {0, 0};
            scene.setOnMousePressed(event -> {
                offset[0] = event.getSceneX();
                offset[1] = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - offset[0]);
                stage.setY(event.getScreenY() - offset[1]);
            });
        }
    }
    public static void main(String[] args) {
        launch();
    }
}