package parser;

import constant.Constants;
import exception.EmptyCommandException;
import task.ToDo;

/**
 * Represents a class that parses the input to a valid ToDo object.
 */
public class ToDoParser implements Parser {
    /**
     * Parses the user input to return a valid ToDo object.
     *
     * @param input The full input entered by the user.
     * @return A ToDo object parsed from the user input.
     * @throws EmptyCommandException If the description of the ToDo is empty.
     */
    public static ToDo parse(String input) throws EmptyCommandException {
        String substr = input.substring(Constants.TODO_STRING_LENGTH);
        if (substr.isBlank()) {
            throw new EmptyCommandException(Constants.STRING_TASK_TODO);
        }
        return new ToDo(substr.trim());
    }
}
