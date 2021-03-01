package duke.command;

import duke.CommandType;
import duke.EmptyDescriptionException;
import duke.Storage;
import duke.Ui;
import duke.TaskManager;

import java.io.IOException;

public class DeleteCmd extends Command{
    public DeleteCmd(String s) {
        super(s);
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        try{
            String[] typeIndex = userInput.split("[Dd][Ee][Ll][Ee][Tt][Ee]",2);
            int taskIndexShow = Integer.parseInt(typeIndex[1].trim());
            if(taskIndexShow <= 0 || taskIndexShow > tasks.getNumOfTasks()) {
                throw new EmptyDescriptionException(CommandType.DELETE);
            }
            tasks.deleteTask(taskIndexShow);
            storage.writeToTxt(tasks.getTasks());
        } catch (NumberFormatException e) {
            throw new EmptyDescriptionException(CommandType.DELETE);
        } catch (IOException e) {
            System.out.println("Something wrong when writing to txt");
        }
    }
}
