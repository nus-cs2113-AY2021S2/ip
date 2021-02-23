package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    public static final String FILEPATH = "Duke.txt";
    public static Storage storage;
    public static Ui ui;
    public static Parser parser;
    public static TaskList list;

    /**
     * Run Duke program
     * Program loads Duke.txt file
     * Program runs until "bye" command
     * Duke.txt is saved again
     */
    public static void main(String[] args) {
        storage = new Storage();
        list = new TaskList();
        ui = new Ui();
        parser = new Parser();
        try {
            Ui.printGreeting();
            Storage.loadFile(FILEPATH,list.list);
            while(true) {
                String command = ui.getCommand();
                if(command.equals("bye")) {
                    break;
                }
                Parser.parseCommand(command,list.list);
            }
            Ui.printExit();
            Storage.saveFile(list.getList());
        } catch (IOException e) {
            Ui.checkError("FILE_NOT_FOUND");
        }
    }

}
