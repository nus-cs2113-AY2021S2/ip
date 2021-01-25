import java.util.Scanner;

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
        command = in.nextLine();
        while(!command.equals("bye")){
            switch(command){
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("list");
                    System.out.println("____________________________________________________________");
                    break;
                case "blah":
                    System.out.println("____________________________________________________________");
                    System.out.println("blah");
                    System.out.println("____________________________________________________________");
                    break;
                case "bye":
                    break;
                default:
                    break;
            }
            command = in.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
