import command.parser.Parser;
import file.storage.Storage;
import ui.UI;
import list.*;
import task.list.TaskList;

import java.util.ArrayList;

public class Duke {
    public static ArrayList<TaskList> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Storage.loadFromFile(tasks);
        UI.greet();
        Parser.processCommands(tasks);
    }
}
