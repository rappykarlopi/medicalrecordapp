package com.example.medicalrecordapp;

public class Date {

    private String month;
    private int day, year;

    public Date() {
        // Default Constructor
    }

    /**
     * This is the custom constructor of the Date class
     */
    public Date(String month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;

    }

    /**
     * getMonth() method
     * - responsible for getting the month value
     * returns month - String data type
     * */
    public String getMonth() {
        return month;
    }

    /**
     * setMonth() method
     * - responsible for setting or changing the month variable
     * @param month - this should be a String data type
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * getDay() method
     * - responsible for getting the day value
     * returns day - Int data type
     * */
    public int getDay() {
        return day;
    }

    /**
     * setDay() method
     * - responsible for setting or changing the month variable
     * @param day - this should be an Int data type
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * getYear() method
     * - responsible for getting the day value
     * returns year - Int data type
     * */
    public int getYear() {
        return year;
    }

    /**
     * setYear() method
     * - responsible for setting or changing the month variable
     * @param year - this should be an Int data type
     */
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "" + month + "/" + day + "/" + year;
    }
}
