package duke.tasks;

public class Todo extends Task{

    public Todo(String inputJob) {
        super(inputJob);
    }

    @Override
    public String addLabel(String s) {
        String label = "[T]";
        label += s;
        return label;
    }

    @Override
    public String addEnd(String s) {
        return s.concat("");
    }
}
