import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> taskList = new ArrayList<>();
    private static final String BORDER = "\t____________________________________________________________\n";

    private static final int ARRAY_INDEX_FOR_DESCRIPTION = 0;
    private static final int ARRAY_INDEX_FOR_DATE = 1;

    public void toDo (String description) {
        Task task = new ToDo(description);
        taskList.add(task);
        printTaskMessage(task);
    }

    public void deadLine (String[] taskAndDeadline) {
        String description = taskAndDeadline[ARRAY_INDEX_FOR_DESCRIPTION];
        String by = taskAndDeadline[ARRAY_INDEX_FOR_DATE];
        Task task = new Deadline(description, by);
        taskList.add(task);
        printTaskMessage(task);
    }

    public void event (String[] eventAndDate) {
        String taskEvent = eventAndDate[ARRAY_INDEX_FOR_DESCRIPTION];
        String at = eventAndDate[ARRAY_INDEX_FOR_DATE];
        Task task = new Event(taskEvent, at);
        taskList.add(task);
        printTaskMessage(task);
    }

    public void printTaskMessage(Task task) {
        System.out.print(BORDER + "\t Got it. I've added this task:\n");
        System.out.print("\t   " + task + "\n");
        System.out.print("\t Now you have " + taskList.size() + " tasks in the list.\n"
                + BORDER);
    }

    public void printExitMessage() {
        System.out.print(BORDER + "\t Good Bye. Hope to see you again soon!" + "\n" + BORDER);
    }

    public void listTasks() {
        System.out.print(BORDER + "\tHere are the task in your list:\n");
        for (int i = 0; i < taskList.size(); ++i) {
            int indexOfList = i+1;
            System.out.print("\t" + indexOfList + "." + taskList.get(i) + "\n");
        }
        System.out.print(BORDER);
    }

    public void markAsDone(int taskNumber) {
        int SizeOfTaskList = taskList.size();
        if (taskNumber > SizeOfTaskList) {
            System.out.print(BORDER +"\t Invalid command. Please enter another task number.\n" + BORDER);
        } else {
            int taskIndex = taskNumber -1;
            taskList.get(taskIndex).setAsDone();
            System.out.print(BORDER + "\t Nice! I've marked this as done:\n");
            System.out.print("\t [" + taskList.get(taskIndex).getStatusIcon() + "] " +
                    taskList.get(taskIndex).getDescription() + "\n" + BORDER);
        }
    }
    public void echo(String userInput) {
        System.out.print(BORDER + "\t" + userInput + "\n" + BORDER);
    }
}
