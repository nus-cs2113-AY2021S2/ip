public class Task {
    protected String description;
    protected boolean isDone;
    protected Integer id;

    public Task(String description, Integer id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatus() {
        String s = "[" + getStatusIcon() + "]";
        return s;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return String.valueOf(id);
    }

}
