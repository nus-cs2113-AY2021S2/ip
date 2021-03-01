package duke.tasks;

import static duke.common.Constants.DEFAULT_STATUS_AS_INT;
import static duke.common.Constants.DONE_STATUS_AS_INT;

/**
 * Represents a to-do task with its description, status of whether it is done, and the
 * letter representation of its task type.
 * This is also the base class for all three task types in this application.
 */
public class Task {
    private String item;
    private String status;
    private String type;

    public Task(String item) {
        this.item = item;
    }

    public void printTask() {
        System.out.print("[" + this.getType() + "] "
                + "[" + this.getStatus() + "] "
                + this.getItem());
    }

    /**
     * Formats this {@code Task} as a single string for saving in the .txt storage file, where
     * {@code statusAsInt} indicates if the task is done (1 for done, and 0 for otherwise).
     * <br>Example: new {@code TodoCommand} command with args "final essay" yields "T | 0 | final essay"
     * @return formatted string
     */
    @Override
    public String toString() {
        int statusAsInt = this.getStatus().equals("X") ? DONE_STATUS_AS_INT : DEFAULT_STATUS_AS_INT;
        return this.getType() + " | "
                + statusAsInt + " | "
                + this.getItem();
    }

    public String getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }
}
