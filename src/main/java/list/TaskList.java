package list;

import exceptions.IllegalTaskRedoException;

public class TaskList extends duke {
    protected String description;
    protected boolean isDone;


    public TaskList() {
        super(null);
    }

    public void printTask() {
        System.out.println("[" + getStatusIcon() + "]" + getTaskDescription());
    }

    public String getStatusIcon() {
        return ((getIsTaskDone()) ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean getIsTaskDone() {
        return (this.isDone);
    }

    public String getTaskDescription() {
        return (this.description);
    }

    public void markAsDone() throws IllegalTaskRedoException {
        if (this.isDone) {
            throw new IllegalTaskRedoException();
        }
        setDone();
        System.out.println("Nice! I've marked this task as done:");
        this.printTask();
        this.printDottedLines();
    }

    public void setDone() {
        this.isDone = true;
    }

    public void printAddedTask() {
        System.out.println("Added " + getTaskDescription());
    }

    public String getTaskToPrintInFile() {
        return getTaskDescription();
    }
}