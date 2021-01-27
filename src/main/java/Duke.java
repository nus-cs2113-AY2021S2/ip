import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;
        while (true) {
            String command = sc.nextLine();
            System.out.println("\t____________________________________________________________");
            if (command.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println("\t " + (i + 1) + ". " + tasks[i]);
                }
            } else if (command.length() >= 4 && command.substring(0, 4).equals("done")) {
                try {
                    int taskNumber = Integer.parseInt(command.substring(5)) - 1;
                    if (0 <= taskNumber && taskNumber < numberOfTasks) {
                        tasks[taskNumber].markAsDone();
                        System.out.println("\t Nice! I've marked this task as done: ");
                        System.out.println("\t   " + tasks[taskNumber]);
                    } else {
                        System.out.println("\t That's an invalid task number!");
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("\t That's an invalid task number!");
                }
            } else {
                tasks[numberOfTasks] = new Task(command);
                numberOfTasks = numberOfTasks + 1;
                System.out.println("\t added: " + command);
            }
            System.out.println("\t____________________________________________________________");
        }
    }
}
