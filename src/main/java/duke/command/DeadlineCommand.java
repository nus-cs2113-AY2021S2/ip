package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.exception.SaveException;
import duke.task.Deadline;

public class DeadlineCommand extends Command {
    public DeadlineCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, IOException, SaveException {
        String by = arguments.get("by");
        if (by == null || by.length() == 0) {
            // Either /at is not found at all, or no dates are following /at
            throw new InvalidInputException(InputExceptionType.NO_BY_DATE);
        }
        tasks.addTask(new Deadline(arguments.get("payload"), by));
        ui.printNewTask(tasks);
    }
}
