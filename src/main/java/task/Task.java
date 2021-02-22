package task;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String Description) {
        this.description = Description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718] "); //return tick or X symbols
    }

    public String toString() {
        return getStatusIcon()  + description;
    }

    public boolean setDone() {
        return isDone = true;
    }
}