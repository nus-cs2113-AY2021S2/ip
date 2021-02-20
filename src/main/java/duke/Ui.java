package duke;

import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        showLine();
    }


    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().trim();
    }

    public static void showLine() {
        System.out.println("------------------------------------------");
    }

    public static void printExitMessage() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public static void printTasks(ArrayList<Task> tasks) {
        for (int i=0; i<tasks.size(); i++) {
            System.out.println("    " + (i+1) + ". " + tasks.get(i));
        }
    }

    public static void printTaskList() {
        System.out.println("    Here are the tasks in your list: ");
        printTasks(TaskList.getTasks());
        showLine();
    }

    public static void printMatchingTasks(String task) {
        System.out.println("    Here are the matching tasks in your list: ");
        printTasks(TaskList.findTask(task));
        showLine();
    }

    public static void printAddTaskMessage(ArrayList<Task> tasks, int tasksCount) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("      " + tasks.get(tasksCount));
        System.out.println("    Now you have " + (tasksCount + 1) + " tasks in the list.");
        showLine();
    }

    public static void printMarkTaskDoneMessage(Task task) {
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + task);
        showLine();
    }

    public static void printDeleteTaskMessage(Task task, int tasksCount) {
        System.out.println("    Noted. I've removed this task: ");
        System.out.println("    " + task);
        System.out.println("    Now you have " + tasksCount + " tasks in the list.");
        showLine();
    }
}

