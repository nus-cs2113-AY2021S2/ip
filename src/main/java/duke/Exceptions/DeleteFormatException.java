package duke.Exceptions;

public class DeleteFormatException extends DukeException {

    public DeleteFormatException() {
        this.errorMessage = "Delete command must be in the format: delete taskIndex";
    }

}
