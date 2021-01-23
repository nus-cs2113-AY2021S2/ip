import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String[] list = new String[100];
        int lengthOfList = 0;

        while (true) {
            Scanner in = new Scanner(System.in);
            String command = in.nextLine();

            //print bye statement and exit program

            switch (command.toLowerCase()) {
            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________\n");
                in.close();
                return;

            case "list":
                System.out.println("____________________________________________________________");
                for (int i = 0; i < lengthOfList; i++) {
                    System.out.println(String.format("%d. %s", i+1, list[i]));
                }
                System.out.println("____________________________________________________________\n");
                break;
            case "": 
                break;
            default:
                //append command to list
                list[lengthOfList] = command;
                lengthOfList++;

                //echos commands
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + command);
                System.out.println("____________________________________________________________\n");
                break;
            }
        }
    }
}
