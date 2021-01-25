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
            //remove trailing spaces
            String command = in.nextLine().trim();

            switch (command.split(" ")[0].toLowerCase()) {
            case "bye":
                //print bye statement and exit program
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                in.close();
                return;

            case "list":
            //print every task in list
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list: ");
                for (int i = 0; i < lengthOfList; i++) {
                    System.out.println(String.format("     %d.[%s] %s", i+1, list[i].getStatusIcon(), list[i].getDescription()));
                }
                System.out.println("    ____________________________________________________________\n");
                break;
            case "done":
                int taskNumber = Integer.parseInt(command.substring(command.indexOf(" ")+1)) - 1;
                if (taskNumber >= 0 && taskNumber < lengthOfList) {
                    list[taskNumber].setStatus();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done: ");
                    System.out.println(String.format("       [%s] %s", list[taskNumber].getStatusIcon(), list[taskNumber].getDescription()));
                    System.out.println("    ____________________________________________________________");
                }
                break;
            case "": 
                break;
            default:
                //add new Task to list
                list[lengthOfList] = new Task(command);
                lengthOfList++;

                //print notification of addition
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + command);
                System.out.println("    ____________________________________________________________\n");
                break;
            }
        }
    }
}
