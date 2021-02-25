package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

public class Parser {

    public static Command parse(String command) {
        // makes the input case-insensitive
        command = command.toLowerCase();
        String[] words = command.split(" ");
        if (command.contains("list")) {
            return new ListCommand(command);
        } else if (command.contains("done")) {
            return new DoneCommand(command);
        } else if (command.contains("delete")) {
            return new DeleteCommand(command);
        } else if (command.contains("find")) {
            return new FindCommand(command);
        } else if (words[0].equals("todo") || words[0].equals("deadline") || words[0]
            .equals("event")) {
            return new AddCommand(command);
        } else if (command.contains("bye")) {
            return new ByeCommand(command);
        } else {
            Ui.printCommandIsInvalid();
            return null;
        }
    }
}
