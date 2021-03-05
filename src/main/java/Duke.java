public class Duke {
    private static final String SAVE_FILE_PATH = "saveFile.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(ui, filePath);
        tasks = new TaskList(storage.loadStoredTasksData());
        ui.setTaskList(tasks);
        storage.setTaskList(tasks);
        parser = new Parser(ui, storage, tasks);
    }

    private void run() {
        ui.printWelcomeMsg();
        String command;
        boolean isExit;
        do {
            command = ui.getCommandFromUser();
            parser.handleCommand(command);
            isExit = parser.isExit();
        } while (!isExit);

        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Duke(SAVE_FILE_PATH).run();
    }
}
