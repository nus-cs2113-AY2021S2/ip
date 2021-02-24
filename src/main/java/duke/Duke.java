package duke;

    
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.readFile());
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            if (fullCommand.equalsIgnoreCase("bye")) {
                Ui.printExitMessage(isExit);
                Storage.saveFile();
                break;
            }
            else {
                Parser.processUserRequest(fullCommand);
            }
        }
        ui.showLine();
    }

    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir") + "\\Duke.txt").run();
    }
}
