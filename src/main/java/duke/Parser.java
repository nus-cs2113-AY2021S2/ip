package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.InvalidCommand;

import duke.exception.EmptyDescriptionException;
import duke.exception.EmptyStringException;

import duke.task.Deadline;
import duke.task.Event;

import static duke.command.AddCommand.TODO_COMMAND;
import static duke.command.AddCommand.DEADLINE_COMMAND;
import static duke.command.AddCommand.EVENT_COMMAND;
import static duke.command.DeleteCommand.DELETE_COMMAND;
import static duke.command.DoneCommand.DONE_COMMAND;
import static duke.command.ExitCommand.EXIT_COMMAND;
import static duke.command.FindCommand.FIND_COMMAND;
import static duke.command.ListCommand.LIST_COMMAND;

/**
 * Parses user input.
 */
public class Parser {
    private static final int FIRST_WORD = 0;
    private static final int SECOND_WORD = 1;
    private static final int SPLIT_SIZE = 2;

    public static final int EMPTY_WORD_SIZE = 0;
    public static final int ONE_WORD_SIZE = 1;

    private static final String EMPTY_STRING = "";

    /**
     * Parses user input into command and description for execution.
     *
     * @param userInput user input string.
     * @param tasks a list of task store together.
     * @return the command based on the user input.
     * @throws EmptyStringException if the user input an empty string.
     */
    public Command parseInput(String userInput, TaskList tasks) throws EmptyStringException {

        String[] words = userInput.trim().split("\\s+", SPLIT_SIZE);
        String commandWord = words[FIRST_WORD].toLowerCase();
        String description = userInput.replaceFirst(commandWord, EMPTY_STRING).trim();

        if (words.length == EMPTY_WORD_SIZE) {
            throw new EmptyStringException();
        }

        switch (commandWord) {
        case TODO_COMMAND:
            //Fallthrough
        case DEADLINE_COMMAND:
            //Fallthrough
        case EVENT_COMMAND:
            try {
                return new AddCommand(commandWord, description, tasks);
            } catch (EmptyDescriptionException emptyDescriptionException) {
                return new InvalidCommand(commandWord, description, emptyDescriptionException);
            }
        case DONE_COMMAND:
            //Fallthrough
        case DELETE_COMMAND:
            return prepareDoneAndDeleteCommand(commandWord, description);
        case FIND_COMMAND:
            try {
                return new FindCommand(description);
            } catch (EmptyDescriptionException emptyDescriptionException) {
                return new InvalidCommand(commandWord, description, emptyDescriptionException);
            }
        case LIST_COMMAND:
            return new ListCommand();
        case EXIT_COMMAND:
            return new ExitCommand();
        default:
            throw new EmptyStringException();
        }
    }

    /**
     * Parses the description from string to integer for done and delete command.
     *
     * @param commandWord the command based on the user input.
     * @param description the description of string based on user input.
     * @return the prepared command.
     * @throws NumberFormatException if the description is not an integer.
     * @throws IndexOutOfBoundsException if the number given by user is not found in the array list.
     */
    public Command prepareDoneAndDeleteCommand(String commandWord, String description) {
        try {
            int task_number = Integer.parseInt(description);
            switch (commandWord) {
            case DONE_COMMAND:
                return new DoneCommand(task_number);
            case DELETE_COMMAND:
                return new DeleteCommand(task_number);
            default:
                return null;
            }
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand(commandWord, description, numberFormatException);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return new InvalidCommand(commandWord, description, indexOutOfBoundsException);
        }
    }


    /**
     * Parse the description string into description of task and date for deadline subclass.
     *
     * @param description the description of string based on user input.
     * @return the task in deadline format.
     * @throws EmptyStringException if the description does not contain the required regex.
     */
    public Deadline parseDeadline(String description) throws EmptyStringException {
        String[] words = description.trim().split("/by", SPLIT_SIZE);
        String extractedDescription = words[FIRST_WORD].trim();

        if (words.length == ONE_WORD_SIZE) {
            throw new EmptyStringException();
        }

        String date = words[SECOND_WORD].trim();
        return new Deadline(extractedDescription,date);
    }

    /**
     * Parse the description string into description of task and date for event subclass.
     *
     * @param description the description of string based on user input.
     * @return the task in event format.
     * @throws EmptyStringException if the description does not contain the required regex.
     */
    public Event parseEvent(String description) throws EmptyStringException {
        String[] words = description.trim().split("/at", SPLIT_SIZE);
        String extractedDescription = words[FIRST_WORD].trim();

        if (words.length == ONE_WORD_SIZE) {
            throw new EmptyStringException();
        }

        String date = words[SECOND_WORD].trim();
        return new Event(extractedDescription,date);
    }
}
