package duke.tasks;

/**
 * Represents a Task
 * This is the primary class to other subtask classes
 */
public class Task {

    private final String taskName;
    private boolean isDone;

    /** @param task contains the task name */
    public Task(String task) {
        this.taskName = task;
        setDone(false);
    }

    /**
     * @param isDone contains the current task's status
     * set the current task done status to true
     */
    public void setDone(boolean isDone) {

        this.isDone = isDone;
    }

    /** @return task name */
    public String getName() {

        return this.taskName;
    }

    /** @return done status */
    public boolean getDone() {

        return isDone;
    }

    /** prints the task status box */
    public void printStatus() {
        if (getDone()) {
            System.out.print("[" + "\u2713" + "] " + getName());
        } else {
            System.out.print("[" + "\u274C" + "] " + getName());
        }
    }
}