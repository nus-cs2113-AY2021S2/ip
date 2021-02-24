package parser;

import constant.Constants;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import task.Event;

public class EventParser {
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
