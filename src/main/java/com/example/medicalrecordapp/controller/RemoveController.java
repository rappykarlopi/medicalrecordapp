package com.example.medicalrecordapp.controller;

import com.example.medicalrecordapp.Main;
import com.example.medicalrecordapp.RecordTemplate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RemoveController {

    public ListView recordList;
    public TextField removeInput;
    public Label statusLabel;
    private ArrayList<RecordTemplate> records;

    private ObservableList<String> recordNames = FXCollections.observableArrayList();
    private String filename = System.getProperty("user.dir").toString() + "\\src\\main\\java\\com\\example\\medicalrecordapp\\record.txt";

    @FXML

    public void setRecords(ArrayList<RecordTemplate> records) {
        this.records = records;

        for(int i=0; i<records.size(); i++) {
            recordNames.add(records.get(i).getName());
        }
        recordList.setItems(recordNames);
    }

    public void removeRecord(ActionEvent event) {

        String remove = removeInput.getText();

        for(int i=0; i<records.size(); i++) {
            if(records.get(i).getName().equalsIgnoreCase(remove)) {
                statusLabel.setText(records.get(i).getName() + " removed");
                records.remove(i);
                recordNames.remove(i);
                break;
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(filename);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i=0; i<records.size(); i++) {
                if(i == 0) {
                    if(records.get(i).getType().equalsIgnoreCase("STAFF")) {
                        bufferedWriter.write(records.get(i).getName() + "," + records.get(i).getAge() + "," +
                                records.get(i).getHeight() + "," + records.get(i).getWeight() + "," +
                                records.get(i).getBirth().getMonth()+"/"+records.get(i).getBirth().getDay()+"/"+records.get(i).getBirth().getYear() + ","
                                + records.get(i).getNumber() + "," + records.get(i).getPosition() + "," +
                                "," + records.get(i).getGender() +records.get(i).getPicLocation() + "," + "STAFF");
                    } else if (records.get(i).getType().equalsIgnoreCase("PATIENT")) {
                        bufferedWriter.write(records.get(i).getName() + "," + records.get(i).getAge() + "," +
                                records.get(i).getHeight() + "," + records.get(i).getWeight() + "," +
                                records.get(i).getBirth().getMonth()+"/"+records.get(i).getBirth().getDay()+"/"+records.get(i).getBirth().getYear() + "," +
                                records.get(i).getAddress() + "," + records.get(i).getNumber() + "," + records.get(i).getBloodType() + "," +
                                records.get(i).getBloodPressure() + "," + records.get(i).getGender() + ","
                                + records.get(i).getDateAdmitted().getMonth() + "/" + records.get(i).getDateAdmitted().getDay() + "/" + records.get(i).getDateAdmitted().getYear()
                                + "," + records.get(i).getPsychiatristName() + "," + records.get(i).getRemarks() + "," + records.get(i).getPicLocation() + "," + "PATIENT");
                    }
                } else {
                    if (records.get(i).getType().equalsIgnoreCase("STAFF")) {
                        bufferedWriter.write("\n" + records.get(i).getName() + "," + records.get(i).getAge() + "," +
                                records.get(i).getHeight() + "," + records.get(i).getWeight() + "," +
                                records.get(i).getBirth().getMonth() + "/" + records.get(i).getBirth().getDay() + "/" + records.get(i).getBirth().getYear() + ","
                                + records.get(i).getNumber() + "," + records.get(i).getPosition() +
                                "," + records.get(i).getGender() + "," + records.get(i).getPicLocation() + "," + "STAFF");
                    } else if (records.get(i).getType().equalsIgnoreCase("PATIENT")) {
                        bufferedWriter.write("\n" + records.get(i).getName() + "," + records.get(i).getAge() + "," +
                                records.get(i).getHeight() + "," + records.get(i).getWeight() + "," +
                                records.get(i).getBirth().getMonth()+"/"+records.get(i).getBirth().getDay()+"/"+records.get(i).getBirth().getYear() + "," +
                                records.get(i).getAddress() + "," + records.get(i).getNumber() + "," + records.get(i).getBloodType() + "," +
                                records.get(i).getBloodPressure() + "," + records.get(i).getGender() + ","
                                + records.get(i).getDateAdmitted().getMonth() + "/" + records.get(i).getDateAdmitted().getDay() + "/" + records.get(i).getDateAdmitted().getYear()
                                + "," + records.get(i).getPsychiatristName() + "," + records.get(i).getRemarks() + "," + records.get(i).getPicLocation() + "," + "PATIENT");
                    }
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while overwriting the file.");
            e.printStackTrace();
        }
    }

    public void backFromRemove(ActionEvent event) throws IOException {

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