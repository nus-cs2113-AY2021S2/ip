public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[T][\u2713]" : "[T][\u2718]"); //return tick or X symbols
    }

}
