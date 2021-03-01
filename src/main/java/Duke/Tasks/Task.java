package Duke.Tasks;

import java.util.Collection;

/*
@The task class is an abstract class that holds the method to be used for the various task, Deadline, Todo, Event;
@ As such, abstract methods can be used as well like saveTask to obtain the format of the string to be saved or getDescription to
@ get the description of the task
*/

public abstract class Task {
    protected String description;
    protected String by;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String saveTask();

    public abstract String getDescription();

}