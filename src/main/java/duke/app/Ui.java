package duke.app;

import java.util.Scanner;

/*
Class Ui for handling visuals
*/
public class Ui {

    /*
    Display welcome message when application started up
     */
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

    /*
    Display error message when unable to load file
     */
    public void showLoadingError() {
        System.out.println("Unable to load the file.");
    }

    /*
    Display separator line for nice visualization for users
     */
    public void showLine() {
        System.out.println("-------------------------------");
    }

    /*
    Get user input from command line
    Function returns the input string
     */
    public String readCommand() {
        String userInput;
        Scanner inputCommand = new Scanner(System.in);
        userInput = inputCommand.nextLine();
        return userInput;
    }

    /*
    Display the error message passed in as arguement
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
