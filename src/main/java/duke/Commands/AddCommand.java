package duke.Commands;

import duke.Exceptions.DeadlineFormatException;
import duke.Exceptions.DukeException;
import duke.Exceptions.EventFormatException;
import duke.Storage;
import duke.TaskList;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Todo;
import duke.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    private final String commandWord, description;

    public AddCommand(String commandWord, String description) {
        this.commandWord = commandWord;
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int dividerPosition;
        switch (commandWord) {
        case "todo":
            taskList.addToTaskList(new Todo(description));
            break;
        case "deadline":
            if (!description.contains("/by")) {
                throw new DeadlineFormatException();
            }
            dividerPosition = description.indexOf("/by");
            try {
                String by = description.substring(dividerPosition + 4);
                taskList.addToTaskList(new Deadline(description.substring(0, dividerPosition - 1), by));
            } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
                throw new DeadlineFormatException();
            }
            break;
        case "event":
            if (!description.contains("/at")) {
                throw new EventFormatException();
            }
            dividerPosition = description.indexOf("/at");
            try {
                String at = description.substring(dividerPosition + 4);
                taskList.addToTaskList(new Event(description.substring(0, dividerPosition - 1), at));
            } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
                throw new EventFormatException();
            }
            break;
        }

        ui.showAddTask(taskList.getTaskAtIndex(taskList.getSize() - 1), taskList.getSize());

        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.showSaveError();
        }
    }
}
