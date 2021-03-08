package main;

import main.commands.Command;
import main.commands.CommandResult;
import main.commands.ExitCommand;

public class Duke {

    public TaskList taskList;
    public Ui ui;

    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }

    public void run() throws DukeException {
        ui.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    public void runCommandLoopUntilExitCommand() {
        Command command;
        do {

            String fullCommand = ui.getUserCommand();
            ui.showLine(); // show the divider line ("_______")
            command = new Parser().parse(fullCommand);
            CommandResult result = executeCommand(command);
            taskList = result.taskList;
            Ui.showResultToUser(result);

        } while (!ExitCommand.isExit(command));
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(taskList);
            return command.execute();
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    /**
     * Prints the Goodbye message and exits.
     */
    public void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}