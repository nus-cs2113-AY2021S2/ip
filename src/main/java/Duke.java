import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        Task [] tasks = new Task[100];
        int count = 0;
        showWelcomeMessage();

        while(!isExit){
            String echo = getUserInput();
            if(echo.equals("bye")){
                showByeMessage();
                isExit = true;
            }
            else if(echo.equals("list")){
                printBorder();
                if(count == 0){
                    System.out.println("     List is empty");
                }
                for (int i = 0; i < count; i++) {
                    System.out.print("     "+(i+1));
                    System.out.println(". " + tasks[i]);
                }
                printBorder();
            }
            else if(echo.contains("done")) {
                int value = Integer.parseInt(echo.replaceAll("[^0-9]", ""));
                if (value > count) {
                    printBorder();
                    System.out.println("     Task " + value + " does not exist!");
                    printBorder();
                } else {
                    tasks[value - 1].markAsDone();
                    printBorder();
                    System.out.println("     Nice! I've marked this task as done: ");
                    System.out.print("      ");
                    System.out.println(tasks[value - 1]);
                    printBorder();
                }
            }
            else{
                if(echo.contains("todo")) {
                    printBorder();
                    System.out.println("     Got it. I've added this task: ");
                    Task a = new Todo(echo.substring(5));
                    tasks[count] = a;
                    count++;
                    System.out.println("      " + a);
                }
                else if(echo.contains("deadline")) {
                    printBorder();
                    System.out.println("     Got it. I've added this task: ");
                    Task b = new Deadline(echo.substring(9, echo.indexOf("/by")), echo.substring(echo.indexOf("/by") + 4));
                    tasks[count] = b;
                    count++;
                    System.out.println("      " + b);
                }
                else if(echo.contains("event")) {
                    printBorder();
                    System.out.println("     Got it. I've added this task: ");
                    Task c = new Event(echo.substring(6, echo.indexOf("/at")), echo.substring(echo.indexOf("/at") + 4));
                    tasks[count] = c;
                    count++;
                    System.out.println("      " + c);
                }
                else{
                    System.out.println("     !INVALID TASK! Please specify the type of task");

                }
                System.out.println("     Now you have "+count+" tasks in the list.");
                printBorder();
            }
        } //while loop
    } //main method

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void showWelcomeMessage() {
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
    }

    private static void showByeMessage() {
        printBorder();
        System.out.println("     Bye. Hope to see you again soon!");
        printBorder();
    }

    private static void printBorder(){
        System.out.println("    ____________________________________________________________");
    }
}
