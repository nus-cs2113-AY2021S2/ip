package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidTaskCommandException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Handles Duke's commands.
 */
public class Parser {

    /**
     * @param input User input for commands
     * @return Output of commands that user entered.
     * @throws InvalidTaskCommandException Prints Error message if user input fails any exceptions.
     */
    public static boolean determineCommand(String input) throws DukeException {

        if (input.equals("list")) {
            TaskList.listTask();
        } else if (input.startsWith("done")) {
            TaskList.markTaskDone(input);
        } else if (input.startsWith("delete")) {
            TaskList.deleteTask(input);
        } else if (input.startsWith("todo")) {
            TaskList.addToDo(input);
        } else if (input.startsWith("deadline")) {
            TaskList.addDeadline(input);
        } else if (input.startsWith("event")) {
            TaskList.addEvent(input);
        } else if (input.startsWith("find")) {
            TaskList.findTask(input);
        } else if (input.startsWith("help")) {
            Ui.printDivider();
            Ui.printHelpCommandList();
            Ui.printDivider();
        } else if (input.equals("bye")) {
            return true;
        } else {
            throw new InvalidTaskCommandException();
        }

        return false;
    }
}
