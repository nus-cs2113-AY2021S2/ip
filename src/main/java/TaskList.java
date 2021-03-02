import Exceptions.MissingDeadlineException;
import Exceptions.MissingEventTimeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    public TaskList() {}

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(String taskDescription) throws
            MissingDeadlineException, MissingEventTimeException {
        Task t;
        if (taskDescription.contains("todo")) {
            if (taskDescription.length() < 5) {
                throw new IndexOutOfBoundsException();
            }
            t = new Todo(taskDescription.substring(5));
            System.out.println("\tGot it. I've added this task: ");
        } else if (taskDescription.contains("deadline")) {
            // Adds task as a Deadline
            taskDescription.replace("deadline ", "");
            int byIndex = taskDescription.indexOf("/by ");
            if (byIndex == -1 || taskDescription.length() < byIndex + 4) {
                throw new MissingDeadlineException();
            } else {
                t = new Deadline(taskDescription.substring(9, byIndex - 1), taskDescription.substring(byIndex + 4));
            }
        } else {
            // Adds task as an Event.
            taskDescription.replace("event ", "");
            int atIndex = taskDescription.indexOf("/at ");
            if (atIndex == -1 || taskDescription.length() < atIndex + 4) {
                throw new MissingEventTimeException();
            } else {
                t = new Event(taskDescription.substring(6, atIndex - 1), taskDescription.substring(atIndex + 4));
            }
        }
        tasks.add(t);
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    public static void markTaskAsDone(String taskDescription){
        // Marks one of the tasks as done.
        String[] input = taskDescription.split(" ");        // input = ["Done", taskIndex]
        int taskIndex = Integer.parseInt(input[1]);
        try {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println("\t Nice! I've marked this task as done: ");
            String output = String.format("%02d. [%s] %s", taskIndex, tasks.get(taskIndex - 1).getStatusIcon(), tasks.get(taskIndex - 1).getDescription());
            System.out.println("\t" + output);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Invalid task number!");
        } catch (NumberFormatException e) {
            System.out.println("\t Please enter the task number instead!");
        }
    }

    public static void listTasks(){
        // Lists out the things under tasks.
        System.out.println("\t Here are the tasks in your list:");
        int i = 0;
        for(Task t : tasks){
            String output = String.format("%02d. %s", i+1, t);
            System.out.println("\t" + output);
            i++;
        }
    }

    public static void deleteTask(String taskDescription){
        // Deletes one of the tasks
        String[] input = taskDescription.split(" ");        // input = ["Done", taskIndex]
        int taskIndex = Integer.parseInt(input[1]);
        try {
            String taskInformation = tasks.get(taskIndex-1).toString();
            tasks.remove(taskIndex - 1);
            System.out.println("\t Noted. I have removed this task. ");
            System.out.println("\t" + taskInformation);
            System.out.println("\t You now have " + tasks.size() + "tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\t Invalid task number!");
        } catch (NumberFormatException e) {
            System.out.println("\t Please enter the task number instead!");
        }
    }

    static void setupTasks() throws FileNotFoundException,
            MissingDeadlineException, MissingEventTimeException {
        // Reads in current tasks when main() starts running.
        File f = new File("duke.txt");
        if (!f.exists()) {
            try{
                Storage.writeToFile("duke.txt", "");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } else {
            // Reads in inputs from the file and update tasks
            Scanner s = new Scanner(f);
            while (s.hasNext()){
                String line = s.nextLine();
                String[] contents = line.split(" ~ ");
                String taskDescription = contents[2];

                switch (contents[0]){
                case "T":{
                    Todo t = new Todo(taskDescription);
                    if (contents[1].contains("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                    break;
                }
                case "D":{
                    Deadline d = new Deadline(taskDescription, contents[contents.length -1]);
                    if (contents[1].contains("1")) {
                        d.markAsDone();
                    }
                    tasks.add(d);
                    break;
                }
                case "E":{
                    Event e = new Event(taskDescription, contents[contents.length -1]);
                    if (contents[1].contains("1")) {
                        e.markAsDone();
                    }
                    tasks.add(e);
                    break;
                }
                }
            }
        }
    }
}
