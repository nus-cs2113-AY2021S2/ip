package duke.Exceptions;

public class DoneFormatException extends DukeException {

    public DoneFormatException() {
        this.errorMessage = "Done command must be in the format: done taskIndex";
    }
}
