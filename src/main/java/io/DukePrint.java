package io;

import models.Task;
import models.TaskList;

import java.util.ArrayList;

public class DukePrint {
    private static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER =
            "~____________________________________________________________~";
    private static final String END_DIVIDER =
            "~____________________________________________________________~\n";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String ADD_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";
    private static final String BYE_MESSAGE = "Alright cheers mate!";
    private static final String FIND_MESSAGE = "Here are the matching tasks in your list:";

    /**
     * Prints the starting divider before every message
     */
    public void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints the ending divider after every message
     */
    public void printEndDivider() {
        System.out.println(END_DIVIDER);
    }

    /**
     * Prints the number of tasks in the TaskList
     *
     * @param numTasks Number of Tasks
     */
    public void printTaskSize(int numTasks) {
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Prints the DUKE Logo
     */
    public void printLogo() {
        System.out.println("Hello from\n" + LOGO);
    }

    /**
     * Prints Welcome message
     */
    public void printWelcome() {
        printDivider();
        System.out.println("What's up! I'm Duke");
        System.out.println("What can I do for you?");
        printEndDivider();
    }

    /**
     * Prints the Bye message
     */
    public void printBye() {
        System.out.println(BYE_MESSAGE);
    }

    /**
     * Prints the Add message
     *
     * @param addedTask Task to be added
     * @param numTasks  Number of Tasks
     */
    public void printAdd(Task addedTask, int numTasks) {
        System.out.println(ADD_MESSAGE);
        System.out.println(addedTask);
        printTaskSize(numTasks);
    }

    /**
     * Prints the Delete message
     *
     * @param deletedTask Task to be deleted
     */
    public void printDelete(Task deletedTask) {
        System.out.println(DELETE_MESSAGE);
        System.out.println(deletedTask);
    }

    /**
     * Prints the list of Tasks int he TaskList
     *
     * @param taskList TaskList object containing the Tasks
     */
    public void printList(TaskList taskList) {
        System.out.println(LIST_MESSAGE);
        if (taskList.getSize() != 0) {
            int i = 0;
            for (Task task : taskList.getTaskList()) {
                System.out.println(++i + ". " + task.toString());
            }
        } else {
            System.out.println("--(Task list is empty)--");
        }
    }

    /**
     * Prints the Mark As Done message
     *
     * @param doneTask Tasks to be marked as done
     */
    public void printDone(Task doneTask) {
        System.out.println(DONE_MESSAGE);
        System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.getDescription());
    }

    /**
     * Prints all the Tasks that contains the search keyword
     *
     * @param matchingTasks ArrayList of Tasks that contains the keyword
     */
    public void printFind(ArrayList<Task> matchingTasks) {
        System.out.println(FIND_MESSAGE);
        int i = 0;
        for (Task task : matchingTasks) {
            System.out.println(++i + ". " + task.toString());
        }
    }
}
