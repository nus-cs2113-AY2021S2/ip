import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        String greet = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                "____________________________________________________________";
        String exit = "Bye. Hope to see you again!\n" +
                "____________________________________________________________\n";

        System.out.println(greet);

        String line = "";
        Scanner in = new Scanner(System.in);

        System.out.print(" What can I do for you?\n" +
                "____________________________________________________________");
        while(!line.equals("bye")) {
            line = in.nextLine();
            System.out.print("____________________________________________________________\n"+
                    line+"\n"+
                    "____________________________________________________________\n");
        }

        System.out.println(exit);
    }
}
