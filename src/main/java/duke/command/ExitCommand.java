package duke.command;

import duke.Ui;
import duke.command.Command;

public class ExitCommand implements Command {
    public ExitCommand(String input) {
    }

    public void execute(String input) {
        System.out.println("Bye. Hope to see you again soon!" + System.lineSeparator());
        Ui.commandDone();
    }

}
