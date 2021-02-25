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

    public void printTaskSize(ArrayList<Task> tasks) {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void printLogo() {
        System.out.println("Hello from\n" + LOGO + "\nHow may I help you?\n");
    }

    public void printBye() {
        System.out.println(BYE_MESSAGE);
    }

    public void printAdd(Task addedTask) {
        System.out.println(ADD_MESSAGE);
        System.out.println(addedTask);
    }

    public void printDelete(Task deletedTask) {
        System.out.println(DELETE_MESSAGE);
        System.out.println(deletedTask);
    }

    public void printList(TaskList taskList) {
        System.out.println(LIST_MESSAGE);
        int i = 0;
        for (Task task : taskList.getTaskList()) {
            System.out.println(++i + ". " + task.toString());
        }
    }

    public void printDone(Task doneTask) {
        System.out.println(DONE_MESSAGE);
        System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.getDescription());
    }
}
