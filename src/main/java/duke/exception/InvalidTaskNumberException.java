package duke.exception;

public class InvalidTaskNumberException extends DukeException{
    /**
     * Creates DukeExceptions for methods to throw.
     */
    public InvalidTaskNumberException() {
        super("Please input a valid task number!\n"
                + "\tTo view your list, type 'list'!\n");
    }
}
