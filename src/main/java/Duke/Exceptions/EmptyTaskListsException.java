package Duke.Exceptions;

public class EmptyTaskListsException extends DukeException {

    public String getMessage() {
        return "OOPS!!! The list is empty!";
    }
}
