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
    private static final ArrayList<String> records = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isLoop = true;

        System.out.println("Hello! I am Kaman :)");
        System.out.println("What can I do for you?");
        do {
            System.out.print("User>");
            String rawInput = scan.nextLine();
            String[] inputs = rawInput.split(" ");
            switch (inputs[0]) {
            case "list":
                showList();
                break;
            case "done":
                setDone(inputs);
                break;
            case "bye":
                quitProgram();
                isLoop = false;
                break;
            default:
                addRecord(inputs[0]);
                break;
            }
        } while (isLoop);
    }

    private static void setDone(String[] inputs) {
        if (inputs.length != 2) {
            System.out.println("Invalid input! (Too many arguments)");
            return;
        }
        int targetRecordIndex = Integer.parseInt(inputs[1]) - 1;
        if (targetRecordIndex < 0) {
            System.out.println("Invalid input! (Index should be at least 1)");
            return;
        }
        String targetRecord = records.remove(targetRecordIndex);
        String updatedRecord = "[X] " + targetRecord.substring(4);
        records.add(targetRecordIndex, updatedRecord);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + updatedRecord);

    }

    private static void addRecord(String userInput) {
        System.out.println("Added: " + userInput);
        records.add("[ ] " + userInput);
    }

    private static void showList() {
        System.out.println("Here is your task List:");
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i));
        }
    }

    private static void quitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}