public class Parser {
    private String input;

    public Command parseCommand(String input){
        String[] tokens = input.split(" ",2);
        String commandWord = tokens[0];
        if (tokens.length == 0) {
            return new incorrectCommand("Incorrect command format!");
        }
        String arguments = "";
        if (tokens.length > 1){
             arguments = tokens[1];
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


    private Command prepareDeadlineCommand(String args){
        String[] parts = args.split("/by");
        // Validate arg string format
        if (parts.length != 2) {
            return new incorrectCommand("Invalid deadline command format!");
        }
        return new deadlineCommand(parts[0].trim(), parts[1].trim());
    }

    private Command prepareEventCommand(String args){
        String[] parts = args.split("/at");
        // Validate arg string format
        if (parts.length != 2) {
            return new incorrectCommand("Invalid event command format!");
        }
        return new eventCommand(parts[0].trim(), parts[1].trim());
    }










}
