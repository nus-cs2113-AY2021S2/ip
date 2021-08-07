package duke.task;

public class Todo extends Task {
    private static final String TODO_TYPE = "T";

    public Todo (String errand) {
        super(errand);
    }

    @Override
    public String getTaskType() {
        return TODO_TYPE;
    }
}
