public class Task {
    protected String Description;
    protected boolean isDone;
    protected Integer ID;

    public Task(String Description, Integer ID) {
        this.Description = Description;
        this.isDone = false;
        this.ID = ID;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatus() {
        String Status = "[" + getStatusIcon() + "]";
        return Status;
    }

    public String getDescription() {
        return Description;
    }

    public String getID() {
        return String.valueOf(ID);
    }

}
