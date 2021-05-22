package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.InvalidCommandException;

/**
 * Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * parse method takes in a String of user input and returns the corresponding Command object based on it.
     *
     * @param fullCommand String of user input.
     * @return Command command object depending on user input.
     * @throws InvalidCommandException Thrown when user input is not a valid command.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        Command command;
        if (fullCommand.equals("bye")) {
            command = new ExitCommand();
        } else if (fullCommand.equals("list")) {
            command = new ListCommand();
        } else if (fullCommand.startsWith("done ")) {
            command = new DoneCommand(fullCommand);
        } else if (fullCommand.startsWith("todo ")) {
            command = new ToDoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline ")) {
            command = new DeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event ")) {
            command = new EventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete ")) {
            command = new DeleteCommand(fullCommand);
        } else if (fullCommand.startsWith("find ")) {
            command = new FindCommand(fullCommand);
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }
}
