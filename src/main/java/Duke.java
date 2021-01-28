import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> tasksList = new ArrayList<> ();
    static int tasksCount = 0 ;

    public static void printTaskList(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list: ");
        for (int i=0; i<tasksList.size(); i++){
            System.out.println("    " + (i+1) + ". [" + taskList.get(i).getStatusIcon() + "] " + tasksList.get(i).getDescription());
        }
    }

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
                printTaskList(tasksList);
                System.out.println("------------------------------------------");
            }
            else if (task.contains("done")){
                int idx = Integer.parseInt(String.valueOf(task.charAt(5)));
                tasksList.get(idx-1).markAsDone();
                System.out.println("------------------------------------------");
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("    [" + tasksList.get(idx-1).getStatusIcon() + "] " + tasksList.get(idx-1).getDescription());
            }
            else {
                Task t = new Task(task);
                tasksCount++;
                tasksList.add(t);
                System.out.println("    added: " + task);
            }
            task = sc.nextLine().trim();
        }
        System.out.println("------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
