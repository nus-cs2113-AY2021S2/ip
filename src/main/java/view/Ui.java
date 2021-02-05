package view;
import java.util.Scanner;

public class Ui {
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER = "===================================================";
    private final String ERROR_DIVIDER_LINE = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";

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

    public String[] getMenuInputChoice() {
        String choice;
        String[] switchChoice;
        Scanner sc = new Scanner(System.in);
        while (true) {
                //String Manupilation: remove leading and trailing cases and change all words to lower case
                choice = sc.nextLine().trim().toLowerCase();
                switchChoice = choice.toLowerCase().split(" ", 2);
                if (!switchChoice[0].matches("todo|deadline|event|list|done|menu|bye")){
                    System.out.println("Please use the appropriate keyword(s)!Input again!");
                    continue;
                }
                break;
            }
        return switchChoice;
    }

    private void printErrorDivider() {
        System.out.println(ERROR_DIVIDER_LINE);
    }

    public void printErrorMessage(String... messages) {
        printErrorDivider();
        print("Error Message: ");
        for (String message: messages) {
            print(message);
        }
        printErrorDivider();
    }

    public void print(String message) {
        System.out.println(message);
    }
}
