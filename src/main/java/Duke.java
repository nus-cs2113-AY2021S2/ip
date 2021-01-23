import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static String[] tasks = new String[100];
    public static int taskCount = 0;

    public static void printStartUpMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void run(Scanner scanner){
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                return;
            } else if (input.equals("list")) {
                printTaskList();
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
    }

    public static void printTaskList() {
        String[] taskList = Arrays.copyOf(tasks, taskCount);
        System.out.println("____________________________________________________________");
        for(int i = 0; i < taskList.length; i++){
            System.out.printf("%d. %s%n", i+1, taskList[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printStartUpMessage();
        run(scanner);
        printExitMessage();
    }
}
