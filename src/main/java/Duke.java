import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private static final String BREAKLINE = "------------------------------------------------------------";
    private static List<String> userList = new ArrayList<String>();
    public static void showLogo() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(BREAKLINE + "\n" + logo + "\n" + BREAKLINE);
    }
    public static void greetMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(BREAKLINE);
    }
    public static String getUserInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(BREAKLINE);
        return input;
    }
    public static void showList() {
        int count = 1;
        for (String i: userList) {
            System.out.println(count + ". " + i);
            count++;
        }
        System.out.println(BREAKLINE);
    }
    public static void addToList(String input){
        userList.add(input);
        System.out.println("Added: " + input);
        System.out.println(BREAKLINE);
    }
    public static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(BREAKLINE);
    }

    public static void main(String[] args) {
        showLogo();
        greetMessage();
        while(true) {
            String input = getUserInput();
            switch (input) {
            case "bye":
                byeMessage();
                return;
            case "list":
                showList();
                break;
            default:
                addToList(input);
                break;
            }
        }
    }
}
