import java.io.IOException;

public class Parser {

    public static void deadlineTasks(String command, TaskList tasks) {
        try {
            String description = command.substring(9, command.indexOf("/by"));
            String by = command.substring(command.indexOf("/by") + 4);
            Task task = new Deadline(description, by);
            tasks.addTask(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
            Storage.writeToFile("data/duke.txt", tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a deadline must include /by and description.");
        }catch (IOException e) {
            System.out.println("Something went wrong when writing: " + e.getMessage());
        }
    }

    public static void eventTask(String command, TaskList tasks) {
        try {
            String description = command.substring(6, command.indexOf("/at"));
            String at = command.substring(command.indexOf("/at") + 4);
            Task task = new Event(description, at);
            tasks.addTask(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
            Storage.writeToFile("data/duke.txt", tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a event must include /at and description.");
        }catch (IOException e) {
            System.out.println("Something went wrong when writing: " + e.getMessage());
        }
    }

    public static void todoTask(String command, TaskList tasks) {
        try {
            Task task = new Todo(command.substring(5));
            tasks.addTask(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have "+ Task.getTaskCount() +" task(s) in the list.");
            Storage.writeToFile("data/duke.txt", tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }catch (IOException e) {
            System.out.println("Something went wrong when writing: " + e.getMessage());
        }
    }

    public static void doneTasks(String command, TaskList tasks) {
        try {
            int doneNumber=Integer.parseInt(command.substring(5));
            System.out.println("Nice! I've marked this task as done: ");
            tasks.getTaskByIndex(doneNumber-1).markAsDone();
            Storage.writeToFile("data/duke.txt", tasks);
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! A number is expected after command done");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please select from the task list.");
        }catch (IOException e) {
            System.out.println("Something went wrong when writing: " + e.getMessage());
        }
    }

    public static void deleteTask(String command, TaskList tasks) {
        try {
            int deleteNumber=Integer.parseInt(command.substring(7));
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.getTaskByIndex(deleteNumber-1));
            tasks.removeTask(deleteNumber-1);
            System.out.println("Now you have "+ Task.updateTaskCount() +" task(s) in the list.");
            Storage.writeToFile("data/duke.txt", tasks);
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! A number is expected after command done");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please select from the task list.");
        }catch (IOException e) {
            System.out.println("Something went wrong when writing: " + e.getMessage());
        }
    }
}
