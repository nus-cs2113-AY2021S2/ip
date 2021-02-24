package parser;

import constant.Constants;
import exception.EmptyCommandException;
import task.ToDo;

public class ToDoParser {
    public static ToDo processToDo(String input) throws EmptyCommandException {
        String substr = input.substring(Constants.TODO_STRING_LENGTH);
        if (substr.isBlank()) {
            throw new EmptyCommandException(Constants.STRING_TASK_TODO);
        }
        return new ToDo(substr.trim());
    }
}
