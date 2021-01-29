import java.util.Scanner;

public class Duke {
    private static Scanner SCANNER = new Scanner(System.in);
    private static Task[] tasksList = new Task[100];
    private static int tasksCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
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
                int taskNumber = Integer.parseInt(userCommand[1]);
                taskNumber--;
                Task selectedTask = tasksList[taskNumber];
                selectedTask.markAsDone();
                System.out.println("Nice! Following task is now marked as done:");
                System.out.println("[X] " + selectedTask.description);
            }
            else {
            printLine();
            tasksList[tasksCount] = new Task(userCommand[0]);
            tasksCount++;
            System.out.println("Added: " + userCommand[0]);
            }
            printLine();
        }
    }

    private static void exitDuke() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printLine() {
        System.out.println("---------------------------------------------------");
    }

    private static void addUserCommand(String userCommand) {
        System.out.println(userCommand);
    }

    private static String[] getUserInput() {
        String userInput = SCANNER.nextLine();
        String[] listOfInputs = ((String) userInput).split(" ", 2);
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
