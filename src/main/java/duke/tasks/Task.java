package duke.tasks;

public class Task {
    public static final String NO_DESCRIPTION = "!!! Please specify the task description !!!";
    public static final String NO_DATE = "!!! Please specify the task date !!!";
    public static final String NO_INDEX = "!!! Please specify the task index !!!";
    public static final String EVENT_USAGE = "event [event description] /at [date]";
    public static final String DEADLINE_USAGE = "deadline [task description] /by [due date]";
    public static final String TODO_USAGE = "todo [task description]";
    public static final String DONE_USAGE = "done [index of task]";
    public static final String DELETE_USAGE = "delete [index of task]";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType() {
        return " ";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return null;
    }

    public String getDetails() {
        return null;
    }

    public String printDate() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public boolean hasDate() {
        return false;
    }

}
