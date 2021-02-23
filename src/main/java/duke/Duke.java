package duke;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {

    private DukeTaskList dukeTaskList;
    private DukeFileManager dukeFileManager;

    public Duke(String directory, String filename) {
        dukeTaskList = new DukeTaskList();
        dukeFileManager = new DukeFileManager(directory, filename);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/", "duke.csv");

        duke.importFromFile();
        DukePrinter.printWelcomeMessage();

        duke.interactWithUser();

        duke.exportToFile();
        DukePrinter.printExitMessage();
    }

    private void importFromFile() {
        try {
            dukeFileManager.openFile();
            ArrayList<String> taskInfo = dukeFileManager.readFromFile();
            ArrayList<ArrayList<String>> parsedTaskInfo = DukeParser.parseTaskInfo(taskInfo);
            dukeTaskList.importTaskInfo(parsedTaskInfo);
        } catch (IOException ioException) {
            System.out.println("Could not open file, here are the details of the error:");
            System.out.println(ioException.getMessage());
            System.exit(0);
        }
    }

    private void exportToFile() {
        ArrayList<String> tasksAsCSV = dukeTaskList.exportCSV();
        try {
            dukeFileManager.writeToFile(tasksAsCSV);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void interactWithUser() {
        boolean isDoneReadingInputs = false;
        while (!isDoneReadingInputs) {
            ArrayList<String> commandTokens = DukeParser.readUserInput();
            try {
                isDoneReadingInputs = DukeParser.executeCommand(dukeTaskList, commandTokens);
            } catch (DukeException dukeException) {
                String errorMessage = dukeException.getMessage();
                DukePrinter.printDukeErrorMessage(errorMessage);
            } catch (NumberFormatException numberFormatException) {
                DukePrinter.printInvalidArgumentsMessage();
            }
        }
    }
}
