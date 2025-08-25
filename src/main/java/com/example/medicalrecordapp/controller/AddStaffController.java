package com.example.medicalrecordapp.controller;

import com.example.medicalrecordapp.Date;
import com.example.medicalrecordapp.Main;
import com.example.medicalrecordapp.RecordTemplate;
import com.example.medicalrecordapp.StaffRecord;
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

public class AddStaffController {
    public TextField firstNameInput;
    public TextField lastNameInput;
    public TextField ageInput;
    public DatePicker dateInput;
    public TextField heightInput;
    public TextField weightInput;
    public TextField numberInput;
    public TextField positionInput;
    public ChoiceBox genderInput;
    public Label statusLabel;
    private ArrayList<RecordTemplate> records;
    private ObservableList<String> genderChoice = FXCollections.observableArrayList();
    private String filename = System.getProperty("user.dir").toString() + "\\src\\main\\java\\com\\example\\medicalrecordapp\\record.txt";

    @FXML

    public void initialize() {
        genderChoice.add("Male");
        genderChoice.add("Female");
        genderInput.setItems(genderChoice);
    }

    public void setRecords(ArrayList<RecordTemplate> records) {
        this.records = records;
    }

    public void addStaff(ActionEvent event) {

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

        if((!firstNameInput.getText().equals("")) && (!ageInput.getText().equals("")) && !(dateInput.getValue() == null)
        && (!heightInput.getText().equals("")) && (!weightInput.getText().equals("")) && (!numberInput.getText().equals(""))
        && (!positionInput.getText().equals("")) && !(genderInput.getValue() == null) && (!duplicated)) {
            int age, day, year;
            double height, weight;
            String name, month, date, position, number, gender;

            if(lastNameInput.getText().equals("")) {
                name = firstNameInput.getText();
            } else {
                name = firstNameInput.getText() + " " + lastNameInput.getText();
            }
            date = dateInput.getValue().toString();
            gender = genderInput.getValue().toString();
            age = Integer.parseInt(ageInput.getText());
            height = Double.parseDouble(heightInput.getText());
            weight = Double.parseDouble(weightInput.getText());
            position = positionInput.getText();
            number = numberInput.getText();

            String[] dateSplit = date.split("-");

            month = dateSplit[1];
            day = Integer.parseInt(dateSplit[2]);
            year = Integer.parseInt(dateSplit[0]);

            try(FileWriter fileWriter = new FileWriter(filename, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                if(records.size() == 0) {
                    bufferedWriter.write(name + "," + age + "," +
                            height + "," + weight + "," + month+"/"+day+"/"+year + "," + number +
                            "," + position + "," + gender + "," + "" + "," + "STAFF");
                } else {
                    bufferedWriter.write("\n" + name + "," + age + "," +
                            height + "," + weight + "," + month+"/"+day+"/"+year + "," + number +
                            "," + position + "," + gender + "," + "" + "," + "STAFF");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while appending the text to the file: " + e.getMessage());
            }

            records.add(new StaffRecord(name, age, height, weight, new Date(month, day, year), gender, number, position, ""));
            statusLabel.setText(name + " added");
        } else if(duplicated) {
            statusLabel.setText(tempName + " already exists!");
        } else {
            statusLabel.setText("One or more fields are missing!");
        }
    }

    public void backFromStaff(ActionEvent event) throws IOException {

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
