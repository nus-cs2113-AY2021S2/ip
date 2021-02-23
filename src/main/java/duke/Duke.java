package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Starts Duke for user.
 */
public class Duke {

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;
    private final Parser parser;

    public static void main(String[] args) {
        String filePath = new File("").getAbsolutePath();
        new Duke(filePath).run();
    }

    /**
     * Determines if duke.txt should be created.
     *
     * @param filePath File path of duke.txt.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser(taskList);

        try {
            storage.loadFromFile(filePath + "/duke.txt", taskList);
        } catch (FileNotFoundException e) {
            ui.printHorizontalLine();
            ui.printFileCreatedMessage(filePath);
        }
    }

    /**
     * Runs Duke and takes in user input until exit command is called.
     */
    public void run() {
        ui.printStartingMessage();
        boolean isExit = false;

        while (!isExit) {
            isExit = parser.determineCommand();
        }

        storage.saveToFile(taskList);
    }

}
