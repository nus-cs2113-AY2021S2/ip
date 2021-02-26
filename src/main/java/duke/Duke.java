package duke;

import duke.commands.CommandHandler;
import duke.exception.IllegalTaskCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    /**
     * Starts the Task Manager program.
     * Runs an infinite loop until "BYE" is called.
     */
    private static void runTaskManager() throws IOException {
        String userInput;
        String command;
        Scanner in = new Scanner(System.in);

        do {
            userInput = in.nextLine();
            command = Parser.getCommand(userInput);
            CommandHandler.executeCommand(command, userInput);
        } while (!command.equals("BYE"));
    }

    public static void main(String[] args) throws IllegalTaskCommandException, IOException {
        Storage.loadTasks();
        Ui.initialiseWelcomeMessage();
        runTaskManager();
    }
}
