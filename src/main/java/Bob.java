public class Bob {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
