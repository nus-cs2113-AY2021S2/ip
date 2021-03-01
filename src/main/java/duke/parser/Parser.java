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
import duke.commands.DeleteCommand;
import duke.commands.SaveCommand;
import duke.commands.UnknownCommand;
import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.storage.Storage;

import static duke.common.Messages.DIVIDER;

public class Parser {

    private TextUI ui;
    private TaskList taskList;
    private Storage storage;
    public static final String INVALID_DATE = "!!! Invalid date. Please make sure date is in dd/mm/yy format. !!!";

    public Parser(TextUI ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

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
        default:
            return new UnknownCommand(taskList);
        }
    }

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

    private Command checkSave(String commandString) {
        return new SaveCommand(taskList, storage);
    }

}
