package duke.exception;

public class InvalidTaskKeywordException extends DukeException{
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public InvalidTaskKeywordException() {
        super("Please input a keyword to find!\n");
    }
}
