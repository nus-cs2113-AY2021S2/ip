package parser;

import constant.Constants;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import task.Deadline;

public class DeadlineParser {

    public static Deadline processDeadline(String input) throws EmptyCommandException, InvalidCommandException {
        String substr = input.substring(Constants.DEADLINE_STRING_LENGTH);
        if (substr.isBlank()) {
            throw new EmptyCommandException(Constants.STRING_TASK_DEADLINE);
        }
        String[] parts = substr.split(Constants.STRING_SEPARATOR_BY);
        if (parts.length != 2) {
            throw new InvalidCommandException(Constants.STRING_TASK_DEADLINE);
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new Deadline(description, by);
    }
}
