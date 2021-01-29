import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke ");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while(!isExit){
            Scanner scanner = new Scanner(System.in);
            String echo = scanner.nextLine();
            if(echo.equals("bye")){
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                isExit = true;
            }
            else{
                System.out.println("    ____________________________________________________________");
                System.out.println("    "+ echo);
                System.out.println("    ____________________________________________________________");
            }
        }
    }


}
