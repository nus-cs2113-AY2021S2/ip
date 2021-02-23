package duke.commands;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Ui;

public class HelpCommand extends Command {

    public HelpCommand() {
        super();
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Parser parser) {
        ui.printHelpMessage();
    }
}