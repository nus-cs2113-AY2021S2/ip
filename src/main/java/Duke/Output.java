package Duke;

import Duke.Tasks.Task;

import java.util.ArrayList;

public class Output {

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreet() {
        printLine();
        System.out.println("Hello! I'm Duke");
        printLogo();
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printDeleted(String taskName, int size) {
        printLine();
        System.out.println(String.format("Noted. I've removed this task:\n  %1$s \nNow you have %2$d tasks in the list.",
                taskName,
                size));
        printLine();
    }

    public static void printUnknownCommandError() {
        printLine();
        System.out.println("\u2639 OOPS!! I'm sorry, but I don't know what that means :-(");
        printLine();
    }

    public static void printAdded(Task newTask, int size) {
        printLine();
        System.out.println(String.format("Got it. I've added this task:\n  %1$s \nNow you have %2$d tasks in the list.",
                newTask.toString(),
                size));
        printLine();
    }

    public static void printUpdated(String taskName) {
        printLine();
        System.out.println(String.format("Nice! I've marked this task as done:\n  %s",
                taskName));
        printLine();
    }

    public static void printTask(Task task) {
        System.out.println(task);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        printLine();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(String.format("%d. ", i + 1));
            printTask(taskList.get(i));
        }
        printLine();
    }

    public static void printDoneRangeError() {
        printLine();
        System.out.println("\u2639 OOPS!! The number you provide is out of range for the \"done\" command.");
        printLine();
    }

    public static void printDoneInputError() {
        printLine();
        System.out.println("\u2639 OOPS!! \"done\" command must be followed by an integer.");
        printLine();
    }

    public static void printExit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

}