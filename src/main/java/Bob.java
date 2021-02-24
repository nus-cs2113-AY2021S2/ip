public class Bob {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load(), ui);
    }

    public static void main(String[] args) {
        new Bob("data/tasks.txt").run();
    }

    private void run() {
        ui.welcomeMessage();
        Parser.scanInput(ui, tasks, storage);
        ui.goodbyeMessage();
    }

}
