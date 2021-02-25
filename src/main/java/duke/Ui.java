package duke;

import duke.task.Task;

/**
 * Prints responses to user input
 */
public class Ui {

    private final static String END = "____________________________________________________________";

    /**
     * Greet user when first start of application.
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        System.out.println(END);

    }

    /**
     * Say good bye to user.
     */
    public static void bye() {
        System.out.println("See you next time! BYE!");
        System.out.println(END);
    }


    /**
     * Show loading error
     */
    public void showLoadingError() {
        System.out.println("ERROR!!!");
        System.out.println(END);
    }


    /**
     * Tell user that task has been added.
     *
     * @param task
     */
    public static void taskAdded(Task task) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + task.toString());
        System.out.println("Now you have " + TaskList.numOfTasks + " task in the list.");
        System.out.println(END);
    }

    /**
     * Tell user that task has been deleted.
     *
     * @param task
     */
    public static void taskDeleted(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + TaskList.numOfTasks + " tasks in the list.");
        System.out.println(END);
    }

    /**
     * Tell user that task has been marked as done.
     * @param task
     */
    public static void taskDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
        System.out.println(END);
    }

    /**
     * End of every command done.
     */
    public static void commandDone() {
        System.out.println(END);
    }
}
