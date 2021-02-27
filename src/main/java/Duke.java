public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static final String FILE_PATH = "data/saveTaskList.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            if (isExit) {
                ui.showGoodbye();
            }
            ui.showLine();
        }
        storage.store(tasks);
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}

