package duke.ui;

import duke.task.Task;

import java.util.List;

public class TextUI {

    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
    }

    public void showExitMessage() {
        System.out.println("\tBye fellow coder! Hope to see you again soon!");
    }

    public void printAddedTask(List<Task> tasks, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks.get(taskCounter).toString());
        int counter = taskCounter + 1;
        System.out.println("Now you have " + counter + " tasks in the list.\n");
    }

}
