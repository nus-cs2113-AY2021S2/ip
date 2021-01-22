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

        System.out.println("Hello! I am Kaman :)");
        System.out.println("What can I do for you?");
        doWork();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void doWork() {
        String userInput;
        boolean isLoop = true;
        Scanner scan = new Scanner(System.in);

        while (isLoop) {
            System.out.print("User>");
            userInput = scan.nextLine();
            if (userInput.equals("bye")) {
                isLoop = false;
            } else if (userInput.equals("list")) {
                System.out.println("List:");
                for (int i = 0; i < userInputs.size(); i++) {
                    System.out.println((i + 1) + ". " + userInputs.get(i));
                }
            } else {
                System.out.println("Added: " + userInput);
                userInputs.add(userInput);
            }
        }
    }
}