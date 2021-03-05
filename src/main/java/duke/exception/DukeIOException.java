package duke.exception;

public class DukeIOException extends DukeException{
    public DukeIOException() {
        this.ERROR_MESSAGE = "IO Error occurred :-(";
    }
}
