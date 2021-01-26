import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Task[] list = new Task[100];
        int lengthOfList = 0;

        while (true) {
            Scanner in = new Scanner(System.in);
            // Remove trailing spaces
            String command = in.nextLine().trim();

            switch (command.split(" ")[0].toLowerCase()) {
            case "bye":
                // Print bye statement and exit program
                // Fallthrough
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                in.close();
                return;
            case "list":
                // Print every task in list
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list: ");
                for (int i = 0; i < lengthOfList; i++) {
                    System.out.println(String.format("     %d.[%s] %s", i+1, list[i].getStatusIcon(), 
                        list[i].getDescription()));
                }

                System.out.println("    ____________________________________________________________\n");
                break;
            case "done":
                System.out.println("    ____________________________________________________________");
                int taskNumber = Integer.parseInt(command.substring(command.indexOf(" ")+1)) - 1;
                // Ensure that the task number fit the length of task
                if (taskNumber >= 0 && taskNumber < lengthOfList) {
                    list[taskNumber].setStatus();
                    
                    System.out.println("     Nice! I've marked this task as done: ");
                    System.out.println(String.format("       [%s] %s", list[taskNumber].getStatusIcon(), 
                        list[taskNumber].getDescription()));
                } else {
                    System.out.println("     Task not found!");
                }
                System.out.println("    ____________________________________________________________\n");
                break;
            case "": 
                // If no command detected, do nothing and await another command
                break;
            default:
                // Add new Task to list
                list[lengthOfList] = new Task(command);
                lengthOfList++;

                // Print notification of addition
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + command);
                System.out.println("    ____________________________________________________________\n");
                break;
            }
        }
    }
}
