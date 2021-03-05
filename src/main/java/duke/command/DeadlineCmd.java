package duke.command;

import duke.*;
import duke.task.Deadline;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class DeadlineCmd extends Command{

    public DeadlineCmd(String s) {
        super(s);
    }

    /**
     * Executes a Deadline command according to the user input
     * @param tasks the arraylist of tasks currently stored
     * @param ui show messages to interact with users
     * @param storage update the file once the task lists changed
     * @throws DukeException if the description of the deadline is empty
     *                       if no /by found in the user input
     *                       if the data is not in form of yyyy-mm-dd.
     */
    public void execute(TaskManager tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] typeContentBy = userInput.trim().split("[Dd][Ee][Aa][Dd][Ll][Ii][Nn][Ee]", 2);
            String[] contentBy = typeContentBy[1].trim().split("/[Bb][Yy]", 2);
            if (contentBy[0].trim().equals("") || contentBy[1].trim().equals("")) {
                throw new DukeException(CommandType.DEADLINE);
            }

            Deadline d = tasks.addDeadline(contentBy[0].trim(), Parser.parseDate(contentBy[1].trim()));
            ui.showAddResult(d, tasks.getNumOfTasks());
            storage.writeToTxt(tasks.getTasks());
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showNoByMessage();
        } catch (IOException e) {
            ui.showWriteToFileError();
        } catch (DateTimeParseException e) {
            ui.showWrongDateMessage();
        }

    }


}
