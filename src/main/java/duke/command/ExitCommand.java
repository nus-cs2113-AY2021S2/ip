package duke.command;

import duke.Constants;
import duke.Ui;

/**
 * Represents the exit command. An ExitCommand object corresponds to the exit command input by the user. 
 */
public class ExitCommand {
    protected static void executeExitProgramRequest(Ui ui) {
        ui.displayToUser(Constants.MESSAGE_EXIT);
        ui.displayMessageBorder();
        System.exit(0);
    }
}