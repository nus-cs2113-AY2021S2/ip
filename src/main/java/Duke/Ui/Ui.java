package Duke.Ui;

import Duke.Exceptions.EmptyTaskListsException;
import Duke.Tasks.Task;
import Duke.Tasks.TaskList;

import java.util.ArrayList;


public class Ui {
    private final String GREETING_LINES =
            "Hello! I'm Duke" + "\n"
                    + "What can I do for you?";


    public static void printAddTask(ArrayList<Task> tasksLists, Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        if (tasksLists.size() == 1) {
            System.out.println("Now you have " + tasksLists.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasksLists.size() + " tasks in the list.");
        }
    }

    public void showWelcome() {
        System.out.println(GREETING_LINES);
    }


    public void showLine() {
        System.out.println("--------------------------------------------");
    }

    public static String printDone(ArrayList<Task> tasksLists, int num) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasksLists.get(num - 1).toString());
        return "Nice! I've marked this task as done:";
    }

    public static String goodBye() {
        return "Bye. Hope to see you again soon!";
    }

    public static String printDelete(Task toDelete, ArrayList<Task> tasksLists) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + toDelete);
        System.out.println("Now you have " + tasksLists.size() + " tasks in the list.");

        return "Task deleted!";
    }

    public static void printTasksLists(TaskList tasks) throws EmptyTaskListsException {
        if (tasks.size() == 0) throw new EmptyTaskListsException();
        else {
            System.out.println("These are the tasks on the list");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i).toString());
            }
        }
    }

    public static void printFind(ArrayList<Task> tasksLists) {
        if (tasksLists.size() == 0) {
            System.out.println("Could not find task matching search word!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasksLists.size(); i++) {
                System.out.println((i + 1) + ". " + tasksLists.get(i));
            }
        }
    }
}
