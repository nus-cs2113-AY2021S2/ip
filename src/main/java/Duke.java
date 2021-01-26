import java.util.Scanner;

public class Duke {
    private static final String hLine = "\u2500".repeat(60);
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String[] userInputs = new String[100];
    private static int userInputCounter = 0;

    public static void main(String[] args) {
        Duke.greet();
        Duke.readUserInput();
        Duke.bye();
    }

    public static void greet() {
        System.out.println(Duke.hLine);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(Duke.hLine);
    }

    public static void bye() {
        System.out.println(Duke.hLine);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(Duke.hLine);
    }

    // Reads user input, does an action and stops reading when user inputs "bye"
    public static void readUserInput() {
        Scanner in = new Scanner(System.in);
        String userInput;
        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            switch (userInput) {
            case "list":
                Duke.listUserInputs();
                break;
            default:
                Duke.addUserInput(userInput);
                Duke.echo(userInput);
                break;
            }
            userInput = in.nextLine();
        }
    }

    public static void echo(String text) {
        System.out.println(Duke.hLine);
        System.out.println("added: " + text);
        System.out.println(Duke.hLine);
    }

    public static void addUserInput(String text) {
        userInputs[userInputCounter++] = text;
    }

    public static void listUserInputs() {
        int itemNumber;
        System.out.println(Duke.hLine);
        for (int i = 0; i < userInputCounter; i++) {
            itemNumber = i+1;
            System.out.println(itemNumber + ". " + userInputs[i]);
        }
        System.out.println(Duke.hLine);
    }
}
