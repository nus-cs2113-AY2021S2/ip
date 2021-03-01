package duke;

public class InvalidDeadlineTimeException extends Exception{

    public String getMessage() {
        return "OOPS!!! You need to add time for new Deadline with keyword '/by'!!";
    }
}
