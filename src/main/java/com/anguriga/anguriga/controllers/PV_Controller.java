package com.anguriga.anguriga.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Arrays;

public class PV_Controller implements BorderlessModal{
    private String[] testi;

    @FXML
    protected Label titolo;
    @FXML
    protected TextField importo;
    @FXML
    protected Button button;

    @FXML
    public void closeWindow() {
        ((Stage) titolo.getScene().getWindow()).close();
    }

    @FXML
    public void initialize() {
        titolo.setText(testi[0]);
        importo.setPromptText(testi[1]);
        button.setText(testi[2]);
    }

    public PV_Controller(String titolo){
        String[][] dbTesti = new String[][]{
                new String[]{"Versamento", "Inserisci la somma da versare", "VERSA"},
                new String[]{"Prelievo", "Inserisci la somma da prelevare", "PRELEVA"},
        };

        if(titolo.equalsIgnoreCase("versamento")) {
            testi = dbTesti[0];
        }else if(titolo.equalsIgnoreCase("prelievo")){
            testi = dbTesti[1];
        }
    }

}

