package duke.command;

import duke.TaskList;
import duke.Ui;

import duke.error.EmptyNameFieldException;
import duke.error.IllegalAccessException;

import duke.task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private static final int ERR_NO_NAME = -4;
    private static final int ERR_OUT_OF_BOUNDS_MESSAGE = -3;
    private static final int INVALID_INDEX = -1;

    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    public MarkCommand(String input, TaskList taskList, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute(Ui ui) {
        try {
            ArrayList<Task> tasks = this.taskList.getTasks();
            markAsDone(input, tasks);
        } catch (IllegalAccessException e) {
            this.ui.printError(ERR_OUT_OF_BOUNDS_MESSAGE);
        } catch (EmptyNameFieldException e) {
            this.ui.printError(ERR_NO_NAME);
        }
    }

    /**
     * Marks a task as done.
     * Checks for out of bounds access and presence of numerical value.
     *
     * @param line user input.
     * @param tasks ArrayList containing all tasks.
     * @throws IllegalAccessException if index given is out of bounds.
     * @throws EmptyNameFieldException if index is not given.
     */
    public void markAsDone(String line, ArrayList<Task> tasks)
            throws IllegalAccessException, EmptyNameFieldException {
        if (line.length() < 6) {
            throw new EmptyNameFieldException();
        }
        int listNum = checkValidDone(line);
        if (listNum == INVALID_INDEX) {
            throw new IllegalAccessException();
        }
        listNum -= 1;
        tasks.get(listNum).setDone();
        this.ui.printMarkedDone(tasks.get(listNum).getName());
        this.ui.printBorderLine();
    }

    /**
     * Checks for presence of number on index 5 of input.
     * Then check if the number is within bounds.
     *
     * @param line input from user.
     * @return index of item to mark as done if valid, -1 otherwise.
     */
    public static int checkValidDone(String line) {
        if (line.substring(5).trim().matches("[0-9]+")) {
            int listNum = Integer.parseInt(line.substring(5).trim());
            // Check for illegal access to out of bounds index
            if (listNum > Task.totalNumberOfTasks || listNum == 0) {
                return INVALID_INDEX;
            }
            return listNum;
        }
        return INVALID_INDEX;
    }
}
