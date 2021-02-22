import FileStorage.FileStorage;
import list.*;

import java.util.ArrayList;

public class Duke {
    public static ArrayList<TaskList> tasks = new ArrayList<>();

    public static void main(String[] args) {
        FileStorage.loadFromFile(tasks);
        duke.greet();
        Commands.processCommands(tasks);
    }
}
