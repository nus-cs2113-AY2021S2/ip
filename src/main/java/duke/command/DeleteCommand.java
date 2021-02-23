package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.error.EmptyNameFieldException;
import duke.error.IllegalAccessException;
import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private static final int INVALID_INDEX = -1;
    private static final int ERR_NO_NAME = -4;
    private static final int ERR_OUT_OF_BOUNDS_MESSAGE = -3;

    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    public DeleteCommand(String input, TaskList taskList, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute(Ui ui) {
        try {
            ArrayList<Task> tasks = this.taskList.getTasks();
            deleteItem(this.input, tasks);
        } catch (IllegalAccessException e) {
            this.ui.printError(ERR_OUT_OF_BOUNDS_MESSAGE);
        } catch (EmptyNameFieldException e) {
            this.ui.printError(ERR_NO_NAME);
        }

    }
    /**
     * Deletes item from task list if input format is correct.
     *
     * @param line user input.
     * @throws EmptyNameFieldException if index to delete is not given.
     * @throws IllegalAccessException if index is out of bounds.
     */
    public void deleteItem(String line, ArrayList<Task> tasks) throws EmptyNameFieldException, IllegalAccessException {
        if (line.length() < 8) {
            throw new EmptyNameFieldException();
        }
        int index =checkValidDelete(line);
        if (index == INVALID_INDEX) {
            throw new IllegalAccessException();
        } else {
            Task.totalNumberOfTasks -= 1;
            this.ui.printDeletedTask(index-1, tasks);
            tasks.remove(index-1);
        }
        this.ui.printBorderLine();
    }
    /**
     * Checks for presence of number on index 7 of input.
     * Then check if the number is within bounds.
     *
     * @param line input from user.
     * @return index of item to mark as done if valid, -1 otherwise.
     */
    public int checkValidDelete(String line) {
        if (line.substring(7).matches("[0-9]+")) {
            int listNum = Integer.parseInt(line.substring(7));
            // Check for illegal access to out of bounds index
            if (listNum > Task.totalNumberOfTasks || listNum == 0) {
                return INVALID_INDEX;
            }
            return listNum;
        }
        return INVALID_INDEX;
    }
}
