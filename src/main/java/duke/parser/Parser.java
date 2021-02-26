package duke.parser;

import duke.exception.InvalidCommandException;
import duke.command.*;

public class Parser {

    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

    /**
     * Returns the corresponding Command object based on user input
     *
     * @param line user input to be parsed
     * @return Command object
     * @throws InvalidCommandException If given line does not have a valid command
     */
    public static Command parse(String line) throws InvalidCommandException {
        Command command = null;
        if (line.equals(BYE_COMMAND)) {
            command = new ByeCommand();
        } else if (line.equals(LIST_COMMAND)) {
            command = new ListCommand();
        } else if (line.startsWith(TODO_COMMAND)) {
            command = new AddTodoCommand(line);
        } else if (line.startsWith(DEADLINE_COMMAND)) {
            command = new AddDeadlineCommand(line);
        } else if (line.startsWith(EVENT_COMMAND)) {
            command = new AddEventCommand(line);
        } else if (line.startsWith(DONE_COMMAND)) {
            command = new DoneCommand(line);
        } else if (line.startsWith(DELETE_COMMAND)) {
            command = new DeleteCommand(line);
        } else if (line.startsWith(FIND_COMMAND)) {
            command = new FindCommand(line);
        } else {
            throw new InvalidCommandException();
        }
        return command;
    }
}
