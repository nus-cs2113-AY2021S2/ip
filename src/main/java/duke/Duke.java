package duke;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Duke {
    private static final String FORCE_QUIT_BYE_COMMAND = "bye";
    private static final String FORCE_QUIT_EXIT_COMMAND = "exit";

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
            DukePrinter.printImportErrorMessage(ioException.getMessage());
            exit(0);
        }
    }

    private void exportToFile() {
        ArrayList<String> tasksAsCSV = dukeTaskList.exportCSV();
        boolean successfullyExported = false, forceQuit = false;
        while (!successfullyExported && !forceQuit) {
            try {
                dukeFileManager.writeToFile(tasksAsCSV);
                successfullyExported = true;
            } catch (IOException ioException) {
                DukePrinter.printExportErrorMessage(ioException.getMessage());
                forceQuit = checkForceQuit();
            }
        }
    }

    private boolean checkForceQuit() {
        boolean isDoneReadingInputs = false, forceQuit = false;
        while (!isDoneReadingInputs) {
            ArrayList<String> commandTokens = DukeParser.readUserInput();
            if (commandTokens.isEmpty()) {
                DukePrinter.printFallbackMessage();
            }
            switch (commandTokens.get(0)) {
            case FORCE_QUIT_BYE_COMMAND:
                isDoneReadingInputs = true;
                break;
            case FORCE_QUIT_EXIT_COMMAND:
                forceQuit = true;
                isDoneReadingInputs = true;
                break;
            default:
                DukePrinter.printForceQuitErrorMessage();
                break;
            }
        }
        return forceQuit;
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
