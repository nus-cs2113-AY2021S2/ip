package duke.ui;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static final String DIVIDER_LINE = "---------------------------------------------------------";

    private Scanner in;

    /**
     * Constructor of Ui Class
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Returns String of next line of user input
     *
     * @return String of user input
     */
    public String readInput() {
        return in.nextLine();
    }

    /**
     * Prints divider line made of dashes
     */
    public void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints Duke logo and welcome message
     */
    public void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(DIVIDER_LINE);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER_LINE);
    }

    /**
     * Prints farewell message when exiting Duke
     */
    public void bidGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out tasks in TaskList
     *
     * @param tasks ArrayList of tasks
     */
    public void printList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++){
            System.out.print(i + ".");
            System.out.println(tasks.get(i-1).toString());
        }
    }

    /**
     * Prints confirm message that task has been added
     *
     * @param tasks ArrayList of tasks
     */
    public void printAddTaskMessage(ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    /**
     * Prints warning message if user input does not contain valid command
     */
    public void printInvalidCommandMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }


}
