package tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        if (isDone) {
            return "T " + "Y " + description + "\n";
        }
        else {
            return "T " + "N " + description + "\n";
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatus() + " " + super.getDescription();
    }
}
