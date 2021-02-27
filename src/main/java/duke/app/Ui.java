package duke.app;

import java.util.Scanner;

public class Ui {

    public void showWelcome(){
        this.showLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        this.showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("Unable to load the file.");
    }

    public void showLine() {
        System.out.println("-------------------------------");
    }

    public String readCommand() {
        String userInput;
        Scanner inputCommand = new Scanner(System.in);
        userInput = inputCommand.nextLine();
        return userInput;
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
