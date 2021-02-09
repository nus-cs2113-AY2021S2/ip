package duke.view;

import duke.viewmodel.Logic;

public class Duke {

    /**
     * Main function of Duke.
     */
    public static void main(String[] args) throws Exception {
        Logic logic = Logic.getInstance();
        logic.handleMessage();
    }
}