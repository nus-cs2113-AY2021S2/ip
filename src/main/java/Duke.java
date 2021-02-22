import java.util.Scanner;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.exception.InvalidCommandException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class Duke {
    public static Ui ui;
    public static TaskList taskList;
    private static Storage storage;
    private static String home;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(ui);
        home = System.getProperty("user.dir");
    }

    public static void main(String[] args) {
        new Duke();
        ui.displayWelcomeMessage();
        storage.loadHistory(home, taskList);
        inputAndExecuteCommand();
        storage.saveHistory(home, taskList);
        ui.displayExitMessage();
    }

    public static void inputAndExecuteCommand() {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        String fullCommand;
        while (!isExit) {
            fullCommand = scanner.nextLine();
            String[] commandTypeAndArg = fullCommand.split(" ", 2);
            String commandType = commandTypeAndArg[0].trim();
            String commandArg = "";
            if (commandTypeAndArg.length > 1) {
                commandArg = commandTypeAndArg[1].trim();
            }

            Command command;
            try {
                command = getCommand(commandType, commandArg);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.printErrorMessage(e);
                continue;
            }
        }
        scanner.close();
    }

    public static Command getCommand(String commandType, String commandArg) throws InvalidCommandException {
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
            throw new InvalidCommandException(commandType);
        }
        return command;
    }
}