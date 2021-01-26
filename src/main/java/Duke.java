import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is to build a personal assistance chat-bot called "Kaman"
 * (Customised from Duke)
 *
 * Week-2:
 * The program is implemented to greet users and exits subsequently.
 *
 * Week-3:
 * Receives user inputs and stores them in "Task" objects
 *
 * Perform functions such as:
 *  - Listing the stored records
 *  - Marking records as done
 *  - Exiting the program
 *
 *  Some codes are added to prompt the user when their input is invalid.
 *
 * @author NgManSing
 */
public class Duke {
    private static final ArrayList<Task> records = new ArrayList<>();

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
                if (inputs.length == 1) {
                    showList();
                } else {
                    addRecord(rawInput);
                }
                break;
            case "done":
                if (inputs.length == 2) {
                    int targetRecordIndex;
                    try {
                        targetRecordIndex = Integer.parseInt(inputs[1]) - 1;
                    } catch (NumberFormatException e) {
                        addRecord(rawInput);
                        break;
                    }
                    markAsDone(targetRecordIndex);
                } else {
                    addRecord(rawInput);
                }
                break;
            case "bye":
                if (inputs.length == 1) {
                    quitProgram();
                    isLoop = false;
                } else {
                    addRecord(rawInput);
                }
                break;
            default:
                addRecord(rawInput);
                break;
            }
        } while (isLoop);
    }

    private static void markAsDone(int index) {
        if (index < 0 || index >= records.size()) {
            System.out.println("Invalid input! (Index cannot be out of bounds)");
            return;
        }
        records.get(index).setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + "[X] " + records.get(index).getTaskName());

    }

    private static void addRecord(String userInput) {
        System.out.println("Added: " + userInput);
        records.add(new Task(userInput));
    }

    private static void showList() {
        System.out.println("Here is your task List:");
        for (int i = 0; i < records.size(); i++) {
            String mark = records.get(i).isDone() ? "[X] " : "[ ] ";
            System.out.println((i + 1) + ". " + mark + records.get(i).getTaskName());
        }
    }

    private static void quitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}