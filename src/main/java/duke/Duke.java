package duke;

import java.util.Scanner;

import static duke.DukeController.tasks;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukeReader.readFromFile();
        UI.printStartUpMessage();
        DukeController.run(scanner);
        DukeWriter.writeToFile(tasks);
        UI.printExitMessage();
    }
}
