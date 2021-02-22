import CommandParser.Parser;
import FileStorage.Storage;
import UserInterface.UI;
import list.*;

import java.util.ArrayList;

public class Duke {
    public static ArrayList<TaskList> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Storage.loadFromFile(tasks);
        UI.greet();
        Parser.processCommands(tasks);
    }
}
