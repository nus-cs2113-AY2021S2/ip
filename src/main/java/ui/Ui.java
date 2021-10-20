package ui;

import java.util.Scanner;

public class Ui {
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String DIVIDER = "===================================================";
    private final String ERROR_DIVIDER_LINE = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    public static final String FOUND_DIVIDER = "FOUND!!!FOUND!!!FOUND!!!FOUND!!!";

    /**
     * Greets user when the program starts
     */
    public static void printMenu() {
        print("Hello! I'm Duke\n" + logo + "What can I do for you?");
        print("> Use the keyword \"todo\"");
        print("> Use the keyword \"deadline\" followed by \"/by YYYY-MM-DD HH:MM\"");
        print("> Use the keyword \"event\" followed by \"/at\"");
        print("> Use the keyword \"list\" to print task list");
        print("> Use keyword \"done <Number>\"  to mark task as done!");
        print("> Use keyword \"delete <Number>\"  to delete task!");
        print("> Use keyword \"find <keyword>\"  to find task(s)!");
        print("> Use the keyword \"menu\" to bring up the menu");
        print("> Use the keyword \"bye\" to exit");
        print("Enter your command: ");
    }

    /**
     * Retrieves the userInput that will determine the command
     *
     * @return userInput
     */
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim().toLowerCase();
        return userInput;
    }

    /**
     * Prints all error messages for errors encountered during the program runtime
     *
     * @param messages Error Messages to be printed
     */
    public void printErrorMessage(String... messages) {
        print(ERROR_DIVIDER_LINE);
        print("Error Message: ");
        for (String message : messages) {
            print(message);
        }
        print(ERROR_DIVIDER_LINE);
        print("Enter next command: ");
    }

    /**
     * To print any messages required
     *
     * @param messages Message to be printed
     */
    public void printMessage(String... messages) {
        for (String message : messages) {
            print(message);
        }
    }

    /**
     * To print single line message
     *
     * @param string string to print
     */
    public static void print(String string) {
        System.out.println(string);
    }

    /**
     * Ends the program when the user enters the command "bye"
     *
     * @param userInput userInput
     * @return boolean value true if userInput contains "bye"
     */
    public Boolean sayGoodBye(String userInput) {
        if (userInput.contains("BYE")) {
            printMessage("Bye Bye! See you Again");
            return true;
        } else {
            return false;
        }
    }
}
