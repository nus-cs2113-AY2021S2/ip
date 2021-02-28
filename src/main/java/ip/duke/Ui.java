package ip.duke;

import ip.duke.task.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Prints the Duke logo and the greeting message when the program starts to run.
     */
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

    /**
     * Prints an error message when there is no data stored in the text file to be loaded.
     */
    public static void showLoadingError() {
        printLine();
        System.out.println("There is no existing data to load... ");
        printLine();
    }

    /**
     * Prints the corresponding error message for different type of invalid inputs.
     *
     * @param input the invalid input which is not a command or does not provide enough information
     */
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

    /**
     * Prints a message to confirm the task updated successfully by printing the formatted task
     * and the current number of tasks in the updated task list.
     *
     * @param task the new task to be updated into the task list
     */
    public static void printRecordMessage(Task task) {
        printLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (TaskList.getSize()) + " tasks in the list. ");
        printLine();
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param list the task list to be displayed
     */
    public static void printList(ArrayList<Task> list) {
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ". " + list.get(i - 1).toString());
        }
        printLine();
    }

    /**
     * Prints the list of tasks that matches the find input.
     *
     * @param foundList the list of tasks that contains the finding keyword
     */
    public static void printFoundList(ArrayList<Task> foundList) {
        printLine();
        System.out.println("Here are the matching tasks in your list: ");
        printList(foundList);
    }

    /**
     * Prints the list of tasks that matches the find input.
     *
     * @param dateList the list of tasks that contains the finding keyword
     */
    public static void printDateList(ArrayList<Task> dateList) {
        printLine();
        System.out.println("Here are the tasks occurring on this specific date: ");
        printList(dateList);
    }

    /**
     * Prints a message to confirm marking a task as done successfully by printing the formatted task
     * and the updated task status.
     *
     * @param task the task to be marked as done
     */
    public static void printDoneMessage(Task task) {
        printLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
        printLine();
    }

    /**
     * Prints a message to confirm deleting a task successfully by printing the formatted task
     * and the current number of tasks in the task list.
     *
     * @param task the task to be marked as done
     */
    public static void printDeletedMessage(Task task) {
        printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " + (TaskList.getSize() - 1) + " tasks in the list.");
        printLine();
    }

    /**
     * Prints an error message if the task matches the find input cannot be found.
     */
    public static void printEmptyMessage() {
        printLine();
        System.out.println("There is no matching task in your list.");
        System.out.println("Please input another keyword! :)");
        printLine();
    }

    /**
     * Prints an error message if the task matches the find input cannot be found.
     */
    public static void printNoMessage() {
        printLine();
        System.out.println("There is no task occurring on this specific date.");
        System.out.println("Please input another date! :)");
        printLine();
    }

    /**
     * Prints the bye message before the program ends.
     */
    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

}
