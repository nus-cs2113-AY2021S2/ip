package dukchess;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import dukchess.entity.TaskList;
import dukchess.parser.Parser;
import dukchess.storage.Storage;
import dukchess.ui.Ui;

/**
 * Main application driver class.
 */
public class Duke {

    private void initializeDukchessData() {
        try {
            TaskList.setTaskList(Storage.loadData());
        } catch (IOException e) {
            Ui.printErrorMessage(e);
        }
    }

    private void runInteractionLoop() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().toLowerCase(Locale.ROOT).trim();
        boolean toExit = Parser.execute(input);
        while (!toExit) {
            input = in.nextLine().toLowerCase(Locale.ROOT).trim();
            toExit = Parser.execute(input);
        }
        in.close();
    }

    /**
     * Runs the main Dukchess program.
     */
    public void run() {
        initializeDukchessData();
        Ui.printGreeting();
        runInteractionLoop();
        Ui.sayGoodbye();
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }
}
