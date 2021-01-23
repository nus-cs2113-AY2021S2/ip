import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static Task[] tasks = new Task[100];
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
            } else if (input.contains("done")) {
                markTaskDone(input);
            } else {
                addNewTask(input);
            }
        }
    }

    public static void markTaskDone(String input) {
        String[] parts = input.split(" ");
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        tasks[taskIndex].markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex]);
        System.out.println("____________________________________________________________");
    }

    public static void addNewTask(String input){
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + input);
        System.out.println("____________________________________________________________");
    }

    public static void printTaskList() {
        Task[] taskList = Arrays.copyOf(tasks, taskCount);
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
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
