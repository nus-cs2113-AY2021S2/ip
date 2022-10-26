package tasklist;

public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    public String getStatusIcon() {
        return "[T]" + "[" + super.getStatusIcon() + "]";
    }


    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
