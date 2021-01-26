import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String command;
        String horizontalLine = "____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String[] taskList = new String[100];
        int numberOfTasks = 0;

        System.out.println("Hello from\n" + logo);
        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);

        while (!(command = userInput.nextLine().trim()).equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println(" " + (i + 1) + ". " + taskList[i]);
                }
                System.out.println(horizontalLine);
            } else {
                System.out.println(" added: " + command + "\n" + horizontalLine);
                taskList[numberOfTasks] = command;
                numberOfTasks++;
            }
        }
        System.out.println(" Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
