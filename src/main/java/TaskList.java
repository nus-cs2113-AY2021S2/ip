import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void addTask(Task inputTask) {
        tasks.add(inputTask);
        System.out.println(String.format("Got it. I've added this task:\n  %1$s \nNow you have %2$d tasks in the list.",
                inputTask.toString(),
                tasks.size()));
    }

    public void updateCompletion(int taskIndex) {
        tasks.get(taskIndex - 1).setDone();
        System.out.println(String.format("Nice! I've marked this task as done:\n  %s",
                tasks.get(taskIndex - 1).toString()));
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%1$d. %2$s",
                    i + 1,
                    tasks.get(i)));
        }
    }

}
