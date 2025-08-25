package com.example.medicalrecordapp.controller;

import com.example.medicalrecordapp.Main;
import com.example.medicalrecordapp.RecordTemplate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ChooseController {

    private ArrayList<RecordTemplate> records;

    @FXML
    public void setRecords(ArrayList<RecordTemplate> records) {
        this.records = records;
    }

    public void addStaff(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("add-staff.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene addStaffScene = new Scene(root);

        AddStaffController addStaffControl = loader.getController();
        addStaffControl.setRecords(records);

        stage.setScene(addStaffScene);
        stage.show();
    }

    public void addPatient(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("add-patient.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene addPatientScene = new Scene(root);

        AddPatientController addPatientControl = loader.getController();
        addPatientControl.setRecords(records);

        stage.setScene(addPatientScene);
        stage.show();
    }

    public void backFromChoose(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-menu.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene viewScene = new Scene(root, 640, 480);

        MainController mainControl = loader.getController();
        mainControl.setRecords(records);

        stage.setScene(viewScene);
        stage.show();
    }
}