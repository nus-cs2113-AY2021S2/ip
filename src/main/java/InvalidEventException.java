

public class InvalidEventException extends DukeException {
    public InvalidEventException(){
        super("OOPS invalid input for event. Pls check your /at statement or time format");
    }
}
