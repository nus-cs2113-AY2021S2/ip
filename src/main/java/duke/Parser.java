package duke;

import duke.error.*;

/**
 * Represents an instance of a parser. An Parse object corresponds to the processing of one input by the user. 
 */
public class Parser {
    /**
     * Extracts the command word from user input. Removes command name from
     * userCommand. If there are parameters after command word (i.e. length > 4),
     * set userCommand to the parameters. Otherwise, set userCommand to null.
     * 
     * @return Array containing parsed parameters including command, task status, task description and task date.
     */
    protected String[] getCommand(TaskList tasks, String fullCommand) throws InvalidSyntaxException, 
            TaskListEmptyException, IndexOutOfBoundsException, IllegalCommandException {
        // Split command according to first instance of " "
        String[] extractedCommands = fullCommand.split(" ", 2);

        // First index (i.e. 0) contains command word, next index contains parameters if any 
        String command = extractedCommands[Constants.COMMAND_INDEX].toLowerCase();
        return parseCommand(tasks, command, extractedCommands);
    }

    /**
     * Parses the command parameters based on the command word extracted. Returns the parameters in an array of 4. 
     * Array contains: [command, task status, task description, task date]. 
     * 
     * @return Array containing parsed parameters including command, task status, task description and task date.
     */
    private String[] parseCommand(TaskList tasks, String command, String[] extractedCommands) 
            throws InvalidSyntaxException, IndexOutOfBoundsException, 
            TaskListEmptyException, IllegalCommandException {
        switch (command) {
        case Constants.COMMAND_LIST_WORD:
        case Constants.COMMAND_EXIT_WORD:
            // No further parsing needed, return data immediately
            return setCommandParameters(command, null, null);
        case Constants.COMMAND_TODO_WORD:
        case Constants.COMMAND_FIND_WORD:
            checkCommandValidity(extractedCommands, command);
            return setCommandParameters(command, extractedCommands[1], null);
        case Constants.COMMAND_MARK_WORD:
        case Constants.COMMAND_DELETE_WORD:
            // Check if command is complete, throw exception if not 
            checkCommandValidity(extractedCommands, command);
            // Check remaining parameters for valid index (i.e. task number)
            // Add valid index to array, otherwise invoke error
            int taskNumber = getTaskNumber(command, tasks, extractedCommands[1]);
            if (!isTaskNumberValid(tasks, taskNumber)) {
                throw new IndexOutOfBoundsException();
            }
            return setCommandParameters(command, String.valueOf(taskNumber), null);
        case Constants.COMMAND_DEADLINE_WORD:
        case Constants.COMMAND_EVENT_WORD:
            checkCommandValidity(extractedCommands, command);
            String[] processCommandParameters = processParameters(command, extractedCommands[1]);
            // In parsedCommandParameters, first index contains task description and the second contains task date
            return setCommandParameters(command, processCommandParameters[0], processCommandParameters[1]);
        default:
            // Invalid command receieved, throw error
            throw new IllegalCommandException();
        }
    } 

    /**
     * Checks for the validity of command parameters. 
     * 
     * @param extractedCommands Commands extracted from input, split into 2 parts. The command is split with the first 
     *                          delimter (" "). 
     * @param command Command from extractedCommands. Effectively extractedCommand[1].toLowerCase(). 
     * @throws InvalidSyntaxException If there are missing parameters. 
     */
    private void checkCommandValidity(String[] extractedCommands, String command) throws InvalidSyntaxException{
        if (extractedCommands.length < 2) {
            Ui ui = new Ui();
            String message = ui.getSyntaxMessage(command);
            throw new InvalidSyntaxException(message);
        }
    }

    /**
     * Sets the array with the given parameters
     * 
     * @param command Command word. 
     * @param taskDescription The description of task. The task description is task number for "mark" and "delete" 
     *                        commands. 
     * @param taskDate Task date. Task date is valid only for "event" and "deadline" commands. 
     *                          Returns null for the other commands. 
     * @return Array containing parsed parameters including command, task status, task description and task date.
     */
    private String[] setCommandParameters (String command, String taskDescription, String taskDate) {
        return new String[]{command, "false", taskDescription, taskDate};
    }
    
    /**
     * Checks if task number is valid (i.e. within the bounds of the list).
     * 
     * @param taskNumber Task number.
     * @return True if number is valid and false if invalid.
     */
    private boolean isTaskNumberValid(TaskList tasks, int taskNumber) {
        return taskNumber >= 0 && taskNumber < tasks.getSize();
    }

    /**
     * Gets task number from the input string.
     * 
     * @return Task number (Starts with 0).
     * @throws NumberFormatException If userCommand is not an integer.
     * @throws IllegalCommandException If no task number is detected.
     * @throws TaskListEmptyException If task list is empty.
     */
    private int getTaskNumber(String command, TaskList tasks, String commandParameter) throws NumberFormatException, 
            TaskListEmptyException {
        if (tasks.isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        // Since user input task number starts from 1, remove 1 from taskNumber to reflect the correct index in tasks.
        int taskNumber = Integer.parseInt(getTaskNumberString(commandParameter)) - 1;
        return taskNumber;
    }

    /**
     * Gets task number in string.
     * 
     * @return Task number.
     */
    private String getTaskNumberString(String commandParameter) {
        // Gets the taskNumber from the index of the first " "
        // Adds 1 to the index remove the " " from string
        return commandParameter.substring(commandParameter.indexOf(" ") + 1);
    }

    /**
     * Finds the location of the filters and locate the date from the task. Removes
     * the task date information from userCommand, keeping only the task name in
     * userCommand.
     * 
     * @param filterString The string to find in userCommand depending on an event
     *                     (/at) or deadline (/by).
     * @return The date extracted from userCommand.
     * @throws InvalidSyntaxException If date is not found.
     */
    private String[] processParameters(String command, String commandParameter) throws InvalidSyntaxException {
        String filterString;
        if (command.equals(Constants.COMMAND_DEADLINE_WORD)) {
            filterString = Constants.DEADLINE_DATA_PREFIX_BY;
        } else {
            filterString = Constants.EVENT_DATA_PREFIX_AT;
        }
        String[] processCommand = getDate(command, commandParameter, filterString);
        return processCommand;
    }

    /**
     * Extracts the date from userCommand.
     * 
     * @param indexOfDate The index for the location of /by in userCommand. Indicates the begining of date.
     * @param filterString The string to find in userCommand depending on an event (/at) or deadline (/by).
     * @return Date specified in user input (userCommand).
     * @throws InvalidSyntaxException If no date is detected after the /by parameter.
     */
    private String[] getDate(String command, String commandParameter, String filterString) throws InvalidSyntaxException {
        int indexOfDate = commandParameter.indexOf(filterString);
        if (indexOfDate < 0) {
            Ui ui = new Ui();
            throw new InvalidSyntaxException(ui.getSyntaxMessage(command));
        }

        // Check if string contains date after filterString
        // +3 to remove filterString (/at or /by) and +1 to convert index to length. Total: +4
        if (commandParameter.length() < indexOfDate + 4) {
            Ui ui = new Ui();
            throw new InvalidSyntaxException(ui.getSyntaxMessage(command));
        }

        // Add 3 to indexOfDate to remove the "/by" or "/at" filter strings
        String date = commandParameter.substring(indexOfDate + 3).trim();
        String taskDescription = commandParameter.substring(0, indexOfDate).trim();
        
        return new String[]{taskDescription, date};
    }
}