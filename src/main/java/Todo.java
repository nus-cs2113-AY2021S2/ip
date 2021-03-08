public class Todo extends Task {
    protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getType(){
        return "T";
    }
    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description;
    }

    @Override
    public String toFileString() {
        return getType() + " |" + getStatusNum() +" |" + description;
    }
}