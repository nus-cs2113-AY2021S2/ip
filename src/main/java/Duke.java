import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        Task [] tasks = new Task[100];
        int count = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke ");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while(!isExit){
            Scanner scanner = new Scanner(System.in);
            String echo = scanner.nextLine();
            if(echo.equals("bye")){
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                isExit = true;
            }
            else if(echo.equals("list")){
                System.out.println("    ____________________________________________________________");
                if(count == 0){
                    System.out.println("     List is empty");
                }
                for (int i = 0; i < count; i++) {
                    System.out.print("     "+(i+1));
                    System.out.println(". "+tasks[i]);
                }
                System.out.println("    ____________________________________________________________");
            }
            else if(echo.contains("done")) {
                int value = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                if (value > count) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Task " + value + " does not exist!");
                    System.out.println("    ____________________________________________________________");
                } else {
                    tasks[value - 1].markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done: ");
                    System.out.print("      ");
                    System.out.println(tasks[value - 1]);
                    System.out.println("    ____________________________________________________________");
                }
            }
            else{
                switch(echo.substring(0,4)){
                case "todo":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    Task a = new Todo(echo.substring(5));
                    tasks[count] = a;
                    count++;
                    System.out.println("      "+ a);
                    break;
                case "dead":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    Task b = new Deadline(echo.substring(9,echo.indexOf("/by")),echo.substring(echo.indexOf("/by")+4));
                    tasks[count] = b;
                    count++;
                    System.out.println("      "+ b);
                    break;
                case "even":
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    Task c = new Event(echo.substring(6,echo.indexOf("/at")),echo.substring(echo.indexOf("/at")+4));
                    tasks[count] = c;
                    count++;
                    System.out.println("      "+ c);
                    break;
                default:
                    System.out.println("     !INVALID TASK! Please specify the type of task");
                    break;
                }
                System.out.println("     Now you have "+count+" tasks in the list.");
                System.out.println("    ____________________________________________________________");
            }
        } //while loop
    } //main method
} //class duke
