package duke;

import java.util.Arrays;
import java.util.Scanner;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;

public class Duke {
    public static final String SAVE_PATH = "duke.save";

    protected TaskList tasks;
    protected Ui ui;
    protected ActionHandler actionHandler;

    public Duke(String filepath) {
        ui = new Ui();
        Storage storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.printLoadSaveException(filepath, e);
            tasks = new TaskList(storage);
        }
        actionHandler = new ActionHandler(ui, tasks);
    }

    public void run() {
        actionHandler.greetingHandler();
        ui.printLine();

        Scanner in = new Scanner(System.in);
        Boolean isExit = false;

        while (!isExit) {
            String line = in.nextLine();
            // Split the line by any whitespaces characters (including spaces, tabs etc.)
            String[] arguments = line.split("\\s+");
            // If first argument (command) is empty, there are empty spaces typed in at the front - so we remove it
            if (arguments[0].isEmpty()) {
                arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            }

            ui.printLine();

            try {
                switch(arguments[0]) {
                case "bye":
                    actionHandler.byeHandler();
                    isExit = true;
                    break;
                case "list":
                    actionHandler.listHandler();
                    break;
                case "done":
                    actionHandler.doneHandler(arguments);
                    break;
                case "delete":
                    actionHandler.deleteHandler(arguments);
                    break;
                case "deadline":
                    actionHandler.deadlineHandler(arguments);
                    break;
                case "event":
                    actionHandler.eventHandler(arguments);
                    break;
                case "todo":
                    actionHandler.todoHandler(arguments);
                    break;
                default:
                    throw new InvalidInputException(InputExceptionType.UNKNOWN_COMMAND);
                }
            } catch (Exception e) {
                ui.printException(e);
            }

            ui.printLine();
        }
        in.close();
    }

    public static void main(String[] args) {
        new Duke(SAVE_PATH).run();
    }
}
