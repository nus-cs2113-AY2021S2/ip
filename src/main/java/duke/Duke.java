package duke;

import duke.commands.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.loadFromDisk();
        ui = new Ui();
        parser = new Parser(tasks);
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readCommand();
            Command userCommand = parser.processInput(userInput);
            userCommand.execute();
            isExit = userCommand.isExit();
        }
        storage.saveToDisk(tasks);
    }
}
