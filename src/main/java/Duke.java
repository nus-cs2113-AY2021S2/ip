import java.io.File;

/**
 * Starts the Duke application and begins interactions with user.
 */
public class Duke {

    public final int index = 0;
    private final Ui ui;
    private final TaskList tasks = new TaskList();
    private final Storage storage;
    private final Parser parser;


    /**
     * Initialises required objects and loads any existing data from the storage file.
     *
     * @param filePath pre-defined path to the file where data is loaded from and saved to
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(new File(filePath));
        storage.readFile(tasks, index);
        parser = new Parser();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs the application until termination.
     */
    public void run() {
        ui.printGreeting();
        String input = ui.readCommand();
        while (!input.equals("bye")) {
            parser.processUserInput(ui, tasks, input);
            input = ui.readCommand();
        }
        storage.writeToFile(tasks);
        ui.printByeMessage();
    }

}
