package duke.parser;

import duke.command.*;
import duke.exception.InvalidCommandException;

public class Parser {
    public static Command parse(String fullCommand) throws InvalidCommandException {
        Command command;
        if (fullCommand.equals("bye")) {
            command = new ExitCommand();
        } else if (fullCommand.equals("list")) {
            command = new ListCommand();
        } else if (fullCommand.startsWith("done")) {
            command = new DoneCommand(fullCommand);
        } else if (fullCommand.startsWith("todo")) {
            command = new ToDoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            command = new DeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            command = new EventCommand(fullCommand);
        } else if (fullCommand.startsWith("delete")) {
            command = new DeleteCommand(fullCommand);
        } else if (fullCommand.startsWith("find")) {
            command = new FindCommand(fullCommand);
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }
}
