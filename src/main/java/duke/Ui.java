package duke;

import duke.task.Task;
import duke.TaskList;

public class Ui {

    private final static String END = "____________________________________________________________";
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

    public static void bye() {
        System.out.println("See you next time! BYE!");
        System.out.println(END);
    }


    public void showLoadingError() {
        System.out.println("ERROR!!!");
        System.out.println(END);
    }


    public static void taskAdded(Task task) {
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + task.toString());
        System.out.println("Now you have " + TaskList.numOfTasks + " task in the list.");
        System.out.println(END);
    }

    public static void taskDeleted(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + TaskList.numOfTasks + " tasks in the list.");
        System.out.println(END);
    }

    public static void taskDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
        System.out.println(END);
    }

    public static void commandDone() {
        System.out.println(END);
    }
}
