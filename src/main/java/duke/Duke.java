package duke;

import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        DukeUI.printWelcomeMessage();
        DukeUI.printMenu();
        DukeController.run();
        DukeUI.printExitMessage();
    }
}