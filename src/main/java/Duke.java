import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        Boolean command_in = true;

        Scanner in = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" );

        while(command_in){
            String input = in.nextLine();
            if(input.equals("bye")){
                input = "Bye. Hope to see you again soon!";
                command_in = false;
            }
            System.out.println("____________________________________________________________\n" +
            input + "\n" +
            "____________________________________________________________\n");


        }




    }
}
