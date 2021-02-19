package duke.Main;

import duke.Tasks.*;
import duke.Controller.*;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private static UI ui = new UI();
    private static Storage storage = new Storage();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Duke() {}

    public void load() {
        tasks = storage.printFileContents();
    }

    public void run() throws IOException {
        ui.displayWelcome();
        load();
        ui.handleTasklist(tasks);
    }

    public static void main(String[] args) throws IOException{
        Duke duke = new Duke();
        duke.run();
    }
}