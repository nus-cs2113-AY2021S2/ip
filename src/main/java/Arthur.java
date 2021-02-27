import command.parser.Parser;
import file.storage.Storage;
import ui.UI;
import task.list.Task;

import java.util.ArrayList;

public class Arthur {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Storage.loadFromFile(tasks);
        UI.greet();
        Parser.processCommands(tasks);
    }
}