package duke;

import java.io.IOException;

public class Ui {


    static void listBeginMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    static void findTaskMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    static void deletedTaskMessage(int deletedTaskIndex) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(TaskList.tasks.get(deletedTaskIndex));
        System.out.println("Now you have " + (TaskList.maxTaskIndex - 1) + " tasks in the list." + "\n");
    }

    static void completedTaskMessage(int completedTaskIndex) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + TaskList.tasks.get(completedTaskIndex).getStatusIcon() + "] " + TaskList.tasks.get(completedTaskIndex).getDescription() + "\n");
    }

    static void welcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    static void confirmNewTaskMessage() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(TaskList.tasks.get(TaskList.maxTaskIndex));

        int numTasks = getNumTasks();
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    private static int getNumTasks() {
        return TaskList.maxTaskIndex + 1;
    }

    static void showLoadingError(IOException e) {
        System.out.println("Can't load from file: " + e.getMessage());
    }

    static void showTaskIndexNotExistsError() {
        System.out.println("OOPS!!! You need to add valid Task Index to be done or deleted!!");
    }

    static void showInvalidIntegerTaskIndexError() {
        System.out.println("OOPS!!! I don't recognise the number to process Task Index to be done or deleted!!");
    }

    static void showInvalidDeadlineTimeError() {
        System.out.println("OOPS!!! You need to add time for new Deadline with keyword '/by'!!");
    }

    static void showInvalidEventTimeError() {
        System.out.println("OOPS!!! You need to add time for new Event with keyword '/at'!!");
    }

    static void showNoTimeAddedError() {
        System.out.println("OOPS!!! You need to add time for new Event or Deadline with '/at' or '/by'!!");
    }

    static void showEmptyInputError() {
        System.out.println("OOPS!!! The description of a new task cannot be empty.");
    }

    static void showInvalidCommandError() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
