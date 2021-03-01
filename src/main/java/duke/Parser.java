package duke;

import static duke.Ui.INVALID_INDEX_MESSAGE;
import static duke.Ui.INVALID_TASK_MESSAGE;
import static duke.Ui.MISSING_FIELDS_MESSAGE;
import static duke.Ui.OUTSIDE_RANGE_INDEX_MESSAGE;
import static duke.Ui.INVALID_DATE_FORMAT_MESSAGE;
import static duke.common.Constants.DEFAULT_STATUS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.FilterCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exceptions.DukeException;

/**
 * Parses user's input and determines command to be run.
 */
public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a {@code Command} to be executed in main.
     * <p></p>
     * For commands with no args, this method returns immediately
     * (e.g. exiting the program with "bye" command word).
     * Otherwise, it returns after preparing the {@code Command} based on the given args.
     *
     * @param userInput full user input string
     * @return valid {@code Command} based on user's input or
     * {@code InvalidCommand} explaining why given input is invalid
     */
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
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
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
        case "find":
            return new FindCommand(tasks, userCommand[1]);
        case "filter":
            return prepareFilterCommand(userCommand[1]);
        default:
            return new InvalidCommand(null);
        }
    }

    /**
     * Separates commands that come with args into the command word and its args.
     *
     * @param userInput full user input string
     * @return command word and command args
     * @throws DukeException if no args are found
     */
    public String[] prepareCommand(String userInput) throws DukeException {
        userInput = userInput.trim();
        String[] tokens = userInput.split(" ", 2);
        if (tokens.length < 2) {
            throw new DukeException();
        }
        return tokens;
    }

    /**
     * Creates the {@code TodoCommand} to add a valid {@code Task} with
     * given {@code description} to {@code TaskList}.
     *
     * @param description task item string
     * @return new {@code TodoCommand} or {@code InvalidCommand} explaining why given input is invalid
     */
    public Command prepareTodoCommand(String description) {
        try {
            checkTodoDescription(description);
            return new TodoCommand(tasks, description, DEFAULT_STATUS);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        } catch (DukeException e) {
            return new InvalidCommand(INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Creates the {@code Command} to add a valid {@code Deadline} with
     * given deadline {@code description} to {@code TaskList}.
     *
     * @param description string containing deadline item, keyword {@code /by}, date, time
     * @return new {@code DeadlineCommand} or {@code InvalidCommand} explaining why given input is invalid
     */
    public Command prepareDeadlineCommand(String description) {
        try {
            String[] words = getDeadlineOrEventDescription(" /by ", description);
            String deadlineDescription = words[0];
            String[] taskDeadline = prepareCommand(words[1]);
            LocalDate deadlineDate = LocalDate.parse(taskDeadline[0]);
            LocalTime deadlineTime = LocalTime.parse(taskDeadline[1]);
            return new DeadlineCommand(tasks, deadlineDescription, DEFAULT_STATUS, deadlineDate, deadlineTime);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(INVALID_DATE_FORMAT_MESSAGE);
        }
    }

    /**
     * Creates the {@code Command} to add a valid {@code Event} with
     * given event {@code description} to the {@code TaskList}.
     *
     * @param description string containing event item, keyword {@code /at}, details
     * @return new {@code EventCommand} or {@code InvalidCommand} explaining why given input is invalid
     */
    public Command prepareEventCommand(String description) {
        try {
            String[] words = getDeadlineOrEventDescription(" /at ", description);
            return new EventCommand(tasks, words[0], DEFAULT_STATUS, words[1]);
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        }
    }

    /**
     * Creates the {@code Command} to mark a valid {@code Task} in the {@code TaskList} as completed.
     *
     * @param description index of task to be marked done
     * @return new {@code DoneCommand} or {@code InvalidCommand} explaining why given input is invalid
     */
    public Command prepareDoneCommand (String description) {
        try {
            int taskIndex = getTaskIndex(description);
            return new DoneCommand(tasks, taskIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        } catch (NumberFormatException e) {
            return new InvalidCommand(INVALID_INDEX_MESSAGE);
        } catch (DukeException e) {
            return new InvalidCommand(OUTSIDE_RANGE_INDEX_MESSAGE + description);
        }
    }

    /**
     * Creates the {@code Command} to remove a valid {@code Task} from {@code TaskList}.
     *
     * @param description index of task to be deleted
     * @return new {@code DeleteCommand} or {@code InvalidCommand} explaining why given input is invalid
     */
    public Command prepareDeleteCommand(String description) {
        try {
            int taskIndex = getTaskIndex(description);
            return new DeleteCommand(tasks, taskIndex);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        } catch (NumberFormatException e) {
            return new InvalidCommand(INVALID_INDEX_MESSAGE);
        } catch (DukeException e) {
            return new InvalidCommand(OUTSIDE_RANGE_INDEX_MESSAGE + description);
        }
    }

    /**
     * Creates the {@code Command} to extract any {@code Deadline} tasks from {@code TaskList}
     * with matching deadline date as the given {@code date}.
     *
     * @param date date to search for
     * @return new {@code FilterCommand} or {@code InvalidCommand} explaining why given input is invalid
     */
    public Command prepareFilterCommand(String date) {
        try {
            LocalDate targetDate = LocalDate.parse(date);
            return new FilterCommand(tasks, targetDate);
        } catch (NullPointerException e) {
            return new InvalidCommand(MISSING_FIELDS_MESSAGE);
        } catch (DateTimeParseException e) {
            return new InvalidCommand(INVALID_DATE_FORMAT_MESSAGE);
        }
    }

    /**
     * Checks if to-do {@code Task} is valid (meaningful).
     *
     * @param description task item string
     * @throws DukeException if to-do {@code Task} contains only numerical digits.
     */
    public void checkTodoDescription(String description) throws DukeException {
        try {
            Long.parseLong(description);
            throw new DukeException();
        } catch (NumberFormatException e) {
            //task description should be meaningful
        }
    }

    /**
     * Extracts from command args the task item and its details, for deadline and event commands.
     *
     * @param keyword string separating the task item and its details
     *                ({@code /by} for deadline, {@code /at} for event)
     * @param sentence full command args string
     * @return task item and task details
     * @throws DukeException if no task item or details were given.
     */
    public String[] getDeadlineOrEventDescription(String keyword, String sentence) throws DukeException {
        String[] words = sentence.split(keyword, 2);
        if (words.length < 2) {
            throw new DukeException();
        }
        return words;
    }

    /**
     * Returns a task index in numerical form for done and delete commands.
     *
     * @param description index of task
     * @return valid index of task as a number
     * @throws DukeException if task index is out of range of {@code TaskList}.
     */
    public int getTaskIndex(String description) throws DukeException {
        int index = Integer.parseInt(description);
        boolean isPossibleIndex = index > 0;
        boolean isValidIndex = index <= tasks.getTasksCount();
        if (!isPossibleIndex || !isValidIndex) {
            throw new DukeException();
        }
        return index;
    }
}
