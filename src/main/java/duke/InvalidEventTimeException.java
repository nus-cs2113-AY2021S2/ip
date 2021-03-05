package duke;

public class InvalidEventTimeException extends Exception{

    public String getMessage() {
        return "OOPS!!! You need to add time for new Event with keyword '/at'!!";
    }

}
