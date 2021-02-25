package duke.parser;

import duke.exception.InvalidCommandException;
import duke.command.*;

public class Parser {

    /**
     * Returns the corresponding Command object based on user input
     *
     * @param line user input to be parsed
     * @return Command object
     * @throws InvalidCommandException If given line does not have a valid command
     */
    public static Command parse(String line) throws InvalidCommandException {
        Command command = null;
        if (line.equals("bye")) {
            command = new ByeCommand();
        } else if (line.equals("list")) {
            command = new ListCommand();
        } else if (line.startsWith("todo")) {
            command = new AddTodoCommand(line);
        } else if (line.startsWith("deadline")) {
            command = new AddDeadlineCommand(line);
        } else if (line.startsWith("event")) {
            command = new AddEventCommand(line);
        } else if (line.startsWith("done")) {
            command = new DoneCommand(line);
        } else if (line.startsWith("delete")) {
            command = new DeleteCommand(line);
        } else if (line.startsWith("find")) {
            command = new FindCommand(line);
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }
}
