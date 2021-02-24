package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.HelpCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exception.InvalidCommandTimeException;
import duke.exception.InvalidTaskNumberException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents an object that deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns the appropriate <code>Command</code> object based on user input.
     * 
     * @param fullCommand string containing the full command as provided by user
     * @return Command command object
     */
    public Command getCommand(String fullCommand) {
        String commandType = getCommandType(fullCommand);
        String commandArg = getCommandArg(fullCommand);

        Command command;
        switch (commandType) {
            case "help":
                command = new HelpCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "done":
                command = new DoneCommand(commandArg);
                break;
            case "todo":
                command = new TodoCommand(commandArg);
                break;
            case "deadline":
                command = new DeadlineCommand(commandArg);
                break;
            case "event":
                command = new EventCommand(commandArg);
                break;
            case "delete":
                command = new DeleteCommand(commandArg);
                break;
            case "bye":
                command = new ByeCommand();
                break;
            default:
                command = new InvalidCommand(commandType);
        }
        return command;
    }

    /**
     * Returns the breakdown of the full input provided by the user in an array.
     * Index 0 - command type
     * Index 1 - command argument
     * 
     * @param String full command string as provided by user
     * @return
     */
    private String[] parseCommand(String fullCommand) {
        String[] commandTypeAndArg = new String[2];
        String[] rawCommandTypeAndArg = fullCommand.split(" ", 2);
        commandTypeAndArg[0] = rawCommandTypeAndArg[0].trim();
        if (rawCommandTypeAndArg.length > 1) {
            commandTypeAndArg[1] = rawCommandTypeAndArg[1].trim();
        } else {
            commandTypeAndArg[1] = "";
        }
        return commandTypeAndArg;
    }

    /**
     * Returns the command argument
     * 
     * @param  fullCommand string containing the full command as provided by user
     * @return command argument
     */
    private String getCommandArg(String fullCommand) {
        String[] commandTypeAndArg = parseCommand(fullCommand);
        String commandArg = commandTypeAndArg[1];
        return commandArg;
    }

    /**
     * Returns the command type
     * 
     * @param fullCommand string containing the full command as provided by user
     * @return command type
     */
    private String getCommandType(String fullCommand) {
        String[] commandTypeAndArg = parseCommand(fullCommand);
        String commandType = commandTypeAndArg[0];
        return commandType;
    }

    /**
     * Returns the task number
     * 
     * @param commandArg command argument provided by the user
     * @param taskList <code>TaskList</code> object containing all tasks
     * @return task number
     * @throws InvalidTaskNumberException if command arugment is not a number or if task number is out of bounds
     */
    public static int getTaskNumber(String commandArg, TaskList taskList) throws InvalidTaskNumberException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(commandArg);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(commandArg);
        }

        if (taskNumber < 1 || taskNumber > taskList.getListSize()) {
            throw new InvalidTaskNumberException(taskNumber);
        }
        return taskNumber;
    }

    /**
     * Returns an array containing command arguments
     * Index 0 - task description
     * Index 1 - task due date
     * 
     * @param commandType string representing command type
     * @param commandArg string representing command arguments
     * @return array containing command arguments
     */
    public String[] splitCommandArg(String commandType, String commandArg) {
        String[] taskDescriptionAndTime;
        String delimiter = getDelimiter(commandType);
        taskDescriptionAndTime = commandArg.split(delimiter, 2);
        taskDescriptionAndTime[0] = taskDescriptionAndTime[0].trim();
        return taskDescriptionAndTime;
    }

    /**
     * Returns true if command arguments do not contain a due date argument, and false otherwise
     * 
     * @param commandType string representing command type
     * @param taskDescriptionAndTime an array containing command arguments
     * @return boolean representing if the time argument is empty
     */
    private static boolean isEmptyTimeArg(String commandType, String[] taskDescriptionAndTime) {
        return taskDescriptionAndTime.length == 1 || taskDescriptionAndTime[1].equals("");
    }

    /**
     * Returns the command delimiter of command types "deadline" and "event".
     * If other command types are given, <code>null</code> is returned.
     * 
     * @param commandType string representing command type
     * @return delimiter
     */
    private static String getDelimiter(String commandType) {
        String delimiter = null;
        switch (commandType) {
            case "deadline":
                delimiter = "/by";
                break;
            case "event":
                delimiter = "/at";
                break;
        }
        return delimiter;
    }

    /**
     * Returns the description argument provided by user
     * 
     * @param commandType string representing command type
     * @param commandArg string representing command arguments
     * @return string representing description
     */
    public String getDescription(String commandType, String commandArg) {
        String[] taskDescriptionAndTime = splitCommandArg(commandType, commandArg);
        return taskDescriptionAndTime[0];
    }
   
    /**
     * Returns string representing date and time
     * @param commandType string representing command type
     * @param commandArg string representing command arguments
     * @return string representing date and time
     */
    public String getDateTimeString(String commandType, String commandArg) {
        String[] taskDescriptionAndTime = splitCommandArg(commandType, commandArg);
        if (isEmptyTimeArg(commandType, taskDescriptionAndTime)) {
            return "";
        }
        taskDescriptionAndTime[1] = taskDescriptionAndTime[1].trim();
        return taskDescriptionAndTime[1];
    }

    /**
     * Returns <code>LocalDateTime</code> object from command arguments based on command type
     * 
     * @param commandType string representing command type
     * @param commandArg string representing command arguments
     * @return <code>LocalDateTime</code> object
     * @throws InvalidCommandTimeException if user input is of invalid format
     */
    public LocalDateTime getDateTime(String commandType, String commandArg) throws InvalidCommandTimeException {
        String dateTimeString = getDateTimeString(commandType, commandArg);
        LocalDateTime dateTime;
        try {
            dateTime = parseDateTime(dateTimeString);
        } catch (Exception e) {
            throw new InvalidCommandTimeException(commandType, e);
        }

        return dateTime;
    }

    /**
     * Returns <code>LocalDateTime</code> object from string representing date and time
     * 
     * @param dateTimeString string representing date and time
     * @return <code>LocalDateTime</code> object
     */
    public LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }

    /**
     * Returns <code>Task</code> object based on saved task details
     * 
     * @param line string containing task details delimited by "~"
     * @return <code>Task</code> object
     * @throws InvalidCommandTimeException if user input for due date is of invalid format
     */
	public Task parseTask(String line) throws InvalidCommandTimeException {
	    String[] tokens = line.split("~");
	    String taskType = tokens[0];
	    String isDone = tokens[1];
	    String description = tokens[2];
        Task task = new Task(description);
        LocalDateTime dateTime;
        String dateTimeString;
	    switch (taskType) {
	        case "Todo":
	            task = new Todo(description);
	            break;
	        case "Deadline":
                dateTimeString = tokens[3];
                dateTime = parseDateTime(dateTimeString);
	            task = new Deadline(description, dateTime);
	            break;
	        case "Event":
	            dateTimeString = tokens[3];
                dateTime = parseDateTime(dateTimeString);
	            task = new Event(description, dateTime);
	            break;
	    }
	    if (isDone == String.valueOf(true)) {
	        task.setIsDone();
	    }
	    return task;
	}
    
}
