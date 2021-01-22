import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int numTasks = 0;
        while (true) {
            String command = sc.nextLine();
            System.out.println("\t____________________________________________________________");
            if (command.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < numTasks; i++) {
                    System.out.println("\t " + (i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[numTasks] = command;
                numTasks = numTasks + 1;
                System.out.println("\t added: " + command);
            }
            System.out.println("\t____________________________________________________________");
        }
    }
}
