package duke.command;

import duke.*;
import duke.task.Event;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCmd extends Command{
    public EventCmd(String s) {
        super(s);
    }

    /**
     * Executes a Event command according to the user input
     * @param tasks the arraylist of tasks currently stored
     * @param ui show messages to interact with users
     * @param storage update the file once the task lists changed
     * @throws DukeException if the description of the event is empty
     *                       if no /at found in the user input
     *                       if the data is not in form of yyyy-mm-dd.
     */
    public void execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] typeContentAt= userInput.trim().split("[Ee][Vv][Ee][Nn][Tt]", 2);
            String[] contentAt = typeContentAt[1].trim().split("/[Aa][Tt]", 2);
            if (contentAt[0].trim().equals("") || contentAt[1].trim().equals("")) {
                throw new DukeException(CommandType.EVENT);
            }
            Event e = tasks.addEvent(contentAt[0].trim(), Parser.parseDate(contentAt[1].trim()));
            ui.showAddResult(e, tasks.getNumOfTasks());
            storage.writeToTxt(tasks.getTasks());
        } catch (IndexOutOfBoundsException e){
            ui.showNoAtMessage();
        } catch (IOException e) {
            ui.showWriteToFileError();
        } catch (DateTimeParseException e) {
            ui.showWrongDateMessage();
        }
    }


}
