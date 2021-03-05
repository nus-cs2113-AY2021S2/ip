package duke;

import duke.tasks.*;
import duke.ui.TextUI;
import duke.parser.Parser;
import duke.commands.Command;
import duke.storage.Storage;

/** 
 * Duke is a CLI-based task manager to help you keep track of Todos, Events and Deadlines. 
 * 
 * @author Huachen
 * @version 0.2
 */
public class Duke {

    private TextUI ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the instance of Duke. This creates new instances of ui, tasklist, storage and parser. 
     */
    public void run() {
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
