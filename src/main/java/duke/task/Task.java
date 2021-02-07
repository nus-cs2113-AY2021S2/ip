package duke.task;

public class Task {
    // We set the default type icon to be *, which should be overwritten for child classes
    protected String typeIcon = "*";

    //@@author fsgmhoward-reused
    // Reused from https://nus-cs2113-ay2021s2.github.io/website/schedule/week3/project.html
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        // Return tick when done, or 'X' symbol when it is not done
        return (isDone ? "\u2713" : "\u2718");
    }
    //@@author

    public String getTypeIcon() {
        return typeIcon;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), getDescription());
    }
}
