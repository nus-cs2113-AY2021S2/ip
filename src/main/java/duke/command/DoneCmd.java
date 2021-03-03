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
