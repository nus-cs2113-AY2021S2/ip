package duke.controller;

import duke.exception.InvalidInputException;
import duke.task.Task;
import duke.ui.*;

import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        UI.showWelcomeScreen();
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            input = sc.nextLine();
            switch (input) {
            case "list":
                System.out.println("This is your current list:");
                for (int i = 0; i < Task.getTaskCounter(); i++){
                    int displayedTask = i + 1;
                    System.out.println(displayedTask + ". " + Task.getTaskList().get(i));
                }
                UI.showDivider();
                break;
            case "bye":
                System.out.println(UI.divider);
                System.out.println("Bye! Hope to hear from you again soon!");
                System.out.println(UI.divider);
                break;
            default:
                try {
                    TaskManager.processTask(input);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                    UI.showDivider();
                }

            }
        } while (!input.equals("bye"));


    }
}
