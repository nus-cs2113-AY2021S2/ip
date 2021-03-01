package duke.command;

import duke.CommandType;
import duke.EmptyDescriptionException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.TaskManager;

import java.io.IOException;

public class DeadlineCmd extends Command{

    public DeadlineCmd(String s) {
        super(s);
    }

    public void execute(TaskManager tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        try {
            String[] typeContentBy = userInput.trim().split("[Dd][Ee][Aa][Dd][Ll][Ii][Nn][Ee]", 2);
            String[] contentBy = typeContentBy[1].trim().split("/[Bb][Yy]", 2);
            if (contentBy[0].trim().equals("") || contentBy[1].trim().equals("")) {
                throw new EmptyDescriptionException(CommandType.DEADLINE);
            }
            Deadline d = tasks.addDeadline(contentBy[0].trim(), contentBy[1].trim());
            ui.showAddResult(d, tasks.getNumOfTasks());
            storage.writeToTxt(tasks.getTasks());
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            System.out.println("OOPS!!! No /by founded in the command");

        }

    }
}
