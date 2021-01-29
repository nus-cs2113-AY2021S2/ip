import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Task[] tasksList = new Task[100];

    private static int tasksCount = 0;
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        sendWelcomeMessage();
        printLine();
        while(true) {
            String[] userCommand = getUserInput();
            if (userCommand[0].equalsIgnoreCase("bye")){
                exitDuke();
                break;
            }
            if (userCommand[0].equalsIgnoreCase("list")){
                listOutTasks();
            } else if (userCommand[0].equalsIgnoreCase("done" )) {
                markTaskAsDone(userCommand[1]);
            }
            else {
                addTasktoList(userCommand);
            }
            printLine();
        }
    }

    private static void sendWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void addTasktoList(String[] userCommand) {
        printLine();
        tasksList[tasksCount] = new Task(userCommand[0]);
        tasksCount++;
        System.out.println("Added: " + userCommand[0]);
    }

    private static void markTaskAsDone(String s) {
        int taskNumber = Integer.parseInt(s);
        taskNumber--;
        Task selectedTask = tasksList[taskNumber];
        selectedTask.markAsDone();
        System.out.println("Nice! Following task is now marked as done:");
        System.out.println("[X] " + selectedTask.description);
    }

    private static void exitDuke() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printLine() {
        System.out.println("---------------------------------------------------");
    }


    private static String[] getUserInput() {
        String userInput = SCANNER.nextLine();
        String[] listOfInputs = userInput.split(" ", 2);
        if (!(listOfInputs[0].equalsIgnoreCase("done" ))) {
            listOfInputs[0] = userInput;
        }
        return listOfInputs;
    }


    private static void listOutTasks() {
        int i = 0;
        while (i < tasksCount) {
            Task selectedTask = tasksList[i];
            i++;
            System.out.println(i + ". " + selectedTask.getStatusIcon() + " " + selectedTask.description);
        }
    }

}
