package ip.duke;

import ip.duke.task.Task;

import java.util.ArrayList;

public class Ui {

    public static void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        printLine();
    }

    public static void showLoadingError() {
        printLine();
        System.out.println("There is no existing data to load... ");
        printLine();
    }

    public static void printInvalidInputWarnings(String input) {
        printLine();
        switch (input) {
        case "todo":
        case "deadline":
        case "done":
        case "delete":
            System.out.println("🙁 OOPS!!! The description of a " + input + " cannot be empty.");
            break;
        case "event":
            System.out.println("🙁 OOPS!!! The description of an event cannot be empty.");
            break;
        default:
            System.out.println("🙁 OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
        System.out.println("Please input again!:)");
        printLine();
    }

    public static void printLine() {
        System.out.println("___________________________________________");
    }

    public static void printRecordMessage(Task task) {
        printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (TaskList.getSize()) + " tasks in the list. ");
        printLine();
    }

    public static void printList(ArrayList<Task> list) {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1).toString());
        }
        printLine();
    }

    public static void printFoundList(ArrayList<Task> foundList) {
        printLine();
        System.out.println("Here are the matching tasks in your list: ");
        printList(foundList);
    }

    public static void printDoneMessage(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
        printLine();
    }

    public static void printDeletedMessage(Task task) {
        printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (TaskList.getSize() - 1) + " tasks in the list.");
        printLine();
    }

    public static void printEmptyMessage() {
        printLine();
        System.out.println("🙁 OOPS!!! The is no matching task in your list.");
        System.out.println("Please input another keyword! :)");
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

}
