module com.example.medicalrecordapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.medicalrecordapp to javafx.fxml;
    exports com.example.medicalrecordapp;
    exports com.example.medicalrecordapp.controller;
    opens com.example.medicalrecordapp.controller to javafx.fxml;
}