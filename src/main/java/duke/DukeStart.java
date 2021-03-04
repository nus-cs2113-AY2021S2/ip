package duke;

import duke.ui.MainUI;

import java.util.Scanner;

/**
 * Represent the start of the Duke Program
 */
public class DukeStart {
    private MainUI ui;
    private static Scanner in = new Scanner(System.in);

    public DukeStart() {
        ui = new MainUI();
        ui.displayUI(in);
    }

    /**
     * Start the program
     * @param args takes in arguments from commandline
     */
    public static void main(String[] args) {
        new DukeStart();
    }
}

