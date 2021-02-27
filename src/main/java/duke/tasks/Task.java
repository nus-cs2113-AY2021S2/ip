package duke.tasks;

import static duke.common.Constants.DEFAULT_STATUS_AS_INT;
import static duke.common.Constants.DONE_STATUS_AS_INT;

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
