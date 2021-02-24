package duke;

import duke.command.AddCommand;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.InvalidCommand;
import duke.command.Command;

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
import static duke.command.ListCommand.LIST_COMMAND;

public class Parser {
    private static final int FIRST_WORD = 0;
    private static final int SECOND_WORD = 1;
    private static final int SPLIT_SIZE = 2;

    public static final int EMPTY_WORD_SIZE = 0;
    public static final int ONE_WORD_SIZE = 1;

    private static final String DEADLINE_REGEX = "/by";
    private static final String EVENT_REGEX = "/at";
    private static final String EMPTY_STRING = "";
    private static final String WHITE_SPACE_REGEX = "\\s+";

    public Command parseInput(String userInput, TaskList tasks) throws EmptyStringException {

        String[] words = userInput.trim().split(WHITE_SPACE_REGEX, SPLIT_SIZE);
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
            return addTask(commandWord, description, tasks);
        case DONE_COMMAND:
            return doneTask(commandWord, description);
        case DELETE_COMMAND:
            return deleteTask(commandWord, description);
        case LIST_COMMAND:
            return new ListCommand();
        case EXIT_COMMAND:
            return new ExitCommand();
        default:
            throw new EmptyStringException();
        }
    }

    public Command addTask(String commandWord, String description, TaskList tasks) {
        try {
            return new AddCommand(commandWord, description, tasks);
        } catch (EmptyDescriptionException emptyDescriptionException) {
            return new InvalidCommand(commandWord, description, emptyDescriptionException);
        }
    }

    public Command doneTask(String commandWord, String description) {
        try {
            int task_number = Integer.parseInt(description);
            return new DoneCommand(task_number);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand(commandWord, description, numberFormatException);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return new InvalidCommand(commandWord, description, indexOutOfBoundsException);
        }
    }

    public Command deleteTask(String commandWord, String description) {
        try {
            int task_number = Integer.parseInt(description);
            return new DeleteCommand(task_number);
        } catch (NumberFormatException numberFormatException) {
            return new InvalidCommand(commandWord, description, numberFormatException);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return new InvalidCommand(commandWord, description, indexOutOfBoundsException);
        }
    }

    public Deadline parseDeadline(String description) throws EmptyStringException {
        String[] words = description.trim().split(DEADLINE_REGEX, SPLIT_SIZE);
        String extractedDescription = words[FIRST_WORD];

        if (words.length == ONE_WORD_SIZE) {
            throw new EmptyStringException();
        }

        String date = words[SECOND_WORD].trim();
        return new Deadline(extractedDescription,date);
    }

    public Event parseEvent(String description) throws EmptyStringException {
        String[] words = description.trim().split(EVENT_REGEX, SPLIT_SIZE);
        String extractedDescription = words[FIRST_WORD];

        if (words.length == ONE_WORD_SIZE) {
            throw new EmptyStringException();
        }

        String date = words[SECOND_WORD].trim();
        return new Event(extractedDescription,date);
    }
}
