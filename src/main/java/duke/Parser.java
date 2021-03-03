package duke;

import java.time.format.DateTimeParseException;

import duke.error.IllegalCommandException;
import duke.error.TaskListEmptyException;
import duke.error.InvalidSyntaxException;

/**
 * Represents an instance of a parser. An Parse object corresponds to the processing of one input by the user. 
 */
public class Parser {
    /**
     * Extracts the command word from user input. Removes command name from
     * userCommand. If there are parameters after command word (i.e. length > 4),
     * set userCommand to the parameters. Otherwise, set userCommand to null.
     * 
     * @return Array of parsed parameters including command, task status, task description and task date.
     */
    protected String[] getCommand(TaskList tasks, String fullCommand) 
            throws NumberFormatException, InvalidSyntaxException, 
            TaskListEmptyException, IndexOutOfBoundsException, 
            IllegalCommandException, DateTimeParseException {
        String[] extractedCommands = splitCommand(fullCommand);
        String command = formatCommand(extractedCommands[Constants.COMMAND_INDEX]);
        return parseCommand(tasks, command, extractedCommands);
    }

    /**
     * Formats the command string into lowercase and remove all trailing spaces. 
     * 
     * @param extractedCommand Command word string to be formatted.  
     * @return The formatted command string. 
     */
    private String formatCommand(String extractedCommand) {
        return extractedCommand.toLowerCase().trim();
    }

    /**
     * Split command according to first instance of " ". 
     * First index (i.e. 0) contains command word, next index contains parameters if any. 
     * 
     * @param fullCommand Full string of user command input. 
     * @return The split commands stored in an array.  
     */
    private String[] splitCommand(String fullCommand) {
        String[] extractedCommands = fullCommand.split(" ", 2);
        return extractedCommands;
    }

    /**
     * Parses the command parameters based on the command word extracted. 
     * Array contains: [command, task status, task description, task date]. 
     * 
     * @return Array of parsed parameters including command, task status, task description and task date.
     */
    private String[] parseCommand(TaskList tasks, String command, String[] extractedCommands) 
            throws InvalidSyntaxException, IndexOutOfBoundsException, NumberFormatException, 
            TaskListEmptyException, IllegalCommandException {
        switch (command) {
        case Constants.COMMAND_LIST_WORD:
            // No further parsing needed, return data immediately
            return setCommandParameters(command, null, null);
        case Constants.COMMAND_EXIT_WORD:
            // No further parsing needed, return data immediately
            return setCommandParameters(command, null, null);
        case Constants.COMMAND_TODO_WORD:
            checkCommandValidity(extractedCommands, command);
            return setCommandParameters(command, extractedCommands[1], null);
        case Constants.COMMAND_FIND_WORD:
            checkCommandValidity(extractedCommands, command);
            return setCommandParameters(command, extractedCommands[1], null);
        case Constants.COMMAND_MARK_WORD:
            checkCommandValidity(extractedCommands, command);
            int markTaskNumber = getTaskNumber(command, tasks, extractedCommands[1]);
            if (!isTaskNumberValid(tasks, markTaskNumber)) {
                throw new IndexOutOfBoundsException();
            }
            return setCommandParameters(command, String.valueOf(markTaskNumber), null);
        case Constants.COMMAND_DELETE_WORD:
            checkCommandValidity(extractedCommands, command);
            int deleteTaskNumber = getTaskNumber(command, tasks, extractedCommands[1]);
            if (!isTaskNumberValid(tasks, deleteTaskNumber)) {
                throw new IndexOutOfBoundsException();
            }
            return setCommandParameters(command, String.valueOf(deleteTaskNumber), null);
        case Constants.COMMAND_DEADLINE_WORD:
            checkCommandValidity(extractedCommands, command);
            String[] deadlineCommandParameters = processParameters(command, extractedCommands[1].trim());
            // First index contains task description and the second contains task date
            return setCommandParameters(command, deadlineCommandParameters[0], deadlineCommandParameters[1]);
        case Constants.COMMAND_EVENT_WORD:
            checkCommandValidity(extractedCommands, command);
            String[] eventCommandParameters = processParameters(command, extractedCommands[1].trim());
            // First index contains task description and the second contains task date
            return setCommandParameters(command, eventCommandParameters[0], eventCommandParameters[1]);
        default:
            // Invalid command receieved, throw error
            throw new IllegalCommandException();
        }
    } 

    /**
     * Checks for the validity of command parameters. 
     * 
     * @param extractedCommands Commands extracted from input, split into 2 parts. The command is split with 
     *                          the first delimter (" "). 
     * @param command Command from extractedCommands. Effectively extractedCommand[1].toLowerCase(). 
     * @throws InvalidSyntaxException If there are missing parameters. 
     */
    private void checkCommandValidity(String[] extractedCommands, String command) 
            throws InvalidSyntaxException{
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
     * @param taskDescription The description of task. The task description is task number for "mark" and 
     *                        "delete" commands. 
     *                        The value is case insensitive and in lower case. 
     * @param taskDate Task date. Task date is valid only for "event" and "deadline" commands. 
     *                 Returns null for the other commands. 
     * @return Array containing parsed parameters including command, task status, task description and 
     *         task date.
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
     * Since user input task number starts from 1, remove 1 from taskNumber to reflect the correct index 
     * in tasks.
     * 
     * @return Task number (Starts with 0).
     * @throws NumberFormatException If userCommand is not an integer.
     * @throws IllegalCommandException If no task number is detected.
     * @throws TaskListEmptyException If task list is empty.
     */
    private int getTaskNumber(String command, TaskList tasks, String commandParameter) 
            throws NumberFormatException, TaskListEmptyException {
        if (tasks.isTaskListEmpty()) {
            throw new TaskListEmptyException();
        }
        int taskNumber = Integer.parseInt(commandParameter) - 1;
        return taskNumber;
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
    private String[] processParameters(String command, String commandParameter) 
            throws InvalidSyntaxException, DateTimeParseException {
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
    private String[] getDate(String command, String commandParameter, String filterString) 
            throws InvalidSyntaxException, DateTimeParseException {
        int indexOfDate = commandParameter.toLowerCase().indexOf(filterString);
        if (indexOfDate <= 0) {
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
        String dateString = commandParameter.substring(indexOfDate + 3).trim();
        String taskDescription = commandParameter.substring(0, indexOfDate).trim();
        return new String[]{taskDescription, dateString};
    }

}
