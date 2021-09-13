/**
 * Runs the Duke program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static final String FILE_PATH = "data/saveTaskList.txt";

    /**
     * Instantiates an instance for running duke
     *
     * @param filePath location of file relative to program
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            //creates new task list if failure to load from folder.
            tasks = new TaskList();
        }
    }

    /**
     * Logic for the main loop that processes information
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                if (isExit) {
                    ui.showGoodbye();
                }
                ui.showLine();
            } catch (NullPointerException e) {
                //Command C can return as null if an error is triggered in parser
                //Null Pointer Exception may hence occur, the catch statement is to ensure it does not exit the loop.
            }
        }
        storage.store(tasks);
    }

    /**
     * Calls for the running of a new Duke instance
     *
     * @param args Runtime arguments are unused
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}

