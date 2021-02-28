/**
 * Main class of the Bob task assistant
 */

public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a Bob class and assign a file path to its storage
     * @param filePath File path which the files will be stored at
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(ui);
    }

    public static void main(String[] args) {
        new Bob("data/tasks.txt").run();
    }

    private void run() {
        tasks.load(storage.load());
        ui.printWelcome();
        Parser.scanInput(tasks, storage, ui);
        ui.printGoodbye();
    }

}
