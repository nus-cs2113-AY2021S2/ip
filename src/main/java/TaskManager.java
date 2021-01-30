import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private static final String BORDER = "\t____________________________________________________________\n";

    public void addTask (String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.print(BORDER + "\t" + "added: " + description + "\n" + BORDER);
    }

    public void endTaskManager() {
        System.out.print(BORDER + "\t" + "Good Bye. Hope to see you again soon!" + "\n" + BORDER);
    }

    public void listTasks() {
        System.out.print(BORDER + "\tHere are the task in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            int index = i+1;
            System.out.print("\t" + index + "." + "[" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription() + "\n");
        }
        System.out.print(BORDER);
    }

    public void markAsDone(int taskNumber) {
        if (taskNumber > tasks.size()) {
            System.out.print(BORDER +"\t Invalid command. Please enter another task number\n" + BORDER);
        } else {
            taskNumber = taskNumber -1;
            tasks.get(taskNumber).setAsDone();
            System.out.print(BORDER + "\tNice! I've marked this as done:\n");
            System.out.print("\t [" + tasks.get(taskNumber).getStatusIcon() + "] " + tasks.get(taskNumber).getDescription() + "\n" + BORDER);
        }
    }
}
