package task.list;

import ui.UI;
import exceptions.IllegalTaskRedoException;

public class TaskList {

    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2718";
    public static final String MARKED_AS_DONE = "Nice! I've marked this task as done:";
    protected String description;
    protected boolean isDone;

    public void printTask() {
        System.out.println("[" + getStatusIcon() + "]" + getTaskDescription());
    }

    public String getStatusIcon() {
        return ((getIsTaskDone()) ? TICK : CROSS); //return tick or X symbols
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
        System.out.println(MARKED_AS_DONE);
        this.printTask();
        UI.printDottedLines();
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