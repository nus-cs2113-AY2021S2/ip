import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String command;
        String [] taskList = new String [100];
        int numberOfTasks = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("    Hi! I'm Duke (:");
        System.out.println("    What can I do for you today?");
        System.out.println("____________________________________________________________");
        command = in.nextLine();
        do {
            if (command.equals("list")){
                for (int i=1; i <= numberOfTasks; ++i)
                    System.out.println(i + ". " + taskList[i-1]);
            }
            else {
                taskList[numberOfTasks++] = command;
                System.out.println("____________________________________________________________");
                System.out.println("    added: " + command);
                System.out.println("____________________________________________________________");
            }
            command = in.nextLine();
        } while (!command.equals("bye"));
        System.out.println("____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
