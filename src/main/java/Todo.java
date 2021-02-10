public class Todo {
    protected String description;
    protected boolean isDone;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return "[T] ["+ (this.isDone ? "X" : " ") + "] "
                + this.description;
    }
}