package duke.task;

public class Todo extends Task{

    //public String type = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
