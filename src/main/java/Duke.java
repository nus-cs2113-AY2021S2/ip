import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public static String lineDivider = "    --------------------------------------------------------------------------";

    public static void addMessage(Task task,int size){
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.getDescription());
        System.out.println("     Now you have " + size + " tasks in this list.");
    }

    public static void takeCommand(String command,List<Task> tasks) throws UnknownCommand, EmptyDescription {
        String[] subStrings = command.split(" ");
        String description = "";
        int slashIndex = command.indexOf('/');

        switch(subStrings[0]){
        case "list":
            printTasks(tasks);
            break;
        case "bye":
            System.out.println("     Bye. Hope to see you again soon!");
            break;
        case "done":
            if(subStrings.length > 1){
                int taskNo = Integer.parseInt(subStrings[1]);
                Task taskDone = tasks.get(taskNo-1);
                taskDone.markAsDone();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("      [" + taskDone.getStatusIcon() + "] " +
                        taskDone.description);
                tasks.set(taskNo-1, taskDone);
            } else {
                throw new EmptyDescription("done");
            }
            break;
        case "todo":
            if(subStrings.length > 2){
                description = command.substring(5);
                Todo todo = new Todo(description);
                tasks.add(todo);
                addMessage(todo,tasks.size());
            } else {
                throw new EmptyDescription("todo");
            }
            break;
        case "deadline":
            if(slashIndex != -1) {
                description = command.substring(9, slashIndex-1);
                String by = command.substring(slashIndex+4);
                Deadline deadline = new Deadline(description, by);
                tasks.add(deadline);
                addMessage(deadline,tasks.size());
            } else {
                throw new EmptyDescription("deadline");
            }
            break;
        case "event":
            if(slashIndex != -1) {
                description = command.substring(6, slashIndex-1);
                String at = command.substring(slashIndex+4);
                Event event = new Event(description, at);
                tasks.add(event);
                addMessage(event,tasks.size());
            } else {
                throw new EmptyDescription("event");
            }
            break;
        default:
            throw new UnknownCommand();
        }
    }

    public static void printTasks(List<Task> tasks){
        int i = 1;
        System.out.println("     Here are the tasks in your list:");
        for(Task task:tasks){
            System.out.println("     " + i + "." + task.getDescription());
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
            System.out.println(lineDivider);
            try{
                takeCommand(command,tasks);
            } catch (UnknownCommand e) {
                System.out.println("     Oops!!! I'm sorry, but I have no idea what that means =(");
            } catch (EmptyDescription e) {
                System.out.println("     Oops!!! The description of " + e.TaskName() + " cannot be empty.");
            }
            System.out.println(lineDivider);
        }while(!command.equals("bye"));
    }
}
