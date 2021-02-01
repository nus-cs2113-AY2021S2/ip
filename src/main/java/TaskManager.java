import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private static final String BORDER = "\t____________________________________________________________\n";

    public void toDo (String description) {
        ToDo task = new ToDo(description);
        tasks.add(task);
        printTaskMessage(task);
    }

    public void deadLine (String description, String by) {
        Deadline task = new Deadline(description, by);
        tasks.add(task);
        printTaskMessage(task);
    }

    public void event (String description, String at) {
        Event task = new Event(description, at);
        tasks.add(task);
        printTaskMessage(task);
    }

    public void printTaskMessage(Task task) {
        System.out.print(BORDER + "\t Got it. I've added this task:\n");
        System.out.print("\t   " + task + "\n");
        System.out.print("\t Now you have " + tasks.size() + " tasks in the list.\n"
                + BORDER);
    }

    public void printExitMessage() {
        System.out.print(BORDER + "\t Good Bye. Hope to see you again soon!" + "\n" + BORDER);
    }

    public void listTasks() {
        System.out.print(BORDER + "\tHere are the task in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            int index = i+1;
            System.out.print("\t" + index + "." + tasks.get(i) + "\n");
        }
        System.out.print(BORDER);
    }

    public void markAsDone(int taskNumber) {
        if (taskNumber > tasks.size()) {
            System.out.print(BORDER +"\t Invalid command. Please enter another task number\n" + BORDER);
        } else {
            taskNumber = taskNumber -1;
            tasks.get(taskNumber).setAsDone();
            System.out.print(BORDER + "\t Nice! I've marked this as done:\n");
            System.out.print("\t [" + tasks.get(taskNumber).getStatusIcon() + "] " + tasks.get(taskNumber).getDescription() + "\n" + BORDER);
        }
    }
    public void echo(String userInput) {
        System.out.print(BORDER + "\t" + userInput + "\n" + BORDER);
    }
}
