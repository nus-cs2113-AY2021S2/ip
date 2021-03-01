package duke.task;


public class Todo extends Task {

    public Todo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String strAddToTxt() { return "T" + super.strAddToTxt(); }
}
