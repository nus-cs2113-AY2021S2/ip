package duke.task;

import duke.exception.MissingTaskDescriptionException;

/**
 * The most basic task in this application - this class only has a description field
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "Todo : " + super.toString();
    }

    public static void checkTodoInput(String[] taskDetails) throws MissingTaskDescriptionException {
        if (taskDetails[0] == null) {
            throw new MissingTaskDescriptionException();
        }
    }


}
