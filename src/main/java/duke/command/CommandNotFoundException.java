package duke.command;

public class CommandNotFoundException extends Exception{
    @Override
    public String getMessage() {
        String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what this command means :-( \n" + "Please try again!";
        return errorMessage;
    }
}
