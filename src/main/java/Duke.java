import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello from Duke!");
        System.out.println("What can I do for you?");

//        Set<String> list = new HashSet<String>();
        Task[] Tasks = new Task[100];
        String taskInput;
        Scanner sc = new Scanner(System.in);
        int taskNumber = 0;
        while (!(taskInput = sc.nextLine()).equals("bye")){
//            taskInput = sc.nextLine();
            if (taskInput.toLowerCase().equals("list")){
                System.out.println("This is your current list:");
                for (int i = 0; i < taskNumber; i++){
                    int displayedTask = i + 1;
                    System.out.println(displayedTask + ": " + Tasks[i].getStatusIcon() + Tasks[i].getTaskDescription());
                }
            }
            else {
                int dividerPosition = taskInput.indexOf(" ");
                String taskCommand = taskInput.toLowerCase().substring(0, dividerPosition);
                String taskDescription = taskInput.toLowerCase().substring(dividerPosition);
                switch (taskCommand) {
                    //            case "list":
                    //                System.out.println("This is your current list:");
                    //                for (int i = 0; i < taskNumber; i++){
                    //                    int displayedTask = i + 1;
                    //                    System.out.println(displayedTask + ": " + Tasks[i].getStatusIcon() + Tasks[i].getTaskDescription());
                    //                }
                    //                break;

                    case "todo":
                        Tasks[taskNumber] = new Task(taskDescription);
                        System.out.println("added: " + taskDescription);
                        taskNumber++;
                        break;

                    case "deadline":
                        break;

                    case "done":
                        int taskNumberCompleted = Integer.parseInt(taskDescription.trim()) - 1;
                        Tasks[taskNumberCompleted].markAsDone();
                        System.out.println("Nice! I've marked this task as done!");
                        System.out.println(Tasks[taskNumberCompleted].getStatusIcon() + Tasks[taskNumberCompleted].getTaskDescription());
                        break;

                    //            case "bye":
                    //                break;

                    default:
                        break;
                }
            }
            System.out.println("What can I do for you?");
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}