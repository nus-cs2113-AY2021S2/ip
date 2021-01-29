import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        String [] store = new String[100];
        int count = 0;

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
            else if(echo.equals("list")){
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < count; i++) {
                    System.out.print("    "+(i+1));
                    System.out.println(". "+store[i]);
                }
                System.out.println("    ____________________________________________________________");
            }
            else{
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: "+ echo);
                System.out.println("    ____________________________________________________________");
                store[count] = echo;
                count++;
            }
        }
    }


}
