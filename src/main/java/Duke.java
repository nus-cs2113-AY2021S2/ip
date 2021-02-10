import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                        "Hello! I'm Duke\n"+
                        "What can I do for you?\n"+
                        "____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        int i=0;
        Task[] tasks = new Task[100];
        while(true){
            String command = sc.nextLine();
            if(command.equals("bye")){
                System.out.println("____________________________________________________________\n"+
                                    "Bye. Hope to see you again soon!\n"+
                                    "____________________________________________________________");
                break;
            }else if(command.equals("list")){
                System.out.println("____________________________________________________________");
                listTasks(i, tasks);
                System.out.println("____________________________________________________________");
            }else if(command.contains("done")){
                doneTasks(tasks, command);
                System.out.println("____________________________________________________________");
            } else if(command.contains("todo")){
                System.out.println("____________________________________________________________");
                todoTask(i, tasks, command);
                System.out.println("____________________________________________________________");
                i++;
            }else if(command.contains("deadline")) {
                System.out.println("____________________________________________________________");
                //Deadline t = new Deadline(8,command.indexOf("/by"));
                deadlineTasks(i, tasks, command);
                System.out.println("____________________________________________________________");
                i++;
            }else if(command.contains("event")) {
                System.out.println("____________________________________________________________");
                //Deadline t = new Deadline(8,command.indexOf("/by"));
                eventTask(i, tasks, command);
                System.out.println("____________________________________________________________");
                i++;
            }else{
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }


    }

    private static void deadlineTasks(int i, Task[] tasks, String command) {
        try {
            String description = command.substring(8, command.indexOf("/by"));
            String by = command.substring(command.indexOf("/by") + 4);
            tasks[i] = new Deadline(description, by);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks[i]);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a deadline must include /by and description.");
        }
    }

    private static void eventTask(int i, Task[] tasks, String command) {
        try {
            String description = command.substring(6, command.indexOf("/at"));
            String at = command.substring(command.indexOf("/at") + 4);
            tasks[i] = new Event(description, at);
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks[i]);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a event must include /at and description.");
        }
    }

    private static void todoTask(int i, Task[] tasks, String command) {
        try {
            tasks[i] = new Todo(command.substring(5));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks[i]);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void doneTasks(Task[] tasks, String command) {
        try {
            int doneNumber=Integer.parseInt(command.substring(5));
            System.out.println("Nice! I've marked this task as done: ");
            tasks[doneNumber-1].markAsDone();
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! A number is expected after command done");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please select from the task list.");
        }
    }

    private static void listTasks(int i, Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for(int index = 0; index< i; index++){
            System.out.print(index+1+". ");
            System.out.println(tasks[index]);
        }
    }
}
