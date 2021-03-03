package taskclasses;

public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    @Override
    public String getTaskType() {
        return "T";
    }

}
