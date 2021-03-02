package duke.ui;

import duke.exception.action.EmptyListException;
import duke.exception.action.NoMatchingTaskException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUi of the application
 */
public class TextUi {

    /** A decorative prefix added to the beginning of lines printed by task list */
    private static final String LINE_PREFIX = "   ";

    /** A decorative line that surrounds all messages shown to the user */
    private static final String DIVIDER = "____________________________________________________________";

    /** A decorative logo of the application that is shown in the welcome message */
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private static final String MESSAGE_GREETING = " Hello! I'm Duke" + "\n What can I do for you?";
    private static final String MESSAGE_ENDING = " Bye. Hope to see you again soon :)";
    private static final String MESSAGE_ADDTASK = " Got it. I've added this task:";
    private static final String MESSAGE_DONETASK = "Nice! I've marked this task as done:";
    private static final String MESSAGE_DELETETASK = " Noted. I've removed this task:";
    private static final String MESSAGE_REMAININGTASK = " Now you have %d tasks in the list.";
    private static final String MESSAGE_LISTTASK = " Here are the tasks in your list:";
    private static final String MESSAGE_FINDTASK = " Here are the matching tasks in your list:";

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints the messages that is to be delivered to the user
     * (Note: All messages are surrounded by a decorative Divider)
     */
    public void showToUser(String... message) {
        out.println(DIVIDER);
        for (String m : message) {
            out.println(m);
        }
        out.println(DIVIDER);
    }

    /** Generates and prints the welcome message upon the start of the application. */
    public void showGreetingMessage() {
        showToUser(LOGO, MESSAGE_GREETING);
    }

    /** Generates and prints the terminating message upon receiving bye command from the user. */
    public void showEndingMessage() {
        showToUser(MESSAGE_ENDING);
    }

    /** Returns the full command input by the user */
    public String getUserCommand() {
        return in.nextLine();
    }

    /**
     * Generates and prints out a message showing that the {@link Task} has been successfully
     * added to {@link TaskList}
     *
     * @param tasks task list where the new task has been added
     */
    public void showAddResponse(TaskList tasks) {
        String addedTask = LINE_PREFIX + tasks.getLastTaskInTheList().toString();
        showToUser(
                MESSAGE_ADDTASK,
                addedTask,
                String.format(MESSAGE_REMAININGTASK, tasks.getTotalNoOfTasks()));
    }

    /**
     * Generates and prints out a message showing that the {@link Task} has been successfully marked as done
     *
     * @param tasks task list where the completed task belongs to
     * @param taskNo task number that has been completed
     */
    public void showDoneResponse(TaskList tasks, int taskNo) {
        String completedTask = LINE_PREFIX + tasks.getTask(taskNo - DISPLAYED_INDEX_OFFSET).toString();
        showToUser(
                MESSAGE_DONETASK,
                completedTask);
    }

    /**
     * Generates and prints out a message showing that the {@link Task} has been successfully
     * deleted from the {@link TaskList}
     *
     * @param tasks task list where the deleted task belongs to
     * @param taskNo task number that has been deleted
     */
    public void showDeleteResponse(TaskList tasks, int taskNo) {
        String deletedTask = LINE_PREFIX + tasks.getTask(taskNo - 1).toString();
        showToUser(
                MESSAGE_DELETETASK,
                deletedTask,
                String.format(MESSAGE_REMAININGTASK, tasks.getTotalNoOfTasks() - DISPLAYED_INDEX_OFFSET));
    }

    /**
     * Generates and prints out the full {@link TaskList}
     * The {@link TaskList#getTaskListAsString()} method is used to generate the String of {@link TaskList}
     *
     * @param tasks list of task to be shown to the user
     * @throws EmptyListException if the task list is empty
     */
    public void showListResponse(TaskList tasks) throws EmptyListException {
        if (!tasks.isEmpty()) {
            showToUser(MESSAGE_LISTTASK, tasks.getTaskListAsString());
        } else {
            throw new EmptyListException();
        }
    }

    /**
     * Generates and prints out the {@link TaskList} that contains all {@link Task} that
     * matches the keyword input by the user
     * The {@link TaskList#getTaskListAsString()} method is used to generate the String of {@link TaskList}
     *
     * @param matchingTasks task list which contains the keyword in the description
     * @throws NoMatchingTaskException if there are no tasks that matches the keyword
     */
    public void showFindResponse(TaskList matchingTasks) throws NoMatchingTaskException {
        if (!matchingTasks.isEmpty()) {
            showToUser(MESSAGE_FINDTASK, matchingTasks.getTaskListAsString());
        } else {
            throw new NoMatchingTaskException();
        }
    }
}
