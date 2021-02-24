package duke.controller;

import duke.exception.InvalidInputException;
import duke.ui.UI;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        UI.showWelcomeScreen();
        Scanner sc = new Scanner(System.in);
        String input;

        runProgramLoop(sc);


    }

    private static void runProgramLoop(Scanner sc) {
        String input;
        do {
            input = sc.nextLine();
            switch (input) {
            case "list":
                UI.showAllTasks();
                break;
            case "bye":
                UI.showFarewellScreen();
                break;
            default:
                try {
                    TaskManager.processTask(input);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    UI.requestInput();
                }

            }
        } while (!input.equals("bye"));
    }
}
