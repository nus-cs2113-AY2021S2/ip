package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.FinishCommand;
import duke.commands.TodoCommand;
import duke.commands.EventCommand;
import duke.commands.DeadlineCommand;
import duke.commands.SaveCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.UnknownCommand;
import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.storage.Storage;

import static duke.common.Messages.DIVIDER;

/**
 * Parser class parses the command received and executes the various commands. 
 */
public class Parser {

    private TextUI ui;
    private TaskList taskList;
    private Storage storage;
    public static final String INVALID_DATE = "!!! Invalid date. Please make sure date is in dd/mm/yy format. !!!";

    /**
     * Constructor for the <code>Parser</code> class. 
     * 
     * @param ui ui instance for the input and output of the parser
     * @param taskList tasklist instance on which the commands will be executed
     * @param storage storage instance on which save and load commands will be executed
     */
    public Parser(TextUI ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Parse the incoming command and calls the corresponding command methods. 
     * 
     * @param commandString string input from the user
     * @return <code>Command</code> recognised by parser, or <code>UnknownCommand</code>
     */
    public Command parse(String commandString) {
        String commandWord = commandString.split(" ", 2)[0];
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand(taskList, ui, storage);
        case ListCommand.COMMAND_WORD:
            return new ListCommand(taskList);
        case FinishCommand.COMMAND_WORD:
            return checkFinish(commandString);
        case TodoCommand.COMMAND_WORD:
            return checkTodo(commandString);
        case EventCommand.COMMAND_WORD:
            return checkEvent(commandString);
        case DeadlineCommand.COMMAND_WORD:
            return checkDeadline(commandString);
        case DeleteCommand.COMMAND_WORD:
            return checkDelete(commandString);
        case SaveCommand.COMMAND_WORD:
            return checkSave(commandString);
        case FindCommand.COMMAND_WORD:
            return checkFind(commandString);
        default:
            return new UnknownCommand(taskList);
        }
    }

    /**
     * Checks the arguments for the mark as done command. 
     * 
     * @param commandString string input from the user
     * @return <code>FinishCommand</code> with input arguments, <code>null</code> if invalid arguments
     */
    private Command checkFinish(String commandString) {
        String[] splitCommand = commandString.split(" ", 2);
        if (splitCommand.length==1) {
            ui.noIndex("done");
            return null;
        } else {
            int index = Integer.parseInt(splitCommand[1])-1;
            return new FinishCommand(taskList, index);
        }
    }

    /**
     * Checks the arguments for the todo command. 
     * 
     * @param commandString string input from the user
     * @return <code>TodoCommand</code> with input arguments, <code>null</code> if invalid arguments
     */
    private Command checkTodo(String commandString) {
        String[] splitCommand = commandString.split(" ", 2);
        if (splitCommand.length==1) {
            ui.noDescription("todo");
            return null;
        } else {
            String description = splitCommand[1];
            return new TodoCommand(taskList, description);
        }
    }

    /**
     * Checks the arguments for the event command. 
     * 
     * @param commandString string input from the user
     * @return <code>EventCommand</code> with input arguments, <code>null</code> if invalid arguments
     */
    private Command checkEvent(String commandString) {
        String[] splitCommand = commandString.split(" ", 2);
        if (splitCommand.length==1) {
            ui.noDescription("event");
            return null;
        } else {
            try {
                String description = splitCommand[1].split("/at")[0].strip();
                String dateString = splitCommand[1].split("/at")[1].strip();
                try {
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yy"));
                    return new EventCommand(taskList, description, date);
                } catch (DateTimeParseException e) {
                    ui.printToScreen(DIVIDER, INVALID_DATE, DIVIDER);
                    return null;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.noDate("event");
                return null;
            }
        }
    }

    /**
     * Checks the arguments for the deadline command. 
     * 
     * @param commandString string input from the user
     * @return <code>DeadlineCommand</code> with input arguments, <code>null</code> if invalid arguments
     */
    private Command checkDeadline(String commandString) {
        String[] splitCommand = commandString.split(" ", 2);
        if (splitCommand.length==1) {
            ui.noDescription("deadline");
            return null;
        } else {
            try {
                String description = splitCommand[1].split("/by")[0].strip();
                String dateString = splitCommand[1].split("/by")[1].strip();
                try {
                    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yy"));
                    return new DeadlineCommand(taskList, description, date);
                } catch (DateTimeParseException e) {
                    ui.printToScreen(DIVIDER, INVALID_DATE, DIVIDER);
                    return null;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.noDate("deadline");
                return null;
            }
        }
    }

    /**
     * Checks the arguments for the delete command. 
     * 
     * @param commandString string input from the user
     * @return <code>DeleteCommand</code> with input arguments, <code>null</code> if invalid arguments
     */
    private Command checkDelete(String commandString) {
        String[] splitCommand = commandString.split(" ", 2);
        if (splitCommand.length==1) {
            ui.noIndex("delete");
            return null;
        } else {
            int taskIndex = Integer.parseInt(splitCommand[1])-1;
            return new DeleteCommand(taskList, taskIndex);
        }
    }

    /**
     * Checks the arguments for the save command. 
     * 
     * @param commandString string input from the user
     * @return <code>SaveCommand</code> with input arguments, <code>null</code> if invalid arguments
     */
    private Command checkSave(String commandString) {
        return new SaveCommand(taskList, storage);
    }

    /**
     * Checks the arguments for the find command. 
     * 
     * @param commandString string input from the user
     * @return <code>FindCommand</code> with input arguments, <code>null</code> if invalid arguments
     */
    private Command checkFind(String commandString) {
        String[] splitCommand = commandString.split(" ", 2);
        if (splitCommand.length==1) {
            ui.noIndex("find");
            return null;
        } else {
            String searchWord = splitCommand[1];
            return new FindCommand(taskList, searchWord);
        }
    }

}
