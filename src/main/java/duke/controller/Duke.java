package duke.controller;

import duke.exception.InvalidInputException;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Ui.showWelcomeScreen();
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
                Ui.showAllTasks();
                break;
            case "bye":
                Ui.showFarewellScreen();
                break;
            default:
                try {
                    Parser.processTask(input);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    Ui.requestInput();
                }

            }
        } while (!input.equals("bye"));
    }
}
