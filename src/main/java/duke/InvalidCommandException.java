package duke;

public class InvalidCommandException extends Exception{

    public String getMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
