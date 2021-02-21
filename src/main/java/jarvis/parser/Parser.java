package jarvis.parser;

import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Todo;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into {@code Todo} object.
     *
     * @param userInput command entered by the user.
     * @return a {@code Todo} object.
     */
    public static Todo parseStringToTodo(String userInput) {
        String description = userInput.replaceFirst("todo ", "");
        return new Todo(description);
    }

    /**
     * Parses user input into {@code Deadline} object
     *
     * @param userInput command entered by the user.
     * @return a {@code Deadline} object.
     */
    public static Deadline parseStringToDeadline(String userInput) {
        String task = userInput.replaceFirst("deadline ", "");
        String[] details = task.split("/by", 2);
        String description = details[0];
        String by = details[1];
        return new Deadline(description, by);
    }

    /**
     * Parses user input into {@code Event} object.
     *
     * @param userInput command entered by the user.
     * @return a {@code Event} object.
     */
    public static Event parseStringToEvent(String userInput) {
        String task = userInput.replaceFirst("event ", "");
        String[] details = task.split("/at", 2);
        String description = details[0];
        String at = details[1];
        return new Event(description, at);
    }
}
