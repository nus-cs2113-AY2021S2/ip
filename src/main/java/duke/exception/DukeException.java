package duke.exception;

/*
Class DukeException for handling errors
*/
public class DukeException extends Exception {

    /*
    Constructor DukeException Object
    Initialize object with defined error message
    */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
