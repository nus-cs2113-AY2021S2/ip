package duke;

import duke.command.*;

import java.io.IOException;

public class Parser {
    private static final int ERR_MAX_CAPACITY = -5;
    private static final int COMMAND_EXIT = 0;
    private static final int COMMAND_LIST = 1;
    private static final int COMMAND_MARK = 2;
    private static final int COMMAND_ADD = 3;
    private static final int COMMAND_DELETE = 4;

    private TaskList tasks;
    private Ui ui;

    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Loops through all features available.
     * Returns if user inputs bye.
     */
    public Command parseCommands(String line) throws IOException {
        try {
            int command = getCommand(line);
            // No fallthrough required
            switch (command) {
            // If user wants to mark a task as done
            case COMMAND_MARK:
                return new MarkCommand(line, this.tasks, this.ui);
            // If user wants to list all tasks
            case COMMAND_LIST:
                return new ListCommand(line, this.tasks, this.ui);
            // If user wants to add an item
            case COMMAND_ADD:
                return new AddCommand(line, this.tasks, this.ui);
            // If user wants to delete an item
            case COMMAND_DELETE:
                return new DeleteCommand(line, this.tasks, this.ui);
            // IF user wants to exit program
            case COMMAND_EXIT:
                return new ByeCommand();
            }
        } catch (IndexOutOfBoundsException e) {
                ui.printError(ERR_MAX_CAPACITY);
        }
        return null; // To make compiler happy :D
    }
    /**
     * Parses the command that user has keyed in.
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
        return COMMAND_ADD;
    }
}
