package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> taskList = new ArrayList<>();
    private static final String BORDER = "\t________________________________________________________________\n";

    private static final int ARRAY_INDEX_FOR_DESCRIPTION = 0;
    private static final int ARRAY_INDEX_FOR_DATE = 1;
    private static final int EXTRA_INDEX_FOR_ARRAY = 1;

    public void addToDo (String description) {
        Task task = new ToDo(description);
        taskList.add(task);
        printAddTaskMessage(task);
    }

    public void addDeadline (String[] taskAndDeadline) {
        String description = taskAndDeadline[ARRAY_INDEX_FOR_DESCRIPTION];
        String by = taskAndDeadline[ARRAY_INDEX_FOR_DATE];
        Task task = new Deadline(description, by);
        taskList.add(task);
        printAddTaskMessage(task);
    }

    public void addEvent (String[] eventAndDate) {
        String taskEvent = eventAndDate[ARRAY_INDEX_FOR_DESCRIPTION];
        String at = eventAndDate[ARRAY_INDEX_FOR_DATE];
        Task task = new Event(taskEvent, at);
        taskList.add(task);
        printAddTaskMessage(task);
    }

    public void printAddTaskMessage(Task task) {
        System.out.print(BORDER
                + "\t Got it. I've added this task:\n"
                + "\t   " + task + "\n"
                + "\t Now you have " + taskList.size() + " tasks in the list.\n"
                + BORDER);
    }

    public void printExitMessage() {
        System.out.print(BORDER
                + "\t Good Bye. Hope to see you again soon!" + "\n"
                + BORDER);
    }

    public void listTasks() {
        System.out.print(BORDER + "\t Here are the task in your list:\n");
        for (int i = 0; i < taskList.size(); ++i) {
            int indexOfList = i+1;
            System.out.print("\t " + indexOfList + "." + taskList.get(i) + "\n");
        }
        System.out.print(BORDER);
    }

    public void markAsDone(String description) {
        int taskNumber = Integer.parseInt(description)-EXTRA_INDEX_FOR_ARRAY;
        taskList.get(taskNumber).setAsDone();
        System.out.print(BORDER
                + "\t Nice! I've marked this as done:\n"
                + "\t   " + taskList.get(taskNumber).toString() + "\n"
                + BORDER);
    }

    public void deleteTask(String description) {
        int taskNumber = Integer.parseInt(description)-EXTRA_INDEX_FOR_ARRAY;
        System.out.print(BORDER
                + "\t Noted! I've removed this task:\n"
                + "\t   " + taskList.get(taskNumber).toString() + "\n");
        taskList.remove(taskNumber);
        System.out.print("\t Now you have " + taskList.size() + " tasks in the list.\n"
                + BORDER);
    }
}
