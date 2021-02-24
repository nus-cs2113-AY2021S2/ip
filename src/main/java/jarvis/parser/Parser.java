package jarvis.parser;

import jarvis.exception.EmptyDescriptionException;
import jarvis.exception.EmptyKeywordException;
import jarvis.exception.EmptyTaskIdException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Todo;

import java.util.Locale;

/**
 * Parses user input.
 */
public class Parser {

    private static String[] splitUserInput(String userInput) {
        String[] command = userInput.split(" ", 2);
        return command.length == 2 ? command : new String[] {command[0], ""};
    }

    /**
     * Parses user input into a Todo object.
     *
     * @param userInput command entered by the user.
     * @return a Todo object.
     * @throws EmptyDescriptionException if task description is empty.
     */
    public static Todo parseStringToTodo(String userInput) throws EmptyDescriptionException {
        String[] command = splitUserInput(userInput);
        String description = command[1];
        if (description.equals("")) {
            throw new EmptyDescriptionException();
        }
        return new Todo(description);
    }

    /**
     * Parses user input into a Deadline object
     *
     * @param userInput command entered by the user.
     * @return a Deadline object.
     * @throws EmptyDescriptionException if task description is empty.
     */
    public static Deadline parseStringToDeadline(String userInput) throws EmptyDescriptionException {
        String[] command = splitUserInput(userInput);
        String description = command[1];
        if (description.equals("")) {
            throw new EmptyDescriptionException();
        }
        String[] descriptionArray = description.split("/by ", 2);
        String details = descriptionArray[0];
        String by = descriptionArray[1];
        return new Deadline(details, by);
    }

    /**
     * Parses user input into an Event object.
     *
     * @param userInput command entered by the user.
     * @return an Event object.
     * @throws EmptyDescriptionException if task description is empty.
     */
    public static Event parseStringToEvent(String userInput) throws EmptyDescriptionException {
        String[] command = splitUserInput(userInput);
        String description = command[1];
        if (description.equals("")) {
            throw new EmptyDescriptionException();
        }
        String[] descriptionArray = description.split("/at ", 2);
        String details = descriptionArray[0];
        String at = descriptionArray[1];
        return new Event(details, at);
    }

    /**
     * Parses user input to obtain the keyword for finding tasks
     *
     * @param userInput command entered by the user.
     * @return the keyword that is used to search in the list
     * @throws EmptyKeywordException if keyword is missing
     */
    public static String parseFindCommand(String userInput) throws EmptyKeywordException {
        String[] command = splitUserInput(userInput);
        if (command[1].equals("")) {
            throw new EmptyKeywordException();
        }
        return command[1];
    }

    /**
     * Parses user input to obtain the task ID
     *
     * @param userInput command entered by the user.
     * @return task ID in the list
     * @throws EmptyTaskIdException if keyword is missing
     */
    public static String parseToGetTaskId(String userInput) throws EmptyTaskIdException {
        String[] command = splitUserInput(userInput);
        if (command[1].equals("")) {
            throw new EmptyTaskIdException();
        }
        return command[1];
    }
}
