package com.example.medicalrecordapp;

import com.example.medicalrecordapp.controller.LoginController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    private String filename = System.getProperty("user.dir").toString() + "\\src\\main\\java\\com\\example\\medicalrecordapp\\record.txt";
    private String icon = System.getProperty("user.dir").toString() + "\\src\\main\\java\\com\\example\\medicalrecordapp\\icon.png";
    private ArrayList<RecordTemplate> records = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("login-page.fxml"));
        Parent root = loader.load();

        loadList(records, filename);

        LoginController loginController = loader.getController();
        loginController.setRecords(records);

        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setTitle("DocuMed");
        stage.getIcons().add(new Image(icon));
        stage.show();
    }
    /*
    * loadlist() method
    * - This method is responsible for extracting all contents in the records.txt
    *
    * Parameters:
    * Arraylist<RecordTemplate> record - This parameter is used to store all necessary info of a patient or a staff
    * String filename - This parameter is used to load the location of the filename
    * */
    private void loadList(ArrayList<RecordTemplate> record, String filename) {
        File file = new File(filename);

        if(file.exists()) {
            try {
                FileReader fileReader = new FileReader(filename);
                Scanner text = new Scanner(fileReader);
                String list[] = null;

                while (text.hasNext()) {
                    String line = text.nextLine();
                    list = line.split(",");

                    String[] birthDateSplit = list[4].split("/");

                    if(list[list.length-1].equals("STAFF")) {
                        record.add(new StaffRecord(list[0],
                                Integer.parseInt(list[1]),
                                Double.parseDouble(list[2]),
                                Double.parseDouble(list[3]),
                                new Date(birthDateSplit[0], Integer.parseInt(birthDateSplit[1]), Integer.parseInt(birthDateSplit[2])),
                                list[7],
                                list[5],
                                list[6],
                                list[8]));

                    } else if(list[list.length-1].equals("PATIENT")) {
                        String[] dateAdmittedSplit = list[10].split("/");

                        record.add(new PatientRecord(list[0],
                                Integer.parseInt(list[1]),
                                Double.parseDouble(list[2]),
                                Double.parseDouble(list[3]),
                                new Date(birthDateSplit[0], Integer.parseInt(birthDateSplit[1]), Integer.parseInt(birthDateSplit[2])),
                                list[5],
                                list[6],
                                list[7],
                                list[8],
                                list[9],
                                new Date(dateAdmittedSplit[0], Integer.parseInt(dateAdmittedSplit[1]), Integer.parseInt(dateAdmittedSplit[2])),
                                list[11],
                                list[12],
                                list[13]));

                    }
                }
            } catch (java.io.FileNotFoundException e) {
                System.out.println("File does not Exist");
            }
        } else {
            try {
                FileWriter fileWriter = new FileWriter(filename);

                fileWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}