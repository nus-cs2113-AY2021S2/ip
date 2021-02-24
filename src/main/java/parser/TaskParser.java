package parser;

import constant.Constants;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import exception.UnknownCommandException;
import task.Task;

public class TaskParser {
    public static Task processTaskToAdd(String input) throws UnknownCommandException {
        try {
            if (input.contains(Constants.STRING_TASK_TODO)) {
                return ToDoParser.processToDo(input);
            } else if (input.contains(Constants.STRING_TASK_DEADLINE)) {
                return DeadlineParser.processDeadline(input);
            } else if (input.contains(Constants.STRING_TASK_EVENT)) {
                return EventParser.processEvent(input);
            } else {
                throw new UnknownCommandException();
            }
        } catch (EmptyCommandException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
