package duke.command;

import duke.storage.Storage;
import duke.task.*;

public class ExitCommand extends Command {

    public ExitCommand() {
        this.exit = true;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeExceptions {
        System.out.println("bye");
    }

    @Override
    public boolean isExit() {
        return exit;
    }
}
