package duke.command;

import duke.DukeException;
import duke.DukePrinter;
import duke.DukeTaskList;
import duke.task.Deadline;
import duke.task.Event;

import java.util.ArrayList;

public class EventCommand extends Command {
    private static final int REQUIRED_NUMBER_OF_ARGS = 3;
    private static final int TASK_DESCRIPTION = 1;
    private static final int TASK_DATE = 2;

    private boolean printMessage, isDone;

    public EventCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList, boolean printMessage, boolean isDone) {
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
            throw new DukeException("Please specify a date for the event.");
        }

        Event event = new Event(description, date);
        dukeTaskList.addTask(event);
        if (printMessage) {
            int numberOfTasks = dukeTaskList.getNumberOfTasks();
            DukePrinter.printTaskAdded(event.toString(), numberOfTasks);
        }
        if (isDone) {
            event.markAsDone();
        }
    }
}
