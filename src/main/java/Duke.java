import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    protected static Storage storage;
    private final Ui ui;

    public Duke() {
        /*
        Sets up Duke according storage in hard disk.
        */
        ui = new Ui();
        Storage.createFolder();
        storage = new Storage();
        if (storage.retrieveTextFile()) {
            storage.loadData();
        }

    }

    public void run() {
        ui.printGreeting();
        Parser.determineCommand();
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
