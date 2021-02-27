package duke;

import duke.tasks.*;
import duke.ui.TextUI;
import duke.parser.Parser;
import duke.commands.Command;
import duke.storage.Storage;

public class Duke {

    private TextUI ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    public static void main(String[] args) {
        new Duke().run(args);
    }

    public void run(String[] launchArgs) {
        this.ui = new TextUI();
        ui.showWelcomeMessage();
        this.taskList = new TaskList(ui);
        this.storage = new Storage(taskList, ui);
        this.parser = new Parser(ui, taskList, storage);
        
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = parser.parse(command);
                c.execute(taskList, ui);
                isExit = c.isExit;
            } catch (Exception e) {
                ;
            } finally {
                ;
            }
        }
    }
}
