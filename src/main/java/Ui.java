import java.util.ArrayList;
import java.util.Scanner;

/**
 *  This class deals with interactions with users.
 *  Get user commands and respond to the commands.
 */
public class Ui {
    private Parser parser;

    /**
     * Constructor for Ui object.
     */
    public Ui() {
        parser = new Parser();
    }

    /**
     * Read the input stream to get user commands.
     * Perform the actions corresponds to a specific command.
     * Update the list of tasks if necessary.
     * @param tasks The existing list of tasks
     */
    public static void getCommand(TaskList tasks) {
        Scanner sc = new Scanner(System.in);

        while(true){
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("____________________________________________________________\n"+
                                    "Bye. Hope to see you again soon!\n"+
                                    "____________________________________________________________");
                break;
            }else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                listTasks();
                System.out.println("____________________________________________________________");
            }else if (command.contains("done")) {
                Parser.doneTasks(command, tasks);
                System.out.println("____________________________________________________________");
            } else if (command.contains("todo")) {
                System.out.println("____________________________________________________________");
                Parser.todoTask(command, tasks);
                System.out.println("____________________________________________________________");
            }else if (command.contains("deadline")) {
                System.out.println("____________________________________________________________");
                Parser.deadlineTasks(command, tasks);
                System.out.println("____________________________________________________________");
            }else if (command.contains("event")) {
                System.out.println("____________________________________________________________");
                Parser.eventTask(command, tasks);
                System.out.println("____________________________________________________________");
            }else if (command.contains("delete")) {
                System.out.println("____________________________________________________________");
                Parser.deleteTask(command, tasks);
                System.out.println("____________________________________________________________");

            } else {
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }
    }

    /**
     * Print the entire list of tasks. In the following format:
     * [Task type][completion icon]Description
     * If the task is done, it will be marked as [✓]
     * If the task is still pending, it will be marked as [✘]
     */
    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        TaskList.printTaskList();
    }
}
