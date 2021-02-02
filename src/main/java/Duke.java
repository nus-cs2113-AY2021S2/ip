import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is to build a personal assistance chat-bot called "Kaman"
 * (Customised from Duke)
 *
 * @author NgManSing
 */
public class Duke {
    private static final ArrayList<Task> records = new ArrayList<>();

    public static void main(String[] args) {
        boolean isLoop = true;
        printWelcomeMsg();

        do {
            String rawInput = getUserInput();
            String[] inputs = rawInput.split(" ");
            String command = inputs[0];
            String[] arguments = Arrays.copyOfRange(inputs, 1, inputs.length);

            switch (command) {
            case "todo":
                addRecord(arguments, Todo.TASK_TYPE);
                break;
            case "deadline":
                addRecord(arguments, Deadline.TASK_TYPE);
                break;
            case "event":
                addRecord(arguments, Event.TASK_TYPE);
                break;
            case "list":
                showList();
                break;
            case "done":
                executeCommandDone(arguments);
                break;
            case "bye":
                isLoop = executeCommandBye(rawInput, arguments);
                break;
            default:
                promptUserInputInvalid();
                break;
            }
        } while (isLoop);
    }

    private static void promptUserInputInvalid() {
        System.out.println("Invalid command! Please try again!");
    }

    private static boolean executeCommandBye(String rawInput, String[] arguments) {
        if (arguments.length != 0) {
            System.out.println("Command \"Bye\" requires no argument. Please try again!");
            return true;
        }

        quitProgram();
        return false;
    }

    private static void executeCommandDone(String[] arguments) {
        int targetRecordIndex = -1;
        boolean isArgumentInteger = true;
        try {
            targetRecordIndex = Integer.parseInt(arguments[0]) - 1;
        } catch (NumberFormatException e) {
            isArgumentInteger = false;
        }

        if (arguments.length == 1 && isArgumentInteger) {
            markAsDone(targetRecordIndex);
        } else {
            System.out.println("Command \"Done\" only requires an integer argument. Please try again!");
        }
    }

    private static String getUserInput() {
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        System.out.println("Command entered: " + userInput);
        return userInput;
    }

    private static void printWelcomeMsg() {
        System.out.println("Hello! I am Kaman :)");
        System.out.println("What can I do for you?");
    }

    private static void markAsDone(int index) {
        if (index < 0 || index >= records.size()) {
            System.out.println("Invalid input! (Index cannot be out of bounds)");
            return;
        }
        records.get(index).setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + records.get(index));
    }

    private static void addRecord(String[] detailFragments, String taskType) {
        String[] details = null;
        String taskName;
        String date;
        switch (taskType) {
        case Todo.TASK_TYPE:
            taskName = String.join(" ", detailFragments);
            records.add(new Todo(taskName));
            break;
        case Deadline.TASK_TYPE:
            details = getTaskNameAndDate(detailFragments, taskType);
            if (details != null) {
                taskName = details[0];
                date = details[1];
                records.add(new Deadline(taskName, date));
            }
            break;
        case Event.TASK_TYPE:
            details = getTaskNameAndDate(detailFragments, taskType);
            if (details != null) {
                taskName = details[0];
                date = details[1];
                records.add(new Event(taskName, date));
            }
            break;
        }
        if (details != null || taskType.equals(Todo.TASK_TYPE)) {
            int numberOfRecords = records.size();
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + records.get(numberOfRecords - 1));
            System.out.printf("Now you have %d tasks in the list.\n", numberOfRecords);
        }
    }

    private static String[] getTaskNameAndDate(String[] detailFragments, String taskType) {
        int slashElementIndex;
        boolean hasSlashElement = false;
        boolean isTypeMatch = false;
        for (slashElementIndex = 0; slashElementIndex < detailFragments.length - 1; slashElementIndex++) {
            if (detailFragments[slashElementIndex].charAt(0) == '/') {
                hasSlashElement = true;
                break;
            }
        }
        if (!hasSlashElement) {
            System.out.println("Invalid input! (No date/time provided)");
            return null;
        }

        switch (taskType) {
        case Deadline.TASK_TYPE:
            if (detailFragments[slashElementIndex].substring(1).equals("by")) {
                isTypeMatch = true;
            }
            break;
        case Event.TASK_TYPE:
            if (detailFragments[slashElementIndex].substring(1).equals("at")) {
                isTypeMatch = true;
            }
            break;
        }
        if (!isTypeMatch) {
            System.out.println("Invalid input! (keywords not matching)");
            return null;
        }

        StringBuilder taskName = new StringBuilder();
        StringBuilder dueDay = new StringBuilder();
        for (int i = 0; i < slashElementIndex; i++) {
            taskName.append(detailFragments[i]);
        }
        for (int i = slashElementIndex + 1; i < detailFragments.length; i++) {
            dueDay.append(detailFragments[i]);
        }
        return new String[]{taskName.toString(), dueDay.toString()};
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