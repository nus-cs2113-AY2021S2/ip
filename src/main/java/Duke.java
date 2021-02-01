import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String dash = "-";
        Scanner myObj = new Scanner(System.in);

        // print welcome message
        System.out.println("Hello from\n" + logo);
        System.out.println(dash.repeat(50));
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(dash.repeat(50));

        // scan input
        String command = myObj.nextLine();

        String[] tasks = new String[100];
        int count = 1;
        while (!command.equals("bye") && !command.equals("list")) {
            // add task into array
            tasks[count] = command;
            count++;
            // echo task
            System.out.println(dash.repeat(50));
            System.out.println("added: " + command);
            System.out.println(dash.repeat(50));
            command = myObj.nextLine();
        }
        // print list when command=list
        if (command.equals("list")) {
            for(int i=1; i<count; ++i){
                System.out.println(dash.repeat(50));
                System.out.println(i + "." + " " + tasks[i]);
                System.out.println(dash.repeat(50));
            }
            command = myObj.nextLine();
        }
        // exit program when input=bye
        if(command.equals("bye")) {
            System.out.println(dash.repeat(50));
            System.out.println(dash.repeat(49));
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(dash.repeat(50));
        }
    }

}
