package duke.command;

import duke.ui.Ui;

/**
 * Says bye to user that wants to exit the program
 */
public class ExitCommand implements Command {
    public ExitCommand(String input) {
    }

    /**
     * Says bye to user that wants to exit the program
     *
     * @param input
     */
    public void execute(String input) {
        System.out.println("Bye. Hope to see you again soon!");
        Ui.commandDone();
    }

}
