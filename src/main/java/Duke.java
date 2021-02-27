import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

/**
 * Main class
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());

    }

    /**
     * executes the program
     */
    public void run() {
        Ui.helloMessage();
        boolean isExit = false;
        while(!isExit){

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
    
    public static void main(String[] args){
        new Duke("data/tasks.txt").run();

    }


}