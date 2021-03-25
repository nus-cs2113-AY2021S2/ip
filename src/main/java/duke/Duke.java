package duke;

import java.io.File;
import java.io.IOException;

/**
 * Main program class to start Duke.
 */
public class Duke {
    public static boolean isRunning;
    private final Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creation of text file to store user inputs if file does not exist.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        try {
            storage.readFile(filePath, tasks);
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.printStart();
        isRunning = true;

        while (isRunning) {
            try {
                Parser.selectCommand(tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printErrorMessageOnWhitespaces();
            }
        }

    }

    public static void main(String[] args) {
        String filePath = new File("").getAbsolutePath();
        new Duke(filePath).run();
    }
}
