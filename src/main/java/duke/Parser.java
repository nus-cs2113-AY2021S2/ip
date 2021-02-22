package duke;

import duke.error.*;

public class Parser {
    public Parser() {

    }

    /**
     * Extracts the command word from user input. Removes command name from
     * userCommand. If there are parameters after command word (i.e. length > 4),
     * set userCommand to the parameters. Otherwise, set userCommand to null.
     * 
     * @return Command word extracted.
     */
    protected String[] getCommand(TaskList tasks, String fullCommand) throws NumberFormatException, InvalidSyntaxException, TaskListEmptyException, IndexOutOfBoundsException, IllegalCommandException {
        String[] parsedCommands = new String[4];

        // Split command according to first instance of " "
        String[] extractedCommands = fullCommand.split(" ", 2);

        // First index (i.e. 0) contains command word, next index contains parameters if any 
        parsedCommands[Constants.COMMAND_INDEX] = extractedCommands[Constants.COMMAND_INDEX].toLowerCase();
        parsedCommands[Constants.MARK_INDEX] = "false";

        switch (parsedCommands[Constants.COMMAND_INDEX]) {
        case Constants.COMMAND_LIST_WORD:
        case Constants.COMMAND_EXIT_WORD:
            // No further parsing needed, return immediately
            break;
        case Constants.COMMAND_TODO_WORD:
        case Constants.COMMAND_FIND_WORD:
            if (extractedCommands.length < 2) {
                Ui ui = new Ui();
                throw new InvalidSyntaxException(ui.getSyntaxMessage(parsedCommands[Constants.COMMAND_INDEX]));
            }
            // Add remaining parameters to array (i.e. task description)
            parsedCommands[Constants.TASK_DESCRIPTION_INDEX] = extractedCommands[1];
            parsedCommands[Constants.TASK_DATE_INDEX] = null;
            break;
        case Constants.COMMAND_MARK_WORD:
        case Constants.COMMAND_DELETE_WORD:
            // Check if command is complete, throw exception if not 
            if (extractedCommands.length < 2) {
                Ui ui = new Ui();
                throw new InvalidSyntaxException(ui.getSyntaxMessage(parsedCommands[Constants.COMMAND_INDEX]));
            }

            // Check remaining parameters for valid index (i.e. task number)
            // Add valid index to ArrayList, otherwise invoke error
            int taskNumber = getTaskNumber(parsedCommands[Constants.COMMAND_INDEX], tasks, extractedCommands[1]);
            if (!isTaskNumberValid(tasks, taskNumber)) {
                throw new IndexOutOfBoundsException();
            }
            parsedCommands[Constants.TASK_DESCRIPTION_INDEX] = String.valueOf(taskNumber);
            break;
        case Constants.COMMAND_DEADLINE_WORD:
        case Constants.COMMAND_EVENT_WORD:
            if (extractedCommands.length < 2) {
                Ui ui = new Ui();
                throw new InvalidSyntaxException(ui.getSyntaxMessage(parsedCommands[Constants.COMMAND_INDEX]));
            }

            // Get task description and date (after /by or /at clause)
            // Append both into commands and return
            String[] processCommandParameters = processParameters(parsedCommands[Constants.COMMAND_INDEX], extractedCommands[1]);
            
            // In parsedCommandParameters, first index contains task description and the second contains task date
            parsedCommands[Constants.TASK_DESCRIPTION_INDEX] = processCommandParameters[0];
            parsedCommands[Constants.TASK_DATE_INDEX] = processCommandParameters[1];
            break;
        default:
            // Invalid command receieved, throw error
            throw new IllegalCommandException();
        }
        return parsedCommands;
    }
    
    /**
     * Checks if task number is valid.
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
     * @throws NumberFormatException    If userCommand is not an integer.
     * @throws IllegalCommandException If no task number is detected.
     * @throws TaskListEmptyException   If task list is empty.
     */
    private int getTaskNumber(String command, TaskList tasks, String commandParameter) throws NumberFormatException, InvalidSyntaxException, TaskListEmptyException {
        if (tasks.isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        // Since user input task number starts from 1, remove 1 from taskNumber to
        // reflect the correct index in tasks.
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
     * @param indexOfDate  The index for the location of /by in userCommand.
     *                     Indicates the begining of date.
     * @param filterString The string to find in userCommand depending on an event
     *                     (/at) or deadline (/by).
     * @return Date specified in user input (userCommand).
     * @throws InvalidSyntaxException If no date is detected after the /by
     *                     parameter.
     */
    private String[] getDate(String command, String commandParameter, String filterString) throws InvalidSyntaxException {
        int indexOfDate = commandParameter.indexOf(filterString);
        if (indexOfDate < 0) {
            Ui ui = new Ui();
            throw new InvalidSyntaxException(ui.getSyntaxMessage(command));
        }

        // Check if string contains date after filterString
        // +3 to remove filterString and +1 to convert index to length. Total: +4
        if (commandParameter.length() < indexOfDate + 4) {
            Ui ui = new Ui();
            throw new InvalidSyntaxException(ui.getSyntaxMessage(command));
        }

        String date = commandParameter.substring(indexOfDate + 3).trim();
        String taskDescription = commandParameter.substring(0, indexOfDate).trim();
        // Add 3 to indexOfDate to remove the "/by" or "/at" filter strings
        return new String[]{taskDescription, date};
    }
}
