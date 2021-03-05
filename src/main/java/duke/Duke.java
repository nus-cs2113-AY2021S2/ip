package duke;

import java.io.File;
import java.io.IOException;


public class Duke {
    public static boolean isRunning;
    private final Storage storage;
    private TaskList tasks;
    private Ui ui;

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

    public void run() throws MissingTaskException {
        Ui.printStart();
        isRunning = true;

        while(isRunning) {
            Parser.selectCommand(tasks);
        }

    }

    public static void main(String[] args) throws MissingTaskException {
        String filePath = new File("").getAbsolutePath();
        new Duke(filePath).run();
    }
}
