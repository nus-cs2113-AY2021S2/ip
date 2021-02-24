package duke.exception;

public class InvalidDateTimeException extends Exception {
    @Override
    public String getMessage() {
        return "Enter the due date in the form 'dd-mm-yyyy hh:mm a' e.g. 18-02-2021 05:00 PM";
    }
}

