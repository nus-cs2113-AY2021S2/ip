package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

/**
 * This class deals with making sense of the user input
 */
public class Parser {

    /**
     * This method takes in the input entered and returns a Command object accordingly
     * @param command is the input entered by the user
     * @return the appropriate Command object depending on the input entered, or null
     * if the input is invalid
     */
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
            throw new NullPointerException();
        }
    }
}
