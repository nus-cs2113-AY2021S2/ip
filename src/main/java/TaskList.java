import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void updateCompletion(int taskIndex) {
        tasks.get(taskIndex - 1).isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[\u2713] " + tasks.get(taskIndex - 1));
    }

    public void addTask(Task inputTask) {
        tasks.add(inputTask);
        System.out.print("added: ");
        System.out.println(inputTask.name);
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            if (tasks.get(i).isDone) {
                System.out.print(". [\u2713] ");
            }
            else {
                System.out.print(". [\u2715] ");
            }
            System.out.println(tasks.get(i));
        }
    }

}