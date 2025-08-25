package com.example.medicalrecordapp.controller;

import com.example.medicalrecordapp.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ListController {

    public TableView<PatientRecord> patientTable;
    public TableView<StaffRecord> staffTable;
    public TableColumn<PatientRecord, String> patientName;
    public TableColumn<PatientRecord, Integer> patientAge;
    public TableColumn<PatientRecord, Double> patientHeight;
    public TableColumn<PatientRecord, Double> patientWeight;
    public TableColumn<PatientRecord, Date> patientBirth;
    public TableColumn<PatientRecord, String> patientAddress;
    public TableColumn<PatientRecord, String> patientNumber;
    public TableColumn<PatientRecord, String> patientBloodType;
    public TableColumn<PatientRecord, String> patientBloodPressure;
    public TableColumn<PatientRecord, String> patientGender;
    public TableColumn<PatientRecord, String> patientRemarks;
    public TableColumn<StaffRecord, String> staffName;
    public TableColumn<StaffRecord, Integer> staffAge;
    public TableColumn<StaffRecord, Double> staffHeight;
    public TableColumn<StaffRecord, Double> staffWeight;
    public TableColumn<StaffRecord, Date> staffBirthdate;
    public TableColumn<StaffRecord, String> staffNumber;
    public TableColumn<StaffRecord, String> staffPosition;
    public TableColumn<StaffRecord, String> staffGender;

    private ArrayList<RecordTemplate> records;

    @FXML
    public void initialize() {
        patientName.setCellValueFactory(new PropertyValueFactory<PatientRecord, String>("Name"));
        patientAge.setCellValueFactory(new PropertyValueFactory<PatientRecord, Integer>("Age"));
        patientHeight.setCellValueFactory(new PropertyValueFactory<PatientRecord, Double>("Height"));
        patientWeight.setCellValueFactory(new PropertyValueFactory<PatientRecord, Double>("Weight"));
        patientBirth.setCellValueFactory(new PropertyValueFactory<PatientRecord, Date>("Birth"));
        patientAddress.setCellValueFactory(new PropertyValueFactory<PatientRecord, String>("Address"));
        patientNumber.setCellValueFactory(new PropertyValueFactory<PatientRecord, String>("Number"));
        patientBloodType.setCellValueFactory(new PropertyValueFactory<PatientRecord, String>("BloodType"));
        patientBloodPressure.setCellValueFactory(new PropertyValueFactory<PatientRecord, String>("BloodPressure"));
        patientGender.setCellValueFactory(new PropertyValueFactory<PatientRecord, String>("Gender"));
        patientRemarks.setCellValueFactory(new PropertyValueFactory<PatientRecord, String>("Remarks"));

        staffName.setCellValueFactory(new PropertyValueFactory<StaffRecord, String>("Name"));
        staffAge.setCellValueFactory(new PropertyValueFactory<StaffRecord, Integer>("Age"));
        staffHeight.setCellValueFactory(new PropertyValueFactory<StaffRecord, Double>("Height"));
        staffWeight.setCellValueFactory(new PropertyValueFactory<StaffRecord, Double>("Weight"));
        staffBirthdate.setCellValueFactory(new PropertyValueFactory<StaffRecord, Date>("Birth"));
        staffNumber.setCellValueFactory(new PropertyValueFactory<StaffRecord, String>("Number"));
        staffPosition.setCellValueFactory(new PropertyValueFactory<StaffRecord, String>("Position"));
        staffGender.setCellValueFactory(new PropertyValueFactory<StaffRecord, String>("Gender"));
    }

    public void setRecords(ArrayList<RecordTemplate> records) {
        this.records = records;
        setTable();
    }

    public void setTable() {
        ObservableList<PatientRecord> patientRecord = patientTable.getItems();
        ObservableList<StaffRecord> staffRecord = staffTable.getItems();

        for(int i=0; i<records.size(); i++) {
            if(records.get(i).getType().equalsIgnoreCase("PATIENT")) {
                patientRecord.add((PatientRecord) records.get(i));
            } else if(records.get(i).getType().equalsIgnoreCase("STAFF")) {
                staffRecord.add((StaffRecord) records.get(i));
            }
        }
        patientTable.setItems(patientRecord);
        staffTable.setItems(staffRecord);
    }

    public void backFromList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-menu.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene viewScene = new Scene(root);

        MainController mainControl = loader.getController();
        mainControl.setRecords(records);

        stage.setScene(viewScene);
        stage.show();
    }
}
