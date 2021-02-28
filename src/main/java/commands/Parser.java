package commands;

import duke.IncorrectFormatException;

public class Parser {
    private String input;

    public Command parseCommand(String input) throws IncorrectFormatException {
        String[] tokens = input.split(" ",2);
        String commandWord = tokens[0];
        String arguments = "";
        if (tokens.length > 1) {
            try {
                arguments = tokens[1];
            }catch(IndexOutOfBoundsException e){
                throw new IncorrectFormatException("Command format is incorrect!");
            }
        }
        switch(commandWord){
        case todoCommand.COMMAND_WORD: // If command is todo, parse the task, print confirmation
            return new todoCommand(arguments);
        case listCommand.COMMAND_WORD: // If command is list, print all tasks in list
            return new listCommand();
        case deadlineCommand.COMMAND_WORD: // If command is deadline, parse the task and by date, print confirmation
            return prepareDeadlineCommand(arguments);
        case eventCommand.COMMAND_WORD: // If command is event, parse the task and at date, print confirmation
            return prepareEventCommand(arguments);
        case doneCommand.COMMAND_WORD: // If command is done, mark task as done, print confirmation
            return new doneCommand(arguments);
        case deleteCommand.COMMAND_WORD: // If command is  delete, delete task entry in list, print confirmation
            return new deleteCommand(arguments);
        case byeCommand.COMMAND_WORD:
            return new byeCommand();
        default: // If command entered is any other unrecognised command, print invalid command
            return new incorrectCommand("Incorrect command word was entered!");
        }
    }
    public Command parseImportTasks(String input) throws IncorrectFormatException {
        String[] tokens = input.split("/");
        String command = "";
        switch(tokens[0]){
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
    private Command prepareDeadlineCommand(String args) throws IncorrectFormatException {
        String[] parts = args.split("/by");
        // Validate arg string format
        if (parts.length != 2) {
            throw new IncorrectFormatException("Deadline command format is incorrect!");
        }
        return new deadlineCommand(parts[0].trim(), parts[1].trim());
    }

    private Command prepareEventCommand(String args) throws IncorrectFormatException {
        String[] parts = args.split("/at");
        // Validate arg string format
        if (parts.length != 2) {
            throw new IncorrectFormatException("Event command format is incorrect!");
        }
        return new eventCommand(parts[0].trim(), parts[1].trim());
    }










}
