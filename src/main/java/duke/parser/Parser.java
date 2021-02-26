package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
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
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }
}
