package duke.util;

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

public class Parser {

    private String commandType;
    private String commandArg;

    public Parser(String fullCommand) {
        String[] commandTypeAndArg = fullCommand.split(" ", 2);
        commandType = commandTypeAndArg[0].trim();
        commandArg = "";
        if (commandTypeAndArg.length > 1) {
            commandArg = commandTypeAndArg[1].trim();
        }
    }

    public Command getCommand() {
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
    
}
