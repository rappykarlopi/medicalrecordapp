package com.example.medicalrecordapp.controller;

import com.example.medicalrecordapp.Main;
import com.example.medicalrecordapp.RecordTemplate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {

    private ArrayList<RecordTemplate> records;

    @FXML
    public void setRecords(ArrayList<RecordTemplate> records) {
        this.records = records;
    }

    public void addClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("type-of-record.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene chooseScene = new Scene(root);

        ChooseController chooseControl = loader.getController();
        chooseControl.setRecords(records);

        stage.setScene(chooseScene);
        stage.show();
    }

    public void viewClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view-record.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene viewScene = new Scene(root);

        ViewController viewControl = loader.getController();
        viewControl.setRecords(records);

        stage.setScene(viewScene);
        stage.show();
    }

    public void removeClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("remove-a-record.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene removeScene = new Scene(root);

        RemoveController removeControl = loader.getController();
        removeControl.setRecords(records);

        stage.setScene(removeScene);
        stage.show();
    }

    public void listClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("list-of-records.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene listScene = new Scene(root);

        ListController listControl = loader.getController();
        listControl.setRecords(records);

        stage.setScene(listScene);
        stage.show();
    }

}
