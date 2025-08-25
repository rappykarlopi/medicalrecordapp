package com.example.medicalrecordapp.controller;

import com.example.medicalrecordapp.Main;
import com.example.medicalrecordapp.RecordTemplate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    public TextField idNum;
    public PasswordField passwordField;
    private Pane root;
    private Stage stage;
    private String username = "admin", password = "admin";
    private ArrayList<RecordTemplate> records;

    @FXML
    public void setRecords(ArrayList<RecordTemplate> records) {
        this.records = records;
    }

    public void loginClick(ActionEvent event) throws IOException {
        if(idNum.getText().equals(username) && passwordField.getText().equals(password)) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-menu.fxml"));
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene viewScene = new Scene(root);

            MainController mainControl = loader.getController();
            mainControl.setRecords(records);

            stage.setScene(viewScene);
            stage.show();
        } else {
            System.out.println("Wrong ID Number or Password!");
        }
    }

}