package com.ip.tasksmanager;

public class Events extends Tasks {

    protected String date;

    public Events(String description, String date) {
        super(description);
        this.date = date;
        this.typeOfTask = "E";
    }

    //get deadline:
    public String getDate() {
        return this.date;
    }

    @Override
    public String convertToTaskOutputString() {
        return super.convertToTaskOutputString() + "(" + "at: " + this.date + ")";
    }

}
