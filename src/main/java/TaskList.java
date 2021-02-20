import duke.Task;

public class TaskList {

    protected static void removeTask(int delete_index, int index, Task[] tasks) {
        if (delete_index - 1 == index) {
        } else {
            for (int i = delete_index - 1; i <= index; i++) {
                tasks[i] = tasks[i + 1];
            }
        }
        index -= 1;
    }

    protected static void removeTaskMessage(int index, Task tasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + tasks.toString());
        System.out.println("Now you have " + (index - 1) + " tasks in the list.");
    }

    protected static int addTaskMessage(int index, Task tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + tasks.toString());
        System.out.println("Now you have " + (index + 1) + " tasks in the list. ");
        index++;
        return index;
    }

    protected static void markAsDone(int index, Task[] tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index].toString());
    }

    protected static void showAllTasks(int index, Task[] tasks) {
        try {
            for (int i = 0; i < index; i++) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        } catch (NullPointerException e) {
            // do not show error
        }
    }
}
