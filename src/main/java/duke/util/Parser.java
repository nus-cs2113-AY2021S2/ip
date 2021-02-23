package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

public class Parser {

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

    private String getCommandArg(String fullCommand) {
        String[] commandTypeAndArg = parseCommand(fullCommand);
        String commandArg = commandTypeAndArg[1];
        return commandArg;
    }

    private String getCommandType(String fullCommand) {
        String[] commandTypeAndArg = parseCommand(fullCommand);
        String commandType = commandTypeAndArg[0];
        return commandType;
    }

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

    public String[] splitCommandArg(String commandType, String commandArg) {
        String[] taskDescriptionAndTime;
        String delimiter = getDelimiter(commandType);
        taskDescriptionAndTime = commandArg.split(delimiter, 2);
        taskDescriptionAndTime[0] = taskDescriptionAndTime[0].trim();
        return taskDescriptionAndTime;
    }

    private static boolean isEmptyTimeArg(String commandType, String[] taskDescriptionAndTime) {
        return taskDescriptionAndTime.length == 1 || taskDescriptionAndTime[1].equals("");
    }

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
    
    public String getDescription(String commandType, String commandArg) {
        String[] taskDescriptionAndTime = splitCommandArg(commandType, commandArg);
        return taskDescriptionAndTime[0];
    }
    
    public String getDateTimeString(String commandType, String commandArg) {
        String[] taskDescriptionAndTime = splitCommandArg(commandType, commandArg);
        if (isEmptyTimeArg(commandType, taskDescriptionAndTime)) {
            return "";
        }
        taskDescriptionAndTime[1] = taskDescriptionAndTime[1].trim();
        return taskDescriptionAndTime[1];
    }

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

    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return dateTime;
    }

	public Task parseTask(String line) throws DateTimeParseException, InvalidCommandTimeException {
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
