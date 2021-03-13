import java.io.IOException;

/**
 * This class deals with making sense of the user command.
 * The commands includes: deadline, todo, event, done, delete, find, list.
 */
public class Parser {

    /**
     * Add a deadline task to the task list, if the user entered a deadline command.
     * The deadline task must follow the format: deadline <description> /by time.
     * Print our the number of tasks on the list after adding the new task.
     * Save the new task to the data file.
     * If the command does not follow the format, print an error message specifying the desired format.
     * If something went wrong when save the task to the data file, print an error message.
     * @param command The command users entered
     * @param tasks  The existing list of tasks
     * @throws StringIndexOutOfBoundsException If the command does not follow the correct format
     * @throws IOException If something went wrong when save the task to the data file
     */
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

    /**
     * Add a event task to the task list, if the user entered a event command.
     * The deadline task must follow the format: event <description> /at time.
     * Print our the number of tasks on the list after adding the new task.
     * Save the new task to the data file.
     * If the command does not follow the format, print an error message specifying the desired format.
     * If something went wrong when save the task to the data file, print an error message.
     * @param command The command users entered
     * @param tasks The existing list of tasks
     * @throws StringIndexOutOfBoundsException If the command does not follow the correct format
     * @throws IOException If something went wrong when save the task to the data file
     */
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

    /**
     * Add a todo task to the task list, if the user entered a todo command.
     * The todo task must follow the format: todo <description>.
     * Print our the number of tasks on the list after adding the new task.
     * Save the new task to the data file.
     * If the command does not follow the format, print an error message specifying the desired format.
     * If something went wrong when save the task to the data file, print an error message.
     * @param command The command users entered
     * @param tasks The existing list of tasks
     * @throws StringIndexOutOfBoundsException If the command does not follow the correct format
     * @throws IOException If something went wrong when save the task to the data file
     */
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
    /**
     * Mark a specific task as done.
     * The command must follow the format: done <index>.
     * Save the new task status to the data file.
     * If the command does not follow the format, print an error message specifying the desired format.
     * If something went wrong when save the task to the data file, print an error message.
     * @param command The command users entered
     * @param tasks The existing list of tasks
     * @throws NumberFormatException If the command does not follow the correct format
     * @throws IOException If something went wrong when save the task to the data file
     * @throws ArrayIndexOutOfBoundsException If the index is not on the existing task list.
     */
    public static void doneTasks(String command, TaskList tasks) {
        try {
            int doneNumber=Integer.parseInt(command.substring(5));
            System.out.println("Nice! I've marked this task as done: ");
            tasks.getTaskByIndex(doneNumber-1).markAsDone();
            Storage.writeToFile("data/duke.txt", tasks);
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! A number is expected after command done");
        }catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please select from the task list.");
        }catch (IOException e) {
            System.out.println("Something went wrong when writing: " + e.getMessage());
        }
    }

    /**
     * delete a specific task.
     * The command must follow the format: delete <index>.
     * delete the task from data file.
     * If the command does not follow the format, print an error message specifying the desired format.
     * If something went wrong when save the task to the data file, print an error message.
     * @param command The command users entered
     * @param tasks The existing list of tasks
     * @throws NumberFormatException If the command does not follow the correct format
     * @throws IOException If something went wrong when save the task to the data file
     * @throws ArrayIndexOutOfBoundsException If the index is not on the existing task list.
     */
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
        }catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! Please select from the task list.");
        }catch (IOException e) {
            System.out.println("Something went wrong when writing: " + e.getMessage());
        }
    }

    public static void findTask(String command, TaskList tasks) {
        try {
            String keyword = command.substring(6);
            int count = 0;
            System.out.println("Here are the matching tasks in your list:");
            for(int i=0; i<Task.getTaskCount(); i++) {
                Task t = tasks.getTaskByIndex(i);
                if(t.getDescription().contains(keyword)) {
                    count++;
                    System.out.print(count+". ");
                    System.out.println(t);
                }
            }
            if(count == 0) {
                System.out.println("OOPS!!! There are no matching tasks.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The keyword cannot be empty.");
        }
    }

}
