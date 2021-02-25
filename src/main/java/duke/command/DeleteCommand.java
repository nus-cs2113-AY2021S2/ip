package duke.command;

import duke.Duke;

public class DeleteCommand extends Command{
    public DeleteCommand(String description) {
        Duke.deleteTask(description);
    }
}
