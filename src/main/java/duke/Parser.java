package duke;

import duke.command.MarkCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.FindCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;

public class Parser {
    private static final int COMMAND_EXIT = 0;
    private static final int COMMAND_LIST = 1;
    private static final int COMMAND_MARK = 2;
    private static final int COMMAND_ADD = 3;
    private static final int COMMAND_DELETE = 4;
    private static final int COMMAND_FIND = 5;

    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses the command user wants to execute and creates new instance of said command.
     *
     * @param line user input.
     * @return new instance of command.
     */
    public Command parseCommands(String line) {
        int command = getCommand(line);
        // No fallthrough required
        switch (command) {
        case COMMAND_MARK:
            return new MarkCommand(line, this.tasks, this.ui);
        case COMMAND_LIST:
            return new ListCommand(line, this.tasks, this.ui);
        case COMMAND_ADD:
            return new AddCommand(line, this.tasks, this.ui);
        case COMMAND_DELETE:
            return new DeleteCommand(line, this.tasks, this.ui);
        case COMMAND_FIND:
            return new FindCommand(line, this.tasks, this.ui);
        // If user wants to exit program
        default:
            return new ByeCommand();
        }
    }

    /**
     * Parses the command that user has keyed in and returns a constant integer representing type of command.
     *
     * @param line input from user.
     * @return The type of command user inputted.
     */
    public static int getCommand(String line) {
        String[] subStrings = line.split(" ");
        String command = subStrings[0];
        if (command.equalsIgnoreCase("bye")) {
            return COMMAND_EXIT;
        }
        if (command.equalsIgnoreCase("done")) {
            return COMMAND_MARK;
        }
        if (command.equalsIgnoreCase("list")) {
            return COMMAND_LIST;
        }
        if (command.equalsIgnoreCase("delete")) {
            return COMMAND_DELETE;
        }
        if (command.equalsIgnoreCase("find")) {
            return COMMAND_FIND;
        }
        return COMMAND_ADD;
    }
}
