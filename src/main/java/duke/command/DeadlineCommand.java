package duke.command;

import duke.DukeException;
import duke.DukePrinter;
import duke.DukeTaskList;
import duke.task.Deadline;
import duke.task.Todo;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private static final int REQUIRED_NUMBER_OF_ARGS = 3;
    private static final int TASK_DESCRIPTION = 1;
    private static final int TASK_DATE = 2;

    private boolean printMessage, isDone;

    public DeadlineCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList, boolean printMessage, boolean isDone) {
        super(arguments, dukeTaskList);
        this.printMessage = printMessage;
        this.isDone = isDone;
    }

    @Override
    public void execute() throws DukeException {
        if (arguments.size() != REQUIRED_NUMBER_OF_ARGS) {
            throw new DukeException("Please give me more details about the task!");
        }

        String description = arguments.get(TASK_DESCRIPTION);
        if (description.isEmpty()) {
            throw new DukeException("The description of a task can't be empty. Please try again.");
        }
        String date = arguments.get(TASK_DATE);
        if (date.isEmpty()) {
            throw new DukeException("Please specify a deadline for the task.");
        }

        Deadline deadline = new Deadline(description, date);
        dukeTaskList.addTask(deadline);
        if (printMessage) {
            int numberOfTasks = dukeTaskList.getNumberOfTasks();
            DukePrinter.printTaskAdded(deadline.toString(), numberOfTasks);
        }
        if (isDone) {
            deadline.markAsDone();
        }
    }
}
