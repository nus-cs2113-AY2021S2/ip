package duke.exception;

public class DataFileNotFoundException extends DukeException{
    public DataFileNotFoundException() {
        this.ERROR_MESSAGE = "No data file was found! A new directory and data file will be created when you restart Duke";
    }
}
