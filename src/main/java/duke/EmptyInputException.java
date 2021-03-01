package duke;

public class EmptyInputException extends Exception{

    public String getMessage() {
        return "OOPS!!! The description of a new task cannot be empty.";
    }
}
