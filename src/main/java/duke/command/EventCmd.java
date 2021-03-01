package duke.command;

import duke.CommandType;
import duke.EmptyDescriptionException;
import duke.Storage;
import duke.Ui;
import duke.task.Event;
import duke.TaskManager;

import java.io.IOException;

public class EventCmd extends Command{
    public EventCmd(String s) {
        super(s);
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        try {
            String[] typeContentAt= userInput.trim().split("[Ee][Vv][Ee][Nn][Tt]", 2);
            String[] contentAt = typeContentAt[1].trim().split("/[Aa][Tt]", 2);
            if (contentAt[0].trim().equals("") || contentAt[1].trim().equals("")) {
                throw new EmptyDescriptionException(CommandType.EVENT);
            }
            Event e = tasks.addEvent(contentAt[0].trim(), contentAt[1].trim());
            ui.showAddResult(e, tasks.getNumOfTasks());
            storage.writeToTxt(tasks.getTasks());
        } catch (IndexOutOfBoundsException e){
            System.out.println("OOPS!!! No /at founded in the command");
        } catch (IOException e) {
            System.out.println("Something wrong when writing to txt");
        }
    }
}
