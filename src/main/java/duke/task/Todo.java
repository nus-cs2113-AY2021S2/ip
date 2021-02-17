package duke.task;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getData() {
        return String.format("%c;%d;%s", getTaskChar(), (isDone?1:0), taskName);
    }

    public static char getTaskChar() {
        return 'T';
    }
}
