package duke;

import static duke.Ui.INVALID_INDEX_MESSAGE;
import static duke.Ui.INVALID_TASK_MESSAGE;
import static duke.Ui.MISSING_FIELDS_MESSAGE;
import static duke.Ui.OUTSIDE_RANGE_INDEX_MESSAGE;
import static duke.common.Constants.DEFAULT_STATUS;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exceptions.DukeException;

/** Parses user's input
 * and determines command to be run
*/
public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public Command processInput(String userInput) {
        if (userInput.equals("bye")) {
            return new ByeCommand();
        }
        if (userInput.equals("list")) {
            return new ListCommand(tasks);
        }
        if (userInput.equals("help")) {
            return new HelpCommand();
        }
        String[] userCommand;
        try {
            userCommand = prepareCommand(userInput);
        } catch (DukeException e) {
            return new InvalidCommand(null);
        }
        switch(userCommand[0]) {
        case "todo":
            return prepareTodoCommand(userCommand[1]);
        case "deadline":
            return prepareDeadlineCommand(userCommand[1]);
        case "event":
            return prepareEventCommand(userCommand[1]);
        case "done":
            return prepareDoneCommand(userCommand[1]);
        case "delete":
            return prepareDeleteCommand(userCommand[1]);
        default:
            return new InvalidCommand(null);
        }
    }

    public String[] prepareCommand(String userInput) throws DukeException {
        userInput = userInput.trim();
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length < 2) {
            throw new DukeException();
        }
        return tokens;
    }

    public Command prepareTodoCommand(String description) {
        try {
            String taskDescription = getTodoDescription(description);
            return new TodoCommand(tasks, taskDescription, DEFAULT_STATUS);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        } catch (DukeException e) {
            return new InvalidCommand(INVALID_TASK_MESSAGE);
        }
    }

    public String getTodoDescription(String description) throws DukeException {
        try {
            /* Task description contains only numbers */
            Long.parseLong(description);
            throw new DukeException();
        } catch (NumberFormatException e) {
            return description;
        }
    }

    public Command prepareDeadlineCommand(String description) {
        try {
            String[] words = getDeadlineOrEventDescription(" /by ", description);
            return new DeadlineCommand(tasks, words[0], DEFAULT_STATUS, words[1]);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        }
    }

    public Command prepareEventCommand(String description) {
        try {
            String[] words = getDeadlineOrEventDescription(" /at ", description);
            return new EventCommand(tasks, words[0], DEFAULT_STATUS, words[1]);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        }
    }

    public Command prepareDoneCommand (String description) {
        try {
            int index = getTaskIndex(description);
            return new DoneCommand(tasks, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        } catch (NumberFormatException e) {
            return new InvalidCommand(INVALID_INDEX_MESSAGE);
        } catch (DukeException e) {
            return new InvalidCommand(OUTSIDE_RANGE_INDEX_MESSAGE + description);
        }
    }

    public Command prepareDeleteCommand(String description) {
        try {
            int index = getTaskIndex(description);
            return new DeleteCommand(tasks, index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        } catch (NumberFormatException e) {
            return new InvalidCommand(INVALID_INDEX_MESSAGE);
        } catch (DukeException e) {
            return new InvalidCommand(OUTSIDE_RANGE_INDEX_MESSAGE + description);
        }
    }

    public int getTaskIndex(String description) throws DukeException {
        int index = Integer.parseInt(description);
        boolean isPossibleIndex = index > 0;
        boolean isValidIndex = index <= tasks.getTasksCount();
        if (!isPossibleIndex || !isValidIndex) {
            throw new DukeException();
        }
        return index;
    }

    public String[] getDeadlineOrEventDescription(String keyword, String sentence) throws DukeException {
        String[] words = sentence.split(keyword, 2);
        if (words.length < 2) {
            throw new DukeException();
        }
        return words;
    }
}
