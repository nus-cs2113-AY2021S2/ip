package duke.command;

import duke.command.Command;

public class ExitCommand implements Command {
    public ExitCommand(String input) {
    }

    public void execute(String input) {
        System.out.println("Bye. Hope to see you again soon!" + System.lineSeparator());
        end();
    }

    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }
}
