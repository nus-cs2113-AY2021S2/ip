package duke;

import duke.command.Command;
import duke.task.*;
import duke.task.Event;

import java.io.IOException;
import java.util.Scanner;

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

    public void run() throws IOException {
        ui.showHello();
        while (true) {
            String userCommand = ui.getUserInput();
            p.parse(userCommand.trim());
            try {
                p.getCmd().execute(tasks, ui, storage);
            } catch (EmptyDescriptionException e) {
                e.showMessage();
            } catch (NullPointerException e) {
                ui.showMessageForInvalidCommandInput();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }

}
