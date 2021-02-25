package parser;

import constant.Constants;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import task.Event;

/**
 * Represents a class that parses user input to a valid Event object.
 */
public class EventParser {
    /**
     * Parses the user input to return a valid Event Object.
     * @param input The full input the user has entered.
     * @return A Event Object parsed from the user input.
     * @throws EmptyCommandException If the description of the event is empty.
     * @throws InvalidCommandException If the description of the event is invalid.
     */
    public static Event processEvent(String input) throws EmptyCommandException, InvalidCommandException {
        String substr = input.substring(Constants.EVENT_STRING_LENGTH);
        if (substr.isBlank()) {
            throw new EmptyCommandException(Constants.STRING_TASK_EVENT);
        }
        String[] parts = substr.split(Constants.STRING_SEPARATOR_AT);
        if (parts.length != 2) {
            throw new InvalidCommandException(Constants.STRING_TASK_EVENT);
        }
        String description = parts[0].trim();
        String at = parts[1].trim();
        return new Event(description, at);
    }
}
