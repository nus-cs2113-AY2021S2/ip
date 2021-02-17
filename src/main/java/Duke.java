import java.lang.reflect.Array;
import java.util.*;
public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

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
                doneTasks(command);
                System.out.println("____________________________________________________________");
            } else if (command.contains("todo")) {
                System.out.println("____________________________________________________________");
                todoTask(command);
                System.out.println("____________________________________________________________");
            }else if (command.contains("deadline")) {
                System.out.println("____________________________________________________________");
                deadlineTasks(command);
                System.out.println("____________________________________________________________");
            }else if (command.contains("event")) {
                System.out.println("____________________________________________________________");
                eventTask(command);
                System.out.println("____________________________________________________________");
            }else if (command.contains("delete")) {
                System.out.println("____________________________________________________________");
                deleteTask(command);
                System.out.println("____________________________________________________________");

            } else {
                System.out.println("____________________________________________________________");
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }


    }

    private static void deadlineTasks(String command) {
        try {
            String description = command.substring(9, command.indexOf("/by"));
            String by = command.substring(command.indexOf("/by") + 4);
            Task task = new Deadline(description, by);
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a deadline must include /by and description.");
        }
    }

    private static void eventTask(String command) {
        try {
            String description = command.substring(6, command.indexOf("/at"));
            String at = command.substring(command.indexOf("/at") + 4);
            Task task = new Event(description, at);
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a event must include /at and description.");
        }
    }

    private static void todoTask(String command) {
        try {
            Task task = new Todo(command.substring(5));
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void doneTasks(String command) {
        try {
            int doneNumber=Integer.parseInt(command.substring(5));
            System.out.println("Nice! I've marked this task as done: ");
            tasks.get(doneNumber-1).markAsDone();
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! A number is expected after command done");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please select from the task list.");
        }
    }

    private static void deleteTask(String command) {
        try {
            int deleteNumber=Integer.parseInt(command.substring(7));
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.get(deleteNumber-1));
            tasks.remove(deleteNumber-1);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! A number is expected after command done");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please select from the task list.");
        }
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(Task t: tasks){
            System.out.print(tasks.indexOf(t)+1+". ");
            System.out.println(t);
        }
    }
}
