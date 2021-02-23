package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final int ERR_NO_DATE = -5;
    private static final int ERR_NO_NAME = -4;
    private static final int ERR_OUT_OF_BOUNDS_MESSAGE = -3;
    private static final int ERR_WRONG_FORMAT_MESSAGE = -2;
    private static final int ERR_WRONG_DATE_FORMAT = -1;

    private static final String GREETING_MESSAGE = "Wagwan! I is Ali G. West side.\nWhat is we chattin' bout today?";
    private static final String GOODBYE_MESSAGE = "Goodbye, big up yourself, keep it real, respekt.";
    private static final String BORDER_LINE = "___________________________________________________________";
    private static final String OUT_OF_BOUNDS_MESSAGE = "You are accessing something that doesn't exist! Stop being an ignoranus.";
    private static final String WRONG_FORMAT_MESSAGE = "I don't think you are cleverer enough to know that the format is wrong!";
    private static final String NO_NAME_MESSAGE = "Why you be trying to tell me something with no name? Ave' you been smoking me special stash?";
    private static final String NO_DATE_MESSAGE = "Ave' you got no understanding of a date? Like Monday the 41th or sumting like that?";
    private static final String WRONG_DATE_FORMAT_MESSAGE = "Are you spasticated? The date format is wrong! It should be" +
            " like YY-DDDD-M or sumting like that...\nNo wait, its YYYY-MM-DD. I'm bare smart innit?";
    private static final String TOTAL_TASK_MESSAGE = "You is having %d task(s) on your list.";
    private static final String SET_TO_DONE_MESSAGE = " set to done. You is well smart innit?";
    private static final String ADDED_TO_LIST_MESSAGE = "Wicked. This ting is now on da list.";
    private static final String DELETED_MESSAGE = "This ting has been deleted. I could've done that task, you stupid.";
    private static final String FIND_MESSAGE = "Here are all the tings I cound find.";
    private static final String NO_SEARCH_RESULTS = "Wait, I can't find anyting with that name on the list. Are you chatting rubbish?";

    public String getCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public void printGreeting() {
        System.out.println(GREETING_MESSAGE);
        printBorderLine();
    }
    public void printBorderLine() {
        System.out.println(BORDER_LINE);
    }
    /** Prints goodbye message after user inputs "bye". */
    public void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
        printBorderLine();
    }
    /** Prints total number of tasks in the list. */
    public void printTotalTasks() {
        System.out.println(String.format(TOTAL_TASK_MESSAGE, Task.totalNumberOfTasks));
    }
    /** Method used to print each item for "list" command. */
    public void printListItem(int index, String type, String status, String name, String date) {
        System.out.println(index + ". " + type + "[" + status + "] " + name + " " + date);
    }
    /** Prints name of item that was set to done. */
    public void printMarkedDone(String name) {
        System.out.println(name + SET_TO_DONE_MESSAGE);
    }
    /**
     * Prints message for deletion of task
     *
     * @param index index of item to delete
     */
    public void printDeletedTask(int index, ArrayList<Task> tasks) {
        System.out.println(DELETED_MESSAGE);
        printDetailsOfTask(index, tasks);
        printTotalTasks();
    }

    /**
     * Prints all items in the list that contains a substring in its name.
     *
     * @param substring substring to query.
     * @param tasks ArrayList of tasks.
     */
    public void printFoundItems(String substring, ArrayList<Task> tasks) {
        System.out.println(FIND_MESSAGE);
        int foundCounter = 0;
        for(int i = 0; i < Task.totalNumberOfTasks; i++) {
            if (tasks.get(i).getName().contains(substring)) {
                printDetailsOfTask(i, tasks);
                foundCounter += 1;
            }
        }
        if (foundCounter == 0) {
            System.out.println(NO_SEARCH_RESULTS);
        }
        printBorderLine();
    }
    /**
     * Prints the type, status, name and date of a given task.
     *
     * @param index index of task to query.
     * @param tasks ArrayList containing all tasks.
     */
    public void printDetailsOfTask(int index, ArrayList<Task> tasks) {
        Task task = tasks.get(index);
        System.out.println(task.getType() + "[" + task.getStatusIcon() + "] " + task.getName() + " " + task.getDate());
    }

    /**
     * Prints message sequence after a task has been added to list.
     * Message includes details of task added and total number of tasks.
     *
     * @param current index of the most recent addition to the list.
     * @param tasks ArrayList of tasks.
     */
    public void printAddedToList(int current, ArrayList<Task> tasks) {
        System.out.println(ADDED_TO_LIST_MESSAGE);
        printDetailsOfTask(current, tasks);
        printTotalTasks();
        printBorderLine();
    }
    /**
     * Prints appropriate error message according to the error type.
     *
     * @param type type of error thrown.
     */
    public void printError(int type) {
        // No fallthrough required
        switch (type) {
        case ERR_OUT_OF_BOUNDS_MESSAGE:
            System.out.println(OUT_OF_BOUNDS_MESSAGE);
            break;
        case ERR_WRONG_FORMAT_MESSAGE:
            System.out.println(WRONG_FORMAT_MESSAGE);
            break;
        case ERR_NO_NAME:
            System.out.println(NO_NAME_MESSAGE);
            break;
        case ERR_NO_DATE:
            System.out.println(NO_DATE_MESSAGE);
            break;
        case ERR_WRONG_DATE_FORMAT:
            System.out.println(WRONG_DATE_FORMAT_MESSAGE);
        }
        printBorderLine();
    }
}
