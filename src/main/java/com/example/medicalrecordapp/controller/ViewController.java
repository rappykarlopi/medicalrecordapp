package com.example.medicalrecordapp.controller;

import com.example.medicalrecordapp.Date;
import com.example.medicalrecordapp.Main;
import com.example.medicalrecordapp.RecordTemplate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class ViewController {

    public TextField searchBox;
    public Label addressLabel;
    public Label bpLabel;
    public Label btLabel;
    public Label phoneNumberLabel;

    public Label name;
    public Label birth;
    public Label gender;
    public Label height;
    public Label weight;
    public Label age;
    public Label address;
    public Label phoneNumber;
    public Label bloodPressure;
    public Label bloodType;
    public Label position;
    public ImageView profilePic;
    public Label dateAdmitted;
    public Label dateAdmittedLabel;
    public Label phychiatristLabel;
    public Label editModeLabel;
    public Label psychiatristName;
    public DatePicker dateAdmittedInput;
    public TextField psychiatristNameInput;
    public TextField weightInput;
    public TextField bloodPressureInput;
    public Button saveChanges;
    public TextField heightInput;
    public Label psychiatristLabel;
    public TextArea noteArea;
    public Button noteChange;
    public ToggleButton editButton;
    private ArrayList<RecordTemplate> records;
    private FileChooser fileChooser;
    private boolean edit;
    private File filePath;
    private String filename = System.getProperty("user.dir").toString() + "\\src\\main\\java\\com\\example\\medicalrecordapp\\record.txt";

    @FXML
    public void initialize() {
        dateAdmittedInput.setVisible(false);
        weightInput.setVisible(false);
        bloodPressureInput.setVisible(false);
        psychiatristNameInput.setVisible(false);
        heightInput.setVisible(false);
        saveChanges.setVisible(false);
        edit = false;
    }

    public void setRecords(ArrayList<RecordTemplate> records) {
        this.records = records;
    }
    
    public void viewRecord(RecordTemplate rt) throws IOException {
        if(rt.getType().equalsIgnoreCase("PATIENT")) {
            String[] noteSplit = rt.getRemarks().split("-");

            String finalText = "";

            noteChange.setVisible(true);
            editModeLabel.setVisible(true);
            editButton.setVisible(true);

            bpLabel.setText("Blood Pressure:");;
            btLabel.setText("Blood Type:");
            addressLabel.setText("Address:");
            dateAdmittedLabel.setText("Last Admitted: ");
            psychiatristLabel.setText("Psychiatrist: ");

            name.setText(rt.getName());
            birth.setText(rt.getBirth().toString());
            gender.setText(rt.getGender());
            height.setText(rt.getHeight() + "cm");
            weight.setText(rt.getWeight() + "kg");
            age.setText("" + rt.getAge());
            address.setText(rt.getAddress());

            phoneNumber.setText(rt.getNumber());
            phoneNumber.setLayoutY(323);
            phoneNumberLabel.setLayoutY(323);

            bloodPressure.setText(rt.getBloodPressure());
            bloodType.setText(rt.getBloodType());
            dateAdmitted.setText(rt.getDateAdmitted().toString());
            psychiatristName.setText(rt.getPsychiatristName());
            position.setText("");
            noteArea.setVisible(true);

            for(int i=0; i<noteSplit.length; i++) {
                finalText = finalText + noteSplit[i] + "\n";
            }
            noteArea.setText(finalText);

            if(rt.getPicLocation().equals("")) {
                profilePic.setImage(new Image(System.getProperty("user.dir").toString() +
                        "\\src\\main\\resources\\com\\example\\medicalrecordapp\\emptypic.png"));
            } else {
                profilePic.setImage(new Image(System.getProperty("user.dir").toString() +
                        "\\src\\main\\resources\\com\\example\\medicalrecordapp\\img\\" + rt.getPicLocation()));
            }
        } else if (rt.getType().equalsIgnoreCase("STAFF")) {
            editModeLabel.setVisible(false);
            editButton.setVisible(false);
            noteArea.setVisible(false);
            noteChange.setVisible(false);
            bpLabel.setText("");
            bloodPressure.setText("");
            btLabel.setText("");
            bloodType.setText("");
            addressLabel.setText("");
            address.setText("");
            name.setText(rt.getName());
            birth.setText(rt.getBirth().toString());
            gender.setText(rt.getGender());
            phoneNumber.setLayoutY(293);
            phoneNumberLabel.setLayoutY(293);
            phoneNumber.setText(rt.getNumber());
            height.setText(rt.getHeight() + "cm");
            weight.setText(rt.getWeight() + "kg");
            age.setText("" + rt.getAge());
            position.setText(rt.getPosition());
            dateAdmitted.setText("");
            dateAdmittedLabel.setText("");
            psychiatristName.setText("");
            psychiatristLabel.setText("");
            if(rt.getPicLocation().equals("")) {
                profilePic.setImage(new Image(System.getProperty("user.dir").toString() +
                        "\\src\\main\\resources\\com\\example\\medicalrecordapp\\emptypic.png"));
            } else {
                profilePic.setImage(new Image(System.getProperty("user.dir").toString() +
                        "\\src\\main\\resources\\com\\example\\medicalrecordapp\\img\\" + rt.getPicLocation()));
            }
        }
    }

    public void searchRecord(ActionEvent event) throws IOException {
        for(int i=0; i<records.size(); i++) {
            if(searchBox.getText().equalsIgnoreCase(records.get(i).getName()) && (!edit)) {
                viewRecord(records.get(i));
                break;
            }
        }
    }

    public void backFromView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-menu.fxml"));
        Pane root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene viewScene = new Scene(root);

        MainController mainControl = loader.getController();
        mainControl.setRecords(records);

        stage.setScene(viewScene);
        stage.show();
    }

    public void changePicture(ActionEvent event) throws IOException {
        if(!name.getText().equalsIgnoreCase("(name)")) {

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    profilePic.setImage(new Image(file.toURI().toString()));

                    Path sourcePath = file.toPath();
                    Path destinationPath = new File( System.getProperty("user.dir").toString() +
                            "\\src\\main\\resources\\com\\example\\medicalrecordapp\\img\\" + file.getName()).toPath();
                    System.out.println(destinationPath);
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    for(int i=0; i<records.size(); i++) {
                        if(name.getText().equalsIgnoreCase(records.get(i).getName())) {
                            records.get(i).setPicLocation(file.getName().toString());
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Error!");
            }

            updateRecord(records, filename);
        }
    }

    public void updateRecord(ArrayList<RecordTemplate> records, String filename) {
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

    public void editMode(ActionEvent event) {
        
        String type = null;
        
        for(int i=0; i<records.size(); i++) {
            if(records.get(i).getName().equals(name.getText())) {
                type = records.get(i).getType();
            }
        }
        
        if(dateAdmittedInput.isVisible()) {
            edit = false;
        } else if (!name.getText().equals("(name)") && type.equals("PATIENT")) {
            edit = true;
        }

        if(edit) {
            editModeLabel.setText("EDIT MODE: ON");
            dateAdmittedInput.setVisible(true);
            weightInput.setVisible(true);
            heightInput.setVisible(true);
            bloodPressureInput.setVisible(true);
            psychiatristNameInput.setVisible(true);
            saveChanges.setVisible(true);
        } else {
            editModeLabel.setText("EDIT MODE: OFF");
            dateAdmittedInput.setVisible(false);
            weightInput.setVisible(false);
            heightInput.setVisible(false);
            bloodPressureInput.setVisible(false);
            psychiatristNameInput.setVisible(false);
            saveChanges.setVisible(false);
        }
    }

    public void changeRecord(ActionEvent event) throws IOException {

        for(int i=0; i<records.size(); i++) {
            if(records.get(i).getName().equals(name.getText())) {
                System.out.println("FOUND");
                if(!weightInput.getText().equals("")) {
                    records.get(i).setWeight(Double.parseDouble(weightInput.getText()));
                }
                if(!heightInput.getText().equals("")) {
                    records.get(i).setHeight(Double.parseDouble(heightInput.getText()));
                }
                if(!bloodPressureInput.getText().equals("")) {
                    records.get(i).setBloodPressure(bloodPressureInput.getText());
                }
                if(!psychiatristNameInput.getText().equals("")) {
                    records.get(i).setPsychiatristName(psychiatristNameInput.getText());
                }
                if (dateAdmittedInput.getValue() != null) {
                    String[] dateAdmittedSplit = dateAdmittedInput.getValue().toString().split("-");
                    String admissionMonth = dateAdmittedSplit[1];
                    int admissionDay = Integer.parseInt(dateAdmittedSplit[2]);
                    int admissionYear = Integer.parseInt(dateAdmittedSplit[0]);
                    records.get(i).setDateAdmitted(null);
                    records.get(i).setDateAdmitted(new Date(admissionMonth, admissionDay, admissionYear));
                }
                updateRecord(records, filename);
                viewRecord(records.get(i));
                break;
            }
        }

    }

    public void changeNote(ActionEvent event) throws IOException {
        for(int i=0; i<records.size(); i++) {
            if (records.get(i).getName().equals(name.getText())) {
                if (!noteArea.getText().equals("")) {
                    String[] noteSplit = noteArea.getText().split("\\n");
                    String remarks = "";
                    for(int j=0; j<noteSplit.length; j++) {
                        if(j < noteSplit.length-1) {
                            remarks = remarks.concat(noteSplit[j]).concat("-");
                        } else {
                            remarks = remarks.concat(noteSplit[j]);
                        }
                    }
                    records.get(i).setRemarks(remarks);
                }
                updateRecord(records, filename);
                viewRecord(records.get(i));
                break;
            }
        }
    }
}
