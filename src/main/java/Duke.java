import java.io.File;
import java.io.IOException;

import commandParser.CommandParser;
import command.Command;
import command.CommandResult;
import fileHandler.Storage;
import task.TaskList;
import ui.Ui;


public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Set up Duke, create Ui, storage object. Load task list from the file
     */
    private void start() {
        ui = new Ui();
        String sourceFilePath = "data" + File.separator + "task.txt";
        try{
            storage = new Storage(sourceFilePath);
            tasks = storage.getTaskList();
        } catch (IOException e){
            ui.showFileErrorMessage();
            System.exit(0);
        }
        ui.showWelcomeMessage();
    }

    /**
     * Keep asking user input, execute command and display result
     */
    private void runCommandLoopUntilExitCommand() {
        CommandParser parser = new CommandParser();
        Command command;
        String userCommandText = ui.getUserInput();
        try{
            while (!userCommandText.equals("bye")) {
                command = parser.parseCommand(userCommandText);
                command.setData(tasks);
                CommandResult result = command.execute();
                ui.printFeedback(result);
                storage.store(tasks);
                userCommandText = ui.getUserInput();
            }
        } catch (IOException e){
            ui.showStoreIssueMessage();
            System.exit(0);
        }
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    public void run() throws IOException {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }


    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}

