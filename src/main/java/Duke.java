import java.util.Scanner;

public class Duke {

    public static void doTask(String task) {
        System.out.println("-----------------------------------------");
        if (task.equals("list")) {
            System.out.println("list\n");
            System.out.println("-----------------------------------------");
        } else if (task.equals("blah")) {
            System.out.println("blah\n");
            System.out.println("-----------------------------------------");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("-----------------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
        System.out.println("-----------------------------------------");
        String line = " ";

        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            doTask(line);
        }
        
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("-----------------------------------------");
    }
}
