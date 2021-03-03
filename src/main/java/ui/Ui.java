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
    public static final String FOUND_DIVIDER ="FOUND!!!FOUND!!!FOUND!!!FOUND!!!";

    /**
     * Greets user when the program starts
     */
    public static void printMenu() {
        System.out.println("Hello! Im Duke\n" + logo + "What can I do for you?");
        System.out.println("> Use the keyword todo");
        System.out.println("> Use the keyword deadlines followed \"/by\"");
        System.out.println("> Use the keyword event followed by \"/at\"");
        System.out.println("> Use the keyword list to print");
        System.out.println("> Use keyword \"Done <Number>\"  to mark task as done!");
        System.out.println("> Use the keyword \"bye\" to exit");
        System.out.println("> Use the keyword \"menu\" to bring up the menu");
        System.out.println("Input here: ");
    }

    /**
     * Retrieves the userInput that will determine the command
     * @return userInput
     */
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim();
        return userInput;
    }

    private void printErrorDivider() {
        System.out.println(ERROR_DIVIDER_LINE);
    }

    /**
     * Prints all error messages for errors encountered during the program runtime
     * @param messages Error Messages to be printed
     */
    public void printErrorMessage(String... messages) {
        printErrorDivider();
        print("Error Message: ");
        for (String message: messages) {
            print(message);
        }
        printErrorDivider();
    }

    /**
     * To print any messages required
     * @param messages Message to be printed
     */
    public void printMessage(String ...messages){
        for(String message : messages){
            print(message);
        }
    }

    /**
     * To print single line message
     * @param string string to print
     */
    public void print(String string) {
        System.out.println(string);
    }

    /**
     * Ends the program when the user enters the command "bye"
     * @param userInput userInput
     * @return boolean value true if userInput contains "bye"
     */
    public Boolean sayGoodBye(String userInput){
        if (userInput.contains("BYE")){
            printMessage("Bye Bye! See you Again");
            return true;
        }else{
            return false;
        }
    }
}
