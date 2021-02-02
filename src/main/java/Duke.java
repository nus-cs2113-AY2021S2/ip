import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static void takeCommand(String command,List<Task> tasks){
        String[] subStrs = command.split(" ");

        switch(subStrs[0]){
        case "list":
            printTasks(tasks);
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case "done":
            int taskNo = Integer.parseInt(subStrs[1]);
            Task temp = tasks.get(taskNo-1);
            temp.markAsDone();
            tasks.set(taskNo-1, temp);
            break;
        default:
            Task task = new Task(command);
            tasks.add(task);
            System.out.println("added: " + command);
            break;
        }
    }

    public static void printTasks(List<Task> tasks){
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for(Task task:tasks){
            System.out.println(i + ".[" + task.getStatusIcon() +
                    "] " + task.getDescription());
            ++i;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter(" ");
        String command = "";
        List<Task> tasks = new ArrayList<Task>();

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        do{
            command = in.nextLine();
            takeCommand(command,tasks);
        }while(!command.equals("bye"));
    }
}
