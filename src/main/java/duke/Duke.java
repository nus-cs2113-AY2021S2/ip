package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import java.io.File;
import java.io.IOException;

public class Duke {
    public static final String FILEPATH = "Duke.txt";
    public static Storage storage;
    public static Ui ui;
    public static Parser parser;
    public static TaskList list;

    /**
     * Constructor of Duke class
     * Imports data from Duke.txt and add to List
     * If no Duke.txt, empty list is loaded
     */
    public Duke() {
        storage = new Storage(FILEPATH);
        list = new TaskList();
        ui = new Ui();
        parser = new Parser();
        try {
            Storage.loadFile(list.list);
        } catch (IOException e) {
            Ui.checkError("IO Error");
        }
    }

    /**
     * Runs the program.
     * Gets user input and parses command until "bye"
     */
    public void run() {
        String command;
        try {
            Ui.printGreeting();
            File file = new File("Duke.txt");
            if (file.exists()) {
                System.out.println("Duke.txt successfully synced!");
                Ui.printBorder();
            }
            do {
                command = ui.getCommand();
                Parser.parseCommand(command,list.list);
            } while (!command.equalsIgnoreCase("bye"));
            Ui.printExit();
            Storage.saveFile(list.getList());
        } catch (IOException e) {
            Ui.checkError("FILE_NOT_FOUND");
        }
    }

    /** Starts the program */
    public static void main(String[] args) {
        new Duke().run();
    }
}
