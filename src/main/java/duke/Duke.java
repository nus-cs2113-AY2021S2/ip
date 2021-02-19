package duke;

import duke.viewmodel.Logic;

public class Duke {

    /**
     * Main function of Duke version 0.1. 
     */
    public static void main(String[] args) {
        Logic logic = Logic.getInstance();
        logic.handleMessage();
    }
}