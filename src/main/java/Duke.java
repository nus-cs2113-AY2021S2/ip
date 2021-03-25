import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

/**
 * A task manager.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor.
     *
     * @param filepath the path of storage
     */
    public Duke(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Get Duke's responses
     */
    public void run() {
        Ui.helloMessage();
        boolean isContinue = true;
        while(isContinue){
            String fullcommand = ui.readCommand();
            if (fullcommand.equalsIgnoreCase("bye")) {
                Ui.byeMessage();
                break;
            } else {
                Parser.parse(fullcommand);
            }
            Ui.showLine();
        }
    }

    /**
     * executes the program Duke
     */
    public static void main(String[] args){
        new Duke("tasks.txt").run();
    }


}
