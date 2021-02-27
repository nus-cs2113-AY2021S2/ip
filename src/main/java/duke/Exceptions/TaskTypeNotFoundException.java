package duke.Exceptions;

public class TaskTypeNotFoundException extends DukeException {

    public TaskTypeNotFoundException() {
        this.errorMessage = "You have entered an unknown task type.";
    }
}
