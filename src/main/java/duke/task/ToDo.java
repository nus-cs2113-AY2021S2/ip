package duke.task;

public class ToDo extends Task {

    public ToDo(String taskName, String taskType) {
        super(taskName, taskType);
    }

    @Override
    public void printTask() {
        String output;
        if (isCompleted) {
            output = "[T][X] " + taskName;
        } else {
            output = "[T][ ] " + taskName;
        }
        System.out.println(output);
    }
}

