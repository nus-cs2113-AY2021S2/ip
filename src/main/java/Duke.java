import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        System.out.println();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner in = new Scanner(System.in);
        String command;
        ArrayList<String> taskList = new ArrayList<String>();
        int index;

        command = in.nextLine();
        while(!command.equals("bye")) {
            if(command.equals("list")){
                index = 1;
                System.out.println("____________________________________________________________");
                for(String task: taskList){
                    System.out.println(index + ". " + task);
                    index += 1;
                }
                System.out.println("____________________________________________________________");
            }
            else {
                System.out.println("____________________________________________________________");
                taskList.add(command);
                System.out.println("added: " + command);
                System.out.println("____________________________________________________________");
            }
            command = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
