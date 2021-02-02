package com.ip.tasksmanager;

public class Tasks {

    protected String description;
    protected boolean isDone;
    protected String typeOfTask;

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or blank space
    }

    public void markAsDone() {
        this.isDone = true;
    }

    //common output for 'todo', 'deadlines', 'events' + 'tasks' + 'done' command:
    public String convertToTaskOutputString() {
        return "[" + this.getTypeOfTask() + "]" + "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
