package duke.tasks;

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
        int statusAsInt = this.getStatus() == "X" ? 1 : 0;
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
