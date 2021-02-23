package jarvis.parser;

import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Todo;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into a Todo object.
     *
     * @param userInput command entered by the user.
     * @return a Todo object.
     */
    public static Todo parseStringToTodo(String userInput) {
        String description = userInput.replaceFirst("todo ", "");
        return new Todo(description);
    }

    /**
     * Parses user input into a Deadline object
     *
     * @param userInput command entered by the user.
     * @return a Deadline object.
     */
    public static Deadline parseStringToDeadline(String userInput) {
        String task = userInput.replaceFirst("deadline ", "");
        String[] details = task.split("/by ", 2);
        String description = details[0];
        String by = details[1];
        return new Deadline(description, by);
    }

    /**
     * Parses user input into an Event object.
     *
     * @param userInput command entered by the user.
     * @return an Event object.
     */
    public static Event parseStringToEvent(String userInput) {
        String task = userInput.replaceFirst("event ", "");
        String[] details = task.split("/at ", 2);
        String description = details[0];
        String at = details[1];
        return new Event(description, at);
    }

    /**
     * Parses user input to obtain the keyword for finding tasks
     *
     * @param userInput command entered by the user.
     * @return the keyword that is used to search in the list
     */
    public static String parseFindCommand(String userInput) {
        return userInput.replaceFirst("find ", "");
    }
}
