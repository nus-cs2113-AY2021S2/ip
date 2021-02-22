package Duke;

import Duke.FileHandling.FileHandler;
import Duke.Parser.ProcessInput;
import Duke.Task.*;
import Duke.UI.UI;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static String inputString;
    public static int taskCount = 0;
    public static List<Task> lists = new ArrayList<>();

    public static void main(String[] args) {
        UI.greetings();
        FileHandler.readFile();
        ProcessInput.processInput();
        FileHandler.writeFile();
        UI.goodbye();
    }
}
