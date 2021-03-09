package main;

import main.commands.*;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input.
 *
 * @author Jeremy
 * @version 0.2
 * @since 2021-02-28
 */
public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Used for separation of the deadline description and date.
     */
    public static final Pattern DEADLINE_DATA_ARGS_FORMAT =
            Pattern.compile("(?<taskDescription>[^/]+)by/ (?<date>[^/]+)");

    /**
     * Used for separation of the event description and date.
     */
    public static final Pattern EVENT_DATA_ARGS_FORMAT =
            Pattern.compile("(?<eventDescription>[^/]+)at/ (?<date>[^/]+)");

    /**
     * Used to parse index of task to be deleted.
     */
    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    /**
     * Parses user input into command for execution
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */

    public Command parse(String userInput) {
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        String commandWord = null;
        String arguments = null;
        while (matcher.find()) {
            commandWord = matcher.group("commandWord").trim();
            arguments = matcher.group("arguments").trim();
        }


        switch (commandWord) {
            case TodoAddCommand.COMMAND_WORD:
                return prepareTodoAdd(arguments);

            case DeadlineAddCommand.COMMAND_WORD:
                return prepareDeadlineAdd(arguments);

            case EventAddCommand.COMMAND_WORD:
                return prepareEventAdd(arguments);

            case MarkDoneCommand.COMMAND_WORD:
                return prepareMarkTaskDone(arguments);

            case FindTaskCommand.COMMAND_WORD:
                return prepareFindTask(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case SaveCommand.COMMAND_WORD:
                return new SaveCommand();

            case LoadCommand.COMMAND_WORD:
                return new LoadCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            default:
                Ui.showToUser(Ui.MESSAGE_INVALID_COMMAND);
                return new HelpCommand();

        }
    }

    /**
     * Parses arguments in the context of the find task command.
     *
     * @param args full command args string
     * @return prepared command
     */
    protected Command prepareFindTask(String args) {
        if (args.isBlank()) {
            return new IncorrectCommand(String.format(Ui.MESSAGE_INCORRECT_COMMAND,FindTaskCommand.MESSAGE_USAGE));
        } else {
            return new FindTaskCommand(args);
        }
    }

    /**
     * Parses arguments in the context of the mark task done command.
     *
     * @param args full command args string
     * @return prepared command
     */
    protected Command prepareMarkTaskDone(String args) {
        try {
            final int targetIndex = parseArgsAsIndex(args);
            return new MarkDoneCommand(targetIndex);
        } catch (DukeException e) {
            return new IncorrectCommand(String.format(Ui.MESSAGE_INCORRECT_COMMAND, MarkDoneCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    protected Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (DukeException e) {
            return new IncorrectCommand(String.format(Ui.MESSAGE_INCORRECT_COMMAND, DeleteCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses arguments in the context of the adding todo task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    protected Command prepareTodoAdd(String args) {
        try {
            return new TodoAddCommand(new Todo(args));
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Parses arguments in the context of the add deadline task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    protected Command prepareDeadlineAdd(String args) {
        final Matcher matcher = DEADLINE_DATA_ARGS_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Ui.MESSAGE_INCORRECT_COMMAND, DeadlineAddCommand.MESSAGE_USAGE));
        }
        try {
            return new DeadlineAddCommand(
                    new Deadline(matcher.group("taskDescription").trim(),
                            matcher.group("date").trim()));
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Parses arguments in the context of the add event task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    protected Command prepareEventAdd(String args) {
        final Matcher matcher = EVENT_DATA_ARGS_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(Ui.MESSAGE_INCORRECT_COMMAND, EventAddCommand.MESSAGE_USAGE));
        }
        try {
            return new EventAddCommand(
                    new Event(matcher.group("eventDescription").trim(),
                            matcher.group("date").trim()));
        } catch (DukeException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    /**
     * Parses the user given string as an index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws DukeException         if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsIndex(String args) throws DukeException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new DukeException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }


}
