package duke.command;

import duke.CommandType;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.TaskManager;
import duke.task.Task;

import java.io.IOException;

public class DoneCmd extends Command{
    public DoneCmd(String s) {
        super(s);
    }

    /**
     * Executes a done command according to the user input
     * @param tasks the arraylist of tasks currently stored
     * @param ui show messages to interact with users
     * @param storage update the file once the task lists changed
     * @throws DukeException if no index field is found in user input
     *                       if the index in not in range of current tasks list
     */
    public void execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {
        try{
        String[] typeIndex = userInput.split("[Dd][Oo][Nn][Ee]",2);
        int taskIndexShow = Integer.parseInt(typeIndex[1].trim());
        if(taskIndexShow <= 0 || taskIndexShow > tasks.getNumOfTasks()) {
            throw new DukeException(CommandType.DONE);
        }
        Task doneTask = tasks.markTaskDone(taskIndexShow);
        ui.showDoneResult(doneTask);
        storage.writeToTxt(tasks.getTasks());
    } catch (NumberFormatException e) {
        throw new DukeException(CommandType.DONE);

    } catch (IOException e) {
        ui.showWriteToFileError();
    }

    }
}
