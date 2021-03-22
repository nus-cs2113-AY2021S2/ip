package Duke;

import java.util.ArrayList;
import java.util.List;
import Duke.Task.*;
import Duke.UI.Ui;
import Duke.Storage.Storage;
import Duke.Parser.Parser;
import Duke.Command.Command;

public class Duke {
    public static int count;

    public static List<Task> lists = new ArrayList<Task>();

    public static void main(String[] args) {
        Ui.showWelcomeMessage();
        Storage.readFile(lists);
        while (true) {
            String userCommand = Parser.getUserInput();
            Ui.echoUserCommand(userCommand);
            int returnValue = Command.executeCommand(userCommand);
            if (returnValue == 0)
                break;
        }
    }

}