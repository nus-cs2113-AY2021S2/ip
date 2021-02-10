package duke.tasksmanager;

public class Tasks {

    protected String description;
    protected boolean isDone;
    protected String typeOfTask;

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : " "); //return tick or blank space
    }

    public String getDescription() {
        return this.description;
    }

    //common output for 'todo', 'deadlines', 'events' + 'tasks' + 'done' command:
    public String convertToTaskOutputString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
