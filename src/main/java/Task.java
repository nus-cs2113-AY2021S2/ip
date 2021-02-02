public class Task {
    protected String Description;
    protected boolean isDone;
    protected Integer ID;

    public Task(String Description) {
        this.Description = Description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "]";
    }

    public String getDescription() {
        return Description;
    }

    public String toString() {
        return getStatus() + " " + getDescription();
    }


}

class Deadline extends Task {
    protected String by;

    public Deadline(String Description, String by) {
        super(Description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatus() + " " + super.getDescription() + " (by: " + by + ")";
    }

}

class ToDo extends Task {
    protected String by;

    public ToDo(String Description) {
        super(Description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatus() + " " + super.getDescription();
    }
}

class Event extends Task {
    protected String at;

    public Event(String Description, String at) {
        super(Description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatus() + " " + super.getDescription() + " (at: " + at + ")";
    }
}