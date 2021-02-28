package duke.Exceptions;

public class ScheduleFormatException extends DukeException {

    public ScheduleFormatException() {
        this.errorMessage = "Schedule command must be in the format: schedule yyyy-mm-dd";
    }
}
