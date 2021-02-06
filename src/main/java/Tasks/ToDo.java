package Tasks;
public class ToDo extends Task {

    public ToDo() {};
    public ToDo(String description) {
        super(description);
    }

    public String getPrintedLine() {
        return "[T]" + super.getPrintedLine();
    }
}
