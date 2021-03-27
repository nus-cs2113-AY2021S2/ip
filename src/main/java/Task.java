public class Task {
    protected String description;
    protected boolean isDone;
    protected String SYMBOL;
    protected String ARROW = " ==> ";

    /**
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return getStatusIcon() + this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] " ); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String stringToSave() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }
}
