package duke.task;

public class Todo extends Task {
    private static final String TODO_TYPE = "T";

    public Todo (String errand) {
        super(errand);
    }

    @Override
    protected String getTaskType() {
        return TODO_TYPE;
    }
}
