package com.ip.tasksmanager;

public class Deadlines extends Tasks {

    protected String date;

    public Deadlines(String description, String date) {
        super(description);
        this.date = date;
        this.typeOfTask = "D";
    }

    //get deadline:
    public String getDate() {
        return this.date;
    }

    @Override
    public String convertToTaskOutputString() {
        return super.convertToTaskOutputString()  + "(" + "by: " + this.date + ")";
    }

}
