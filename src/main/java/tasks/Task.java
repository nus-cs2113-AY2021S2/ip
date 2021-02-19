package tasks;

import common.Constants;

public abstract class Task {

    protected static final Constants constants = new Constants();

    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        setDone(false);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public boolean getDone() {
        return isDone;
    }


    /**
     * Prints task status.
     */
    public void printStatus() {
        if (isDone) {
            System.out.print("[✓] " + name);
        } else {
            System.out.print("[ ] " + name);
        }
    }


    /**
     * Outputs formatted data for saving.
     */
    abstract public String formatData();
}
