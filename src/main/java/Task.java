public class Task {
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

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
