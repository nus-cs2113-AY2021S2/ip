import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to build a personal assistance chat-bot called "Duke"
 * <p>
 * Week-2:
 * The program is implemented to greet users and exits subsequently.
 *
 * @author NgManSing
 */
public class Duke {
    private static final ArrayList<String> userInputs = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isLoop = true;

        System.out.println("Hello! I am Kaman :)");
        System.out.println("What can I do for you?");
        do {
            System.out.print("User>");
            String userInput = scan.nextLine();
            switch (userInput) {
            case "list":
                showList();
                break;
            case "bye":
                quitProgram();
                isLoop = false;
                break;
            default:
                addRecord(userInput);
                break;
            }
        } while (isLoop);
    }

    private static void addRecord(String userInput) {
        System.out.println("Added: " + userInput);
        userInputs.add(userInput);
    }

    private static void showList() {
        System.out.println("List:");
        for (int i = 0; i < userInputs.size(); i++) {
            System.out.println((i + 1) + ". " + userInputs.get(i));
        }
    }

    private static void quitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}