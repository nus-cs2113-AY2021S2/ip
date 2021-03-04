package duke.parser;

import duke.commands.*;
import duke.exception.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input，deals with making sense of the user command
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern DEADLINE_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<description>[^/]+)"
                    + " by/(?<time>[^/]+)");

    public static final Pattern TODO_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+)");

    public static final Pattern EVENT_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+)"
                    + " at/(?<venue>[^/]+)");

    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<index>\\p{Digit}+)");

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public static Command parse(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case AddTodoCommand.COMMAND_WORD:
                return prepareTodo(arguments);

            case AddDeadlineCommand.COMMAND_WORD:
                return prepareDdl(arguments);

            case AddEventCommand.COMMAND_WORD:
                return prepareEvent(arguments);

            case DoneCommand.COMMAND_WORD:
                return prepareDone(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                return new HelpCommand();
        }
    }

    /**
     * Parses arguments in the context of the add to-do command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command prepareTodo(String args) {
        final Matcher matcher = TODO_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
        }
        try {
            return new AddTodoCommand(matcher.group("description"));
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Parses arguments in the context of the add deadline command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command prepareDdl(String args) {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
        }
        try {
            return new AddDeadlineCommand(matcher.group("description"), matcher.group("time"));
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Parses arguments in the context of the add event command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
        }
        try {
            return new AddEventCommand(matcher.group("description"), matcher.group("venue"));
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Parses arguments in the context of the complete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command prepareDone(String args) {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
        }
        return new DoneCommand(Integer.parseInt(matcher.group("index")));

    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private static Command prepareDelete(String args) {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
        }
        int index = Integer.parseInt(matcher.group("index"));
        return new DeleteCommand(index);
    }


}
