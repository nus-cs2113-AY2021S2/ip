package duke;

public class Duke {
    public static void main(String[] args) {
        DukeUI.printWelcomeMessage();
        DukeUI.printMenu();
        DukeController.run();
        DukeUI.printExitMessage();
    }
}