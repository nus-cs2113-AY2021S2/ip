import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String taskType;
        String taskName;
        String by;
        String at;
        Scanner splitInputScanner;
        String[] userInputSplitted = {""};

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // To do textUITesting, comment line 19
        System.out.println("Hello from\n" + logo);
        printDividingLine();

        // Print welcome message
        printWelcomeMessage();

        // Scan for input
        Scanner userInputScanner = new Scanner(System.in);
        String userInput = userInputScanner.nextLine();
        if(isOneWord(userInput)) {
            taskType = userInput;
            taskName = userInput;
        }
        else{
            splitInputScanner = new Scanner(userInput);
            taskType = splitInputScanner.next();
            taskName = splitInputScanner.nextLine();
            userInputSplitted = userInput.split(" ");
        }

        // Loop for user input until "bye" is inputted
        while(!taskType.equals("bye")) {
            switch (taskType) {
            case "todo":
                Task t = new Todo(taskName);
                printDividingLine();
                t.addTask();
                printDividingLine();
                break;
            case "deadline":
                by = extractTime(taskName);
                taskName  = extractTaskName(taskName);
                Task d = new Deadline(taskName, by);
                printDividingLine();
                d.addTask();
                printDividingLine();
                break;
            case "event":
                at = extractTime(taskName);
                taskName = extractTaskName(taskName);
                Task e = new Event(taskName, at);
                //e.setTime(time);
                printDividingLine();
                e.addTask();
                printDividingLine();
                break;
            case "list":
                printDividingLine();
                Task.listTasks();
                printDividingLine();
                break;
            case "done":
                printDividingLine();
                Task.markAsDone(userInputSplitted[1]);
                printDividingLine();
                break;
            default:
                printCommandErrorMessage();
                break;
            }

            // Scan input again
            userInput = userInputScanner.nextLine();
            if(isOneWord(userInput)) {
                taskType = userInput;
                taskName = userInput;
            }
            else{
                splitInputScanner = new Scanner(userInput);
                taskType = splitInputScanner.next();
                taskName = splitInputScanner.nextLine();
                userInputSplitted = userInput.split(" ");
            }
        }
        printByeMessage();
    }

    private static boolean isOneWord(String userInput) {
        return !userInput.contains(" ");
    }

    private static void printCommandErrorMessage() {
        printDividingLine();
        System.out.println("Uh oh this command is not available :<");
        printDividingLine();
    }

    private static void printWelcomeMessage() {
        printDividingLine();
        System.out.println("Hello! I'm Duke");
        System.out.print("What can I do for you?\n");
        printDividingLine();
    }

    private static void printDividingLine() {
        System.out.println("_____________________________________________________");
    }

    private static void printByeMessage() {
        printDividingLine();
        System.out.println("Bye. Hope to see you again soon! :3");
        printDividingLine();
    }

    private static String extractTaskName(String s){
        String[] splitArray = s.split("/");
        return splitArray[0];
    }

    private static String extractTime(String s){
        String[] splitArray = s.split("/");
        Scanner sc = new Scanner(splitArray[1]);
        sc.next();
        return sc.nextLine();
    }
}
