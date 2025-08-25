package com.example.medicalrecordapp;

public class StaffRecord extends RecordTemplate {

    public StaffRecord() {
        // Default Constructor
    }

    /**
     * This is the custom constructor for PatientRecord class
     * @param name - name of the patient (String)
     * @param age - age of the patient (Integer)
     * @param height - height of the patient (Double)
     * @param weight - weight of the patient (Double)
     * @param birth - birthdate of the patient (Date)
     * @param address - address of the patient (String)
     * @param number - phone number of the patient (String)
     * @param bloodType - blood type of the patient (String)
     * @param bloodPressure - blood pressure of the patient (String)
     * @param gender - gender of the patient (String)
     * @param dateAdmitted - date of the patient admitted (Date)
     * @param psychiatristName - psychiatrist name of the patient (String)
     * @param remarks - remarks or notes of a patient (String)
     * @param picLocation - the file location of a profile pic (String)
     */
    public StaffRecord(String name, int age, double height, double weight, Date birth, String gender, String number, String position, String picLocation) {
        setName(name);
        setAge(age);
        setBirth(birth);
        setHeight(height);
        setGender(gender);
        setWeight(weight);
        setNumber(number);
        setPosition(position);
        setPicLocation(picLocation);
    }

    @Override
    public String getType() {
        return "STAFF";
    }
}
