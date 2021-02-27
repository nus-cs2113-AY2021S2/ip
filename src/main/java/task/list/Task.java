package task.list;

import ui.UI;
import exceptions.IllegalTaskRedoException;

/**
 * Represents a task in the list
 */
public class Task {

    public static final String DONE_ICON = "OK";
    public static final String NOT_DONE_ICON = "XX";
    public static final String MARKED_AS_DONE = "Nice! I've marked this task as done:";
    public static final String SPACE = " ";
    protected String description;
    protected boolean isDone;

    /**
     * prints a task from the list
     */
    public void printTask() {
        System.out.println("[" + getStatusIcon() + "]" + SPACE + getTaskDescription());
    }

    /**
     * returns the done status icon for a task
     *
     * @return appropriate status icon
     */
    public String getStatusIcon() {
        return ((getIsTaskDone()) ? DONE_ICON : NOT_DONE_ICON); //return tick or X symbols
    }

    /**
     * gets isDone for a task
     *
     * @return isDone for a task
     */
    public boolean getIsTaskDone() {
        return (this.isDone);
    }

    /**
     * gets description for a task
     *
     * @return task description
     */
    public String getTaskDescription() {
        return (this.description);
    }

    /**
     * marks a task as done
     *
     * @throws IllegalTaskRedoException for wrong input
     */
    public void markAsDone() throws IllegalTaskRedoException {
        if (this.isDone) {
            throw new IllegalTaskRedoException();
        }
        setDone();
        System.out.println(MARKED_AS_DONE);
        this.printTask();
        UI.printDottedLines();
    }

    /**
     * sets isDone as done for a task
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * prints the appropriate line when a task is added to the list
     */
    public void printAddedTask() {
        System.out.println("Added " + getTaskDescription());
    }

    /**
     * returns the appropriate task format to write to duke.txt
     *
     * @return task description to input in file
     */
    public String getTaskToPrintInFile() {
        return getTaskDescription();
    }
}