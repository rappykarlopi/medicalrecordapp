package com.example.medicalrecordapp.controller;

import com.example.medicalrecordapp.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddPatientController {

    public TextField firstNameInput;
    public TextField lastNameInput;
    public TextField ageInput;
    public DatePicker dateInput;
    public TextField heightInput;
    public TextField addressInput;
    public TextField weightInput;
    public TextField bloodPressureInput;
    public ChoiceBox genderInput;
    public ChoiceBox bloodTypeInput;
    public TextField phoneNumberInput;
    public TextField remarksInput;
    public Label statusLabel;
    public DatePicker dateAdmitted;
    public TextField psychiatristNameInput;
    private ArrayList<RecordTemplate> records;

    private ObservableList<String> genderChoice = FXCollections.observableArrayList();
    private ObservableList<String> bloodTypeChoice = FXCollections.observableArrayList();

    private String filename = System.getProperty("user.dir").toString() + "\\src\\main\\java\\com\\example\\medicalrecordapp\\record.txt";

    @FXML
    public void initialize() {
        genderChoice.add("Male");
        genderChoice.add("Female");
        bloodTypeChoice.add("A+");
        bloodTypeChoice.add("A-");
        bloodTypeChoice.add("B+");
        bloodTypeChoice.add("B-");
        bloodTypeChoice.add("AB+");
        bloodTypeChoice.add("AB-");
        bloodTypeChoice.add("O-");
        bloodTypeChoice.add("O+");

        genderInput.setItems(genderChoice);
        bloodTypeInput.setItems(bloodTypeChoice);
    }

    public void setRecords(ArrayList<RecordTemplate> records) {
        this.records = records;
    }

    public void addPatient(ActionEvent event) {

        String tempName;
        boolean duplicated = false;

        if(lastNameInput.getText().equals("")) {
            tempName = firstNameInput.getText();
        } else {
            tempName = firstNameInput.getText() + " " + lastNameInput.getText();
        }

        for(int i=0; i<records.size(); i++) {
            if(records.get(i).getName().equals(tempName)) {
                duplicated = true;
                break;
            } else{
                duplicated = false;
            }
        }

        if((!firstNameInput.getText().equals("")) && (!ageInput.getText().equals("")) && (!ageInput.getText().equals(""))
        && !(dateInput.getValue() == null) && (!heightInput.getText().equals("")) && (!addressInput.getText().equals(""))
        && (!weightInput.getText().equals("")) && (!bloodPressureInput.getText().equals("")) && !(genderInput.getValue() == null)
        && !(bloodTypeInput.getValue() == null) && (!phoneNumberInput.getText().equals("")) && !(remarksInput.getText().equals(""))
        && !(dateAdmitted.getValue() == null) && (!psychiatristNameInput.getText().equals("")) && (!duplicated)) {
            int age, birthDay, birthYear, admissionDay, admissionYear;
            double weight, height;
            String name, birthMonth, birthdate, address, number, bloodType, bloodPressure, gender, remarks, psychiatristName, admissionDate, admissionMonth;

            birthdate = dateInput.getValue().toString();
            admissionDate = dateAdmitted.getValue().toString();
            address = addressInput.getText();

            String[] birthDateSplit = birthdate.split("-");
            String[] admissionDateSplit = admissionDate.split("-");

            if(lastNameInput.getText().equals("")) {
                name = firstNameInput.getText();
            } else {
                name = firstNameInput.getText() + " " + lastNameInput.getText();
            }
            age = Integer.parseInt(ageInput.getText());
            weight = Double.parseDouble(weightInput.getText());
            height = Double.parseDouble(heightInput.getText());
            number = phoneNumberInput.getText();
            bloodType = bloodTypeInput.getValue().toString();
            bloodPressure = bloodPressureInput.getText();
            gender = genderInput.getValue().toString();
            psychiatristName = psychiatristNameInput.getText();
            remarks = remarksInput.getText();

            birthMonth = birthDateSplit[1];
            birthDay = Integer.parseInt(birthDateSplit[2]);
            birthYear = Integer.parseInt(birthDateSplit[0]);

            admissionMonth = admissionDateSplit[1];
            admissionDay = Integer.parseInt(admissionDateSplit[2]);
            admissionYear = Integer.parseInt(admissionDateSplit[0]);

            try(FileWriter fileWriter = new FileWriter(filename, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                if(records.size() == 0) {
                    bufferedWriter.write(name + "," + age + "," +
                            height + "," + weight + "," + birthMonth+"/"+birthDay+"/"+birthYear + "," +
                            address + "," + number + "," + bloodType + "," + bloodPressure + "," + gender + "," + admissionMonth+"/"+admissionDay+"/"+admissionYear
                            + "," + psychiatristName + "," + remarks + "," + "" + "," + "PATIENT");
                } else {
                    bufferedWriter.write("\n" + name + "," + age + "," +
                            height + "," + weight + "," + birthMonth+"/"+birthDay+"/"+birthYear + "," +
                            address + "," + number + "," + bloodType + "," + bloodPressure + "," + gender + "," + admissionMonth+"/"+admissionDay+"/"+admissionYear
                            + "," + psychiatristName + "," + remarks + "," + "" + "," + "PATIENT");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while appending the text to the file: " + e.getMessage());
            }

            records.add(new PatientRecord(name, age, height, weight,
                    new Date(birthMonth, birthDay, birthYear),
                    address, number, bloodType, bloodPressure, gender,
                    new Date(admissionMonth, admissionDay, admissionYear), psychiatristName, remarks, ""));

            statusLabel.setText(name + " added");
        } else if(duplicated) {
            statusLabel.setText(tempName + " already exists!");
        } else {
            statusLabel.setText("One or more fields are missing!");
        }

    }

    public void backFromPatient(ActionEvent event) throws IOException {

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
