package duke.command;

import duke.DukeException;
import duke.DukePrinter;
import duke.DukeTaskList;

import java.util.ArrayList;

public class DoneCommand extends Command {
    private static final int REQUIRED_NUMBER_OF_ARGS = 2;
    private static final int TASK_NUMBER = 1;

    public DoneCommand(ArrayList<String> arguments, DukeTaskList dukeTaskList) {
        super(arguments, dukeTaskList);
    }

    @Override
    public void execute() throws DukeException, NumberFormatException {
        if (arguments.size() != REQUIRED_NUMBER_OF_ARGS) {
            throw new DukeException("Please give me more details about the task!");
        }

        String taskNumberString = arguments.get(TASK_NUMBER);
        int taskNumber = Integer.parseInt(taskNumberString);
        String doneTaskString = dukeTaskList.markTaskAsDone(taskNumber);
        DukePrinter.printTaskMarkedDone(doneTaskString);
    }
}
