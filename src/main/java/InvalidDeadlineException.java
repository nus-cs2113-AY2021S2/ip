

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException(){
        super("OOPS invalid input for event. Pls check your /at statement or time format");
    }
}

