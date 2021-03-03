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

    public static void printMenu() {
        print("Hello! Im Duke\n" + logo + "What can I do for you?");
        print("> Use the keyword todo");
        print("> Use the keyword deadlines followed \"/by YYYY-MM-DD HH:MM\"");
        print("> Use the keyword event followed by \"/at\"");
        print("> Use the keyword list to print");
        print("> Use keyword \"done <Number>\"  to mark task as done!");
        print("> Use keyword \"delete <Number>\"  to delete task!");
        print("> Use keyword \"find <keyword>\"  to find task(s)!");
        print("> Use the keyword \"bye\" to exit");
        print("> Use the keyword \"menu\" to bring up the menu");
        print("Input here: ");
    }

    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim().toLowerCase();
        return userInput;
    }

    public void printErrorMessage(String... messages) {
        print(ERROR_DIVIDER_LINE);
        print("Error Message: ");
        for (String message: messages) {
            print(message);
        }
        print(ERROR_DIVIDER_LINE);
        print("Enter next command: ");
    }

    public void printMessage(String ...messages){
        for(String message : messages){
            System.out.println(message);
        }
    }

    public static void print(String message){
        System.out.println(message);
    }

    public Boolean sayGoodBye(String userInput){
        if (userInput.contains("BYE")){
            printMessage("Bye Bye! See you Again");
            return true;
        }else{
            return false;
        }
    }
}
