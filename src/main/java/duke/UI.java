package duke;

import task.Task;

import java.util.Arrays;

public class UI {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void printStartUpMessage() {
        System.out.println(LOGO);
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void printTaskList(Task[] tasks, int taskCount) {
        Task[] taskList = Arrays.copyOf(tasks, taskCount);
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            System.out.printf("%d. %s%n", i + 1, taskList[i]);
        }
        System.out.println("____________________________________________________________");
    }

    static void addTaskSuccessfulMessage(Task taskToAdd, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskToAdd);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    static void taskMarkedAsDoneMessage(Task taskToMarkDone) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskToMarkDone);
        System.out.println("____________________________________________________________");
    }
}
