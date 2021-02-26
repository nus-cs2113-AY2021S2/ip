package duke.tasks;

public class Task {
    private final int DEFAULT_STATUS_AS_INT = 0;
    private final int DONE_STATUS_AS_INT = 1;
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
        return this.getType() + " | " + statusAsInt + " | "
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
