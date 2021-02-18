package duke;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public void printTask() {
        System.out.printf("[T][%s] %s\n",super.getStatusIcon(), super.getDescription().substring(5));
    }
}
