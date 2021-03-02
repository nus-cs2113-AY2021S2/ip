package duke.command;

import java.util.Scanner;


public class DukeStart {
    private MainUI ui;
    private static Scanner in = new Scanner(System.in);

    public DukeStart() {
        ui = new MainUI();
        ui.displayUI(in);
    }

    public static void main(String[] args) {
        new DukeStart();
    }
}

