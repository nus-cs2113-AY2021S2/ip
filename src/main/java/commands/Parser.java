package commands;

import duke.IncorrectFormatException;

public class Parser {
    private String input;

    /**
     * Parses the user input and returns the respective command
     *
     * @param input user input
     */
    public Command parseCommand(String input) throws IncorrectFormatException {
        String[] tokens = input.split(" ", 2);
        String commandWord = tokens[0];
        String arguments = "";
        if (tokens.length > 1) {
            try {
                arguments = tokens[1];
            } catch (IndexOutOfBoundsException e) {
                throw new IncorrectFormatException("Command format is incorrect!");
            }
        }
        switch (commandWord) {
        case TodoCommand.COMMAND_WORD: // If command is todo, parse the task, print confirmation
            return new TodoCommand(arguments);
        case ListCommand.COMMAND_WORD: // If command is list, print all tasks in list
            return new ListCommand();
        case DeadlineCommand.COMMAND_WORD: // If command is deadline, parse the task and by date, print confirmation
            return prepareDeadlineCommand(arguments);
        case EventCommand.COMMAND_WORD: // If command is event, parse the task and at date, print confirmation
            return prepareEventCommand(arguments);
        case DoneCommand.COMMAND_WORD: // If command is done, mark task as done, print confirmation
            return new DoneCommand(arguments);
        case DeleteCommand.COMMAND_WORD: // If command is  delete, delete task entry in list, print confirmation
            return new DeleteCommand(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments);
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default: // If command entered is any other unrecognised command, print invalid command
            return new IncorrectCommand("Incorrect command word was entered!");
        }
    }

    /**
     * Parses the imported file format and returns the respective command
     *
     * @param input input from imported file
     */
    public Command parseImportTasks(String input) throws IncorrectFormatException {
        String[] tokens = input.split("/");
        String command = "";
        switch (tokens[0]) {
        case "D":
            command = "deadline " + tokens[2] + " /by " + tokens[3];
            break;
        case "E":
            command = "event " + tokens[2] + " /at " + tokens[3];
            break;
        case "T":
            command = "todo " + tokens[2];
            break;
        }
        return parseCommand(command);
    }

    /**
     * Splits the description and date of the incoming deadline command
     *
     * @param args the input's combined description and date string
     */
    private Command prepareDeadlineCommand(String args) throws IncorrectFormatException {
        String[] parts = args.split("/by");
        // Validate arg string format
        if (parts.length != 2) {
            throw new IncorrectFormatException("Deadline command format is incorrect!");
        }
        return new DeadlineCommand(parts[0].trim(), parts[1].trim());
    }

    /**
     * Splits the description and date of the incoming event command
     *
     * @param args the input's combined description and date string
     */
    private Command prepareEventCommand(String args) throws IncorrectFormatException {
        String[] parts = args.split("/at");
        // Validate arg string format
        if (parts.length != 2) {
            throw new IncorrectFormatException("Event command format is incorrect!");
        }
        return new EventCommand(parts[0].trim(), parts[1].trim());
    }


}
