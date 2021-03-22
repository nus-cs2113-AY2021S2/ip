package dukchess;

import java.io.IOException;

import dukchess.controller.Controller;
import dukchess.entity.TaskList;
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

    /**
     * Runs the main Dukchess program.
     */
    public void run() {
        initializeDukchessData();
        Ui.printGreeting();
        Controller.runInteractionLoop();
        Ui.sayGoodbye();
    }

    public static void main(String[] args) throws Exception {
        new Duke().run();
    }
}
