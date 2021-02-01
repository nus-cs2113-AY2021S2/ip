public class TaskManager {

    private static final String INVALID_COMMAND_MESSAGE = "Invalid command. Please try again.";

    private Task[] tasks = new Task[100];
    int taskCount = 0;

    public String listTask() {
        StringBuilder feedback = new StringBuilder();

        for (int i=0; i<taskCount-1; ++i) {
            feedback.append(String.format("%d: %s", (i + 1), tasks[i])).append(System.lineSeparator());
        }
        feedback.append(String.format("%d: %s", (taskCount), tasks[taskCount-1]));

        return feedback.toString();
    }

    public String addTask(String taskType, String description) {
        switch (taskType) {
        case "todo":
            tasks[taskCount] = new Todo(description);
            break;
        case "deadline": {
            String[] nameAndDate = parseDescription(description, " /by ");
            tasks[taskCount] = new Deadline(nameAndDate[0], nameAndDate[1]);
            break;
        }
        case "event": {
            String[] nameAndDate = parseDescription(description, " /at ");
            tasks[taskCount] = new Event(nameAndDate[0], nameAndDate[1]);
            break;
        }
        default:
            return INVALID_COMMAND_MESSAGE;
        }

        ++taskCount;

        return "Got it. I've added this task:" + System.lineSeparator()
                + tasks[taskCount-1] + System.lineSeparator()
                + "Now you have " + taskCount + " tasks in the list.";
    }

    private String[] parseDescription(String description, String regex) {
        final String[] split = description.split(regex);
        if(split.length == 2){
            return split;
        }else{
            return new String[]{split[0], ""};
        }
    }

    public String doneTask(int taskNum) {
        tasks[taskNum].setAsDone();

        return "Nice! I've marked this task as done:" + System.lineSeparator()
                + tasks[taskNum];
    }
}
