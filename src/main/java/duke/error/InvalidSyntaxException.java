package duke.error;

/**
 * Throws if the syntax recieved for a command is incorrect. 
 * Exception message includes the correct syntax for the respective command. 
 */
public class InvalidSyntaxException extends Exception{
    public InvalidSyntaxException (String message) {
        super(message);
    }
}
