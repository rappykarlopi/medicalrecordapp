package com.example.medicalrecordapp;

public class PatientRecord extends RecordTemplate {

    public PatientRecord() {
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
    public PatientRecord(String name, int age, double height, double weight, Date birth, String address,
                         String number, String bloodType, String bloodPressure, String gender, Date dateAdmitted,
                         String psychiatristName, String remarks, String picLocation) {
        setAge(age);
        setName(name);
        setWeight(weight);
        setHeight(height);
        setBirth(birth);
        setAddress(address);
        setNumber(number);
        setBloodType(bloodType);
        setBloodPressure(bloodPressure);
        setGender(gender);
        setDateAdmitted(dateAdmitted);
        setPsychiatristName(psychiatristName);
        setRemarks(remarks);
        setPicLocation(picLocation);
    }


    @Override
    public String getType() {
        return "PATIENT";
    }
}
