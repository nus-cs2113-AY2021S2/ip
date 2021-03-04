package duke;

import duke.command.ByeCommand;
import duke.command.Command;

import java.io.IOException;

public class Duke {
    private static final int IO_ERROR = -7;

    private static final String FILEPATH = "tasklogs/tasks.txt";

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Duke constructor creates instances of Storage, Ui, TaskList and Parser.
     * It then loads the saved file into the created TaskList.
     */
    public Duke() {
        storage = new Storage(FILEPATH);
        ui = new Ui();
        taskList = new TaskList(ui);
        parser = new Parser(taskList, ui);
        try {
            storage.loadFile(taskList.getTasks());
        } catch (IOException e) {
            this.ui.printError(IO_ERROR);
        }
    }

    /**
     * Runs the application.
     * 1. Print greeting.
     * 2. Read commands in a loop and break when 'bye' is the command.
     * 3. Prints goodbye message.
     * 4. Save tasks into file.
     */
    public void run() {
        try {
            ui.printGreeting();
            while(true) {
                String command = ui.getCommand();
                Command parsedCommand = parser.parseCommands(command);
                // If user provides input that is all whitespace.
                if (parsedCommand == null) {
                    continue;
                }
                if (parsedCommand instanceof ByeCommand) {
                    break;
                }
                parsedCommand.execute(ui);
            }
            ui.sayGoodbye();
            // Saves tasks into text file after user types 'bye'.
            storage.saveFile(taskList.getTasks());
        } catch (IOException e) {
            this.ui.printError(IO_ERROR);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
