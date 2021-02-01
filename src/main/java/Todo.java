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
}
