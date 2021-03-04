package Duke.Task;

public class DeadlineTask extends Task {


    private static final String COMMAND_DEADLINE_WORD = "deadline";


    public DeadlineTask(String taskDetail) {
        super(taskDetail);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getTaskType() {
        return COMMAND_DEADLINE_WORD;
    }

    @Override
    public String toString() {
        return "["+COMMAND_DEADLINE_WORD+"]" + super.toString();
    }

    @Override
    public void markAsDone() {
        super.isDone = true;
    }
}
