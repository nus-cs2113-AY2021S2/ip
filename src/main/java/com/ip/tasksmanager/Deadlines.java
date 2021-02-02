package com.ip.tasksmanager;

public class Deadlines extends Tasks {

    protected String date;

    public Deadlines(String description, String date) {
        super(description);
        this.typeOfTask = "D";
        this.date = date;
    }

    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String convertToTaskOutputString() {
        return "[" + getTypeOfTask() + "]" + super.convertToTaskOutputString()  + "(" + "by: " + this.date + ")";
    }

}
