package duke;

public class Duke {

    private TaskManager tasks;
    private Storage storage;
    private Ui ui;
    private Parser p;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = new TaskManager(storage.load());
        p = new Parser();
    }

    public void run() {
        ui.showHello();
        while (true) {
            String userCommand = ui.getUserInput();
            p.parse(userCommand.trim());
            try {
                p.getCmd().execute(tasks, ui, storage);
            } catch (DukeException e) {
                e.showMessage(ui);
            } catch (NullPointerException e) {
                ui.showMessageForInvalidCommandInput();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
