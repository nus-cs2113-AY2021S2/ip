package duke;

import duke.exception.DukeException;
import duke.exception.TaskType;

/**
 * Provides methods for general/repeating UI displays.
 */
public class Ui {

    public Ui() {}

    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am\n" + logo);

        System.out.println("What can I do for you today?");
    }

    public void displayGoodbye() {
        System.out.println("Goodbye. See you again soon :)");
    }

    /**
     * Prints appropriate error message depending on the TaskType causing the exception.
     *
     * @param exception TaskType of exception is defined based on the input that caused the exception.
     */
    public void displayError(DukeException e) {
        TaskType taskType = e.getTaskType();
        switch (taskType) {
        case DEADLINE:
            System.out.println("Please input deadline tasks in the correct format. (e.g deadline <task description> /by YYYY-MM-DD)");
            break;
        case EVENT:
            System.out.println("Please input event tasks in the correct format. (e.g. event <task description> /at <timing>)");
            break;
        case TODO:
            System.out.println("Please input todo tasks in the correct format. (e.g. todo <task description>)");
            break;
        case INVALID:
        default:
            System.out.println("Please input a valid command! (e.g. deadline..., done..., list)");
        }
    }
}
