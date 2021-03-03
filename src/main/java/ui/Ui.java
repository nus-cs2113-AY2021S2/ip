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
    public static final String FOUNDLINE ="FOUND!!!FOUND!!!FOUND!!!FOUND!!!";

    public static void printMenu() {
        System.out.println("Hello! Im Duke\n" + logo + "What can I do for you?");
        System.out.println("> Use the keyword todo");
        System.out.println("> Use the keyword deadlines followed \"/by\"");
        System.out.println("> Use the keyword event followed by \"/at\"");
        System.out.println("> Use the keyword list to print");
        System.out.println("> Use keyword \"done <Number>\"  to mark task as done!");
        System.out.println("> Use the keyword \"bye\" to exit");
        System.out.println("> Use the keyword \"menu\" to bring up the menu");
        System.out.println("Input here: ");
    }

    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim().toLowerCase();
        return userInput;
    }


    public void printErrorMessage(String... messages) {
        System.out.println(ERROR_DIVIDER_LINE);
        print("Error Message: ");
        for (String message: messages) {
            print(message);
        }
        System.out.println(ERROR_DIVIDER_LINE);
        System.out.println("Enter next command: ");
    }

    public void printMessage(String ...messages){
        for(String message : messages){
            print(message);
        }
    }

    public void print(String string) {
        System.out.println(string);
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
