import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("    Hi! I'm Duke (:");
        System.out.println("    What can I do for you today?");
        System.out.println("____________________________________________________________");
        command = in.nextLine();
        do {
            if (command.equals("list")) {
                Task.listTask();
            }
            else {
                if (command.contains("done")) {
                    Task.doneTask(command);
                }
                else {
                    Task.addTask(command);
                }
            }
            command = in.nextLine();
        } while (!command.equals("bye"));
        System.out.println("____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
