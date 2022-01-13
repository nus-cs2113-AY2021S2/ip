package duke.task;

public class Task {

    protected Boolean isDone;
    protected String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        if (isDone == false) {
            return "N";
        }
        return "Y";
    }

    public void printStatus() {
        if (isDone) {
            System.out.print("[X]");
        } else {
            System.out.print("[ ]");
        }
    }

    public void printDescription() {
        printStatus();
        System.out.print(description);
    }

    public void markAsDone() {
        this.isDone = true;
    }

}


