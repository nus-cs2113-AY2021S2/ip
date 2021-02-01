import java.util.Scanner;

public class Duke {
    private static String DIVIDER = "____________________________________________________________";
    private static Scanner in;

    public static void main(String[] args) {
        showWelcomeMessage();

        TaskManager taskManager = new TaskManager();
        in = new Scanner(System.in);

        while(true){
            String input = getUserInput();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                taskManager.listTask();
            } else if (input.length()>4 && input.substring(0,4).equals("done")) {
                int taskNumber = Integer.parseInt(input.substring(5))-1;
                taskManager.doneTask(taskNumber);
            } else {
                taskManager.addTask(input);
            }

            printDivider();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    private static String getUserInput() {
        String line;
        line = in.nextLine();
        printDivider();
        return line;
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDivider();
    }

    private static void printDivider(){
        System.out.println(DIVIDER);
    }
}
