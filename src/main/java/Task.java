public class Task {
    private String description;
    private boolean isDone;

    public Task() {
        this("", false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        if(isDone) {
            return "[\u2713]";
        }
        return "[\u2718]";
    }
}
