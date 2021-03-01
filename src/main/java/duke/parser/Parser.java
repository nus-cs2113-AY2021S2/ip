package duke.parser;

import duke.exception.DukeException;
import duke.exception.InvalidTaskCommandException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles Duke's commands.
 */
public class Parser {

    /**
     * Determines the command from user input.
     *
     * @return Command to exit program.
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
            } else if (input.equals("bye")) {
                return true;
            } else {
                throw new InvalidTaskCommandException();
            }

       return false;
    }
}
