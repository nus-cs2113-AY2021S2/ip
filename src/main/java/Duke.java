import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<String> tasksList = new ArrayList<> ();
    static int tasksCount = 0 ;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");

        boolean toContinueAddingTask = true;
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();

        while (toContinueAddingTask) {
            if (task.equalsIgnoreCase("bye")) {
                toContinueAddingTask = false;
                break;
            }
            else if (task.equalsIgnoreCase("list")) {
                System.out.println("list");
                for (int i=0; i<tasksList.size(); i++){
                    System.out.println("    " + (i+1) + ". " + tasksList.get(i));
                }
                System.out.println("------------------------------------------");
                task = sc.nextLine();
            }
            else {
                tasksCount++;
                tasksList.add(task);
                System.out.println("    added: " + task);
                task = sc.nextLine();
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
