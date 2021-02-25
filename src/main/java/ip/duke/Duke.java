package ip.duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        start();
        runUntilExit();
        exit();
    }


    private void start() {
        ui.printGreetings();
    }

    private void runUntilExit() throws IOException {
        Scanner in = new Scanner(System.in);
        String commandText;

        do {
            commandText = in.nextLine();
            try {
                Parser.parseCommand(commandText);
            } catch (DukeException e) {
                ui.printInvalidInputWarnings(commandText);
            }
        } while (!commandText.equals("bye"));

        storage.saveData();
    }


    private void exit() {
        ui.printBye();
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}
