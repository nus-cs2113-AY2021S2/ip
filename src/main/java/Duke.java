import duke.exception.DukeException;

import duke.parser.Parser;
import duke.storage.Storage;

import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;


public class Duke {
    /**
     * Storage created for user.
     */
    private final Storage storage = new Storage();
    /**
     * Task list created for user.
     */
    private TaskList tasks = new TaskList();
    /**
     * UI created to interact with user
     */
    private static final Ui ui = new Ui();

    public Duke() throws Exception {
        /**
         Sets up Duke according storage in hard disk.
         */
        Storage.createFolder();

        if (storage.retrieveTextFile()) {
            tasks = new TaskList(storage.loadData());
        }

    }

    public void run(){
        ui.printGreeting();
        Scanner in = new Scanner(System.in);
        String input;
        boolean isExit = false;

        while(in.hasNext()) {
            input = in.nextLine().toLowerCase().trim();
            try {
                isExit = Parser.determineCommand(input);
            } catch (DukeException ignored) {
            }
            if(isExit){
                break;
            }

        }
        ui.printExitMessage();
        in.close();
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }

}
