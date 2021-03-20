package duke.Exceptions;

/**
 * When Task Type in loaded data is not recognised
 */
public class TaskTypeNotFoundException extends DukeException {

    public TaskTypeNotFoundException() {
        this.errorMessage = "You have entered an unknown task type.";
    }
}
