import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        Task[] tasks = new Task[100];
        int count = 0;
        showWelcomeMessage();

        while (!isExit) {
            String userInput = getUserInput();
            printBorder();
            if (userInput.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                isExit = true;
            } else if (userInput.equals("list")) {
                if (count == 0) {
                    System.out.println("     List is empty");
                }
                for (int i = 0; i < count; i++) {
                    System.out.print("     " + (i + 1));
                    System.out.println(". " + tasks[i]);
                }
            } else if (userInput.contains("done")) {
                int value = Integer.parseInt(userInput.replaceAll("[^0-9]", ""));
                if (value > count) {
                    System.out.println("     Task " + value + " does not exist!");
                } else {
                    tasks[value - 1].markAsDone();
                    System.out.println("     Nice! I've marked this task as done: ");
                    System.out.print("      ");
                    System.out.println(tasks[value - 1]);
                }
            } else {
                if (userInput.contains("todo")) {
                    System.out.println("     Got it. I've added this task: ");
                    Task a = new Todo(userInput.substring(5));
                    tasks[count] = a;
                    count++;
                    System.out.println("      " + a);
                } else if (userInput.contains("deadline")) {
                    System.out.println("     Got it. I've added this task: ");
                    Task b = new Deadline(userInput.substring(9, userInput.indexOf("/by")), userInput.substring(userInput.indexOf("/by") + 4));
                    tasks[count] = b;
                    count++;
                    System.out.println("      " + b);
                } else if (userInput.contains("event")) {
                    System.out.println("     Got it. I've added this task: ");
                    Task c = new Event(userInput.substring(6, userInput.indexOf("/at")), userInput.substring(userInput.indexOf("/at") + 4));
                    tasks[count] = c;
                    count++;
                    System.out.println("      " + c);
                } else {
                    System.out.println("     !INVALID TASK! Please specify the type of task");
                }
                System.out.println("     Now you have " + count + " tasks in the list.");
            }
            printBorder();
        } //while loop
    } //main method

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"

                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBorder();
        System.out.println("     Hello! I'm Duke ");
        System.out.println("     What can I do for you?");
        printBorder();
    }

    private static void printBorder() {
        System.out.println("    ____________________________________________________________");
    }
}
