package Duke;

import Duke.Commands.*;
import Duke.FileHandling.FileHandler;
import Duke.Task.*;
import Duke.UI.UI;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static final String LINE = "____________________________________________________________";
    public static String inputString;
    public static int taskCount = 0;
    public static List<Task> lists = new ArrayList<>();

    public static void main(String[] args) {
        new UI();
        //UI.greetings();
        FileHandler.readFile(lists);
        ProcessInput.processInput();
        FileHandler.writeFile(lists);
        UI.goodbye();
    }
}
