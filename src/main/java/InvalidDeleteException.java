public class InvalidDeleteException extends DukeException {

    public InvalidDeleteException() {
        super("The wrong format for the delete command has been entered.");
    }
}
