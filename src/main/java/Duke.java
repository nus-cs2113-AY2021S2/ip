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
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isLoop = true;
        String userInput;

        System.out.println("Hello! I am Kaman :)");
        System.out.println("What can I do for you?");
        while (isLoop) {
            System.out.print("User>");
            userInput = scan.nextLine();
            if (userInput.equals("bye")) {
                isLoop = false;
            } else {
                System.out.println(userInput);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
