package tasks;
public class ToDo extends Task {
    protected String type = "T";

    public ToDo() {};
    public ToDo(String description) {
        super(description);
    }

    public String getType() {
        return type;
    }

    public String getPrintedLine() {
        return "[T]" + super.getPrintedLine();
    }
}
