package io;

import models.Task;
import models.TaskList;

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
    private static final String LIST_MESSAGE =
            "Here are the tasks in your list:";
    private static final String DONE_MESSAGE =
            "Nice! I've marked this task as done:";
    private static final String ADD_MESSAGE =
            "Got it. I've added this task:";
    private static final String DELETE_MESSAGE =
            "Noted. I've removed this task:";
    private static final String BYE_MESSAGE =
            "Alright cheers mate!";

    public void printDivider() {
        System.out.println(DIVIDER);
    }

    public void printEndDivider() {
        System.out.println(END_DIVIDER);
    }

    public void printTaskSize(int numTasks) {
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    public void printLogo() {
        System.out.println("Hello from\n" + LOGO);
    }

    public void printWelcome() {
        printDivider();
        System.out.println("What's up! I'm Duke");
        System.out.println("What can I do for you?");
        printEndDivider();
    }

    public void printBye() {
        System.out.println(BYE_MESSAGE);
    }

    public void printAdd(Task addedTask, int numTasks) {
        System.out.println(ADD_MESSAGE);
        System.out.println(addedTask);
        printTaskSize(numTasks);
    }

    public void printDelete(Task deletedTask) {
        System.out.println(DELETE_MESSAGE);
        System.out.println(deletedTask);
    }

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

    public void printDone(Task doneTask) {
        System.out.println(DONE_MESSAGE);
        System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.getDescription());
    }
}
