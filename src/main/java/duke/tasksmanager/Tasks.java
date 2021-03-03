package duke.tasksmanager;

/**
 * Tasks Class: Parent of subsequent subtask classes
 * for storing the typeOfTask, status of Task, taskName, Date
 */
public class Tasks {

    public String description;
    public boolean isDone;
    public String typeOfTask;
    public String date;

    public Tasks(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or blank space
    }

    public String getDescription() {
        return this.description;
    }

    //common output for 'todo', 'deadlines', 'events' + 'tasks' + 'done' command:
    public String convertToTaskOutputString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
