public class Todo extends Task{

    public static final String TODO_LABEL = "[T]";

    public Todo(String description) {
        super(description);
    }

    public static void checkIfToDoDescriptionExists(String input) throws TaskDescriptionMissingException {
        if (input.isBlank()){
            Task.decreaseTaskCount();
            throw new TaskDescriptionMissingException();
        }
    }

    @Override
    public String toString() {
        return TODO_LABEL + super.toString();
    }
}
