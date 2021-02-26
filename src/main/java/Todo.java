public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T] ["+ (this.isDone ? "X" : " ") + "] "
                + this.getDescription() ;
    }
}