package duke.commands;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

public class ByeCommand extends Command {
    public static final String BYE_WORD = "bye";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        setExit(true);
        ui.printStatements(EXIT_MESSAGE);
    }
}
