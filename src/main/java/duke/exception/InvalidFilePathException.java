package duke.exception;

public class InvalidFilePathException extends  StorageOperationException {
    public String getMessage() {
        return " OOPS! Error occurred while creating directory and file :(";
    }
}
