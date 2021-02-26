package parser;

import commands.AddToDoCommand;
import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.Command;
import commands.ListCommand;
import exceptions.MissingInfoException;
import exceptions.UnknownCommandException;
import io.DukePrint;
import io.FileManager;
import models.TaskList;

import java.text.ParseException;

public class CommandParser {

    private final CommandVerifier verifier;
    private final TaskList taskList;
    private final DukePrint dukePrint;
    private FileManager fileManager;

    public CommandParser(TaskList taskList, DukePrint dukePrint) {
        assert taskList != null;
        assert dukePrint != null;

        verifier = new CommandVerifier();
        this.taskList = taskList;
        this.dukePrint = dukePrint;
    }

    public Command parse(String command) throws UnknownCommandException, MissingInfoException, ParseException {
        verifier.verify(command);
        return parseValidCommandString(command);
    }

    private Command parseValidCommandString(String command) throws ParseException {
        Command result = null;
        String[] subStrings = command.split(" ");

        switch (subStrings[0]) {
        case "bye":
            result = new ExitCommand(dukePrint);
            break;
        case "list":
            result = new ListCommand(taskList, dukePrint);
            break;
        case "done":
            result = new DoneCommand(taskList, Integer.parseInt(subStrings[1]), dukePrint);
            break;
        case "todo":
            result = new AddToDoCommand(taskList, dukePrint, command.substring(4).trim());
            break;
        case "deadline":
            String[] splitByFlag = command.split("/by");
            result = new AddDeadlineCommand(taskList, dukePrint,
                    splitByFlag[0].substring(9).trim(), splitByFlag[1].trim());
            break;
        case "event":
            splitByFlag = command.split("/at");
            result = new AddEventCommand(taskList, dukePrint,
                    splitByFlag[0].substring(6).trim(), splitByFlag[1].trim());
            break;
        case "delete":
            result = new DeleteCommand(taskList, Integer.parseInt(subStrings[1]), dukePrint);
            break;
        default:
            break;
        }

        return result;
    }
}
