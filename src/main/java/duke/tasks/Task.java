package duke.tasks;

/**
 * Represents an abstract {@code Task} object that serves as a template for more specific {@code Task} types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    
    /** Task is initially not full. Updated each time list capacity is checked. */
    public static boolean isFull = false;

    /** Task description not guaranteed to be filled. */
    public Task() {
        this.description = null;
        this.isDone = false;
    }

    /** Task is initialised with input string and set to not done. */
    public Task(String inputJob) {
        this.description = inputJob;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Represents a {@code Task} object as a string and prints it. The done status of a task is represented 
     * by a box with an 'X' to show completion.
     */
    public void printTask() {
        String doneBox = "[X] ";
        String emptyBox = "[ ] ";

        String output = this.isDone ? doneBox : emptyBox;
        output += this.description;

        output = addLabel(output);
        output = addEnd(output);

        System.out.println(output);
    }

    /** 
     * Adds suffix to string representation of {@code Task} objects to display additional information.
     */
    public abstract String addEnd(String s);

    /**
     * Adds prefix to string representation of {@code Task} objects to identify the type of task.
     * (e.g. todo is labeled with a T in a box: {@code "[T]"})
     */
    public abstract String addLabel(String s);

}


