import java.util.Scanner;
import duke.controller.TaskController;



public class Duke {

    public static void main(String[] args) {

        TaskController.readFile();
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
                TaskController.saveFile();
                break;
            }
            else if (task.equalsIgnoreCase("list")) {
                TaskController.printTaskList();
                System.out.println("------------------------------------------");
            }
            else if (task.contains("done")){
                TaskController.markTaskDone(task);
            }
            else if (task.contains("delete")){
                TaskController.deleteTask(task);
            }
            else {
                TaskController.addTask(task);
            }
            task = sc.nextLine().trim();
        }
        System.out.println("------------------------------------------");
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
