package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int taskCount = 0;

    public static void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("---------------------------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------");
    }

    public static void bidGoodbye() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------");
    }

    /**
     * Lists the tasks in order
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++){
            System.out.print(i + ".");
            System.out.println(tasks.get(i-1).toString());
        }
    }

    /**
     * Confirms task has been added
     *
     * @param task Task object that was added
     */
    public static void printAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list");
    }

    public static void addTodo(String line) {
        try {
            int descriptionStart = 5;
            String description = line.substring(descriptionStart);
            tasks.add(new Todo(description));
            taskCount++;
            printAddTaskMessage(tasks.get(taskCount - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void addDeadline(String line) {
        try {
            int descriptionStart = 9;
            int descriptionEnd = line.indexOf("/by") - 1;
            String description = line.substring(descriptionStart, descriptionEnd);
            int byStart = line.indexOf("/by") + 4;
            String by = line.substring(byStart);
            tasks.add(new Deadline(description, by));
            taskCount++;
            printAddTaskMessage(tasks.get(taskCount - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static void addEvent(String line) {
        try {
            int descriptionStart = 6;
            int descriptionEnd = line.indexOf("/at") - 1;
            String description = line.substring(descriptionStart, descriptionEnd);
            int atStart = line.indexOf("/at") + 4;
            String at = line.substring(atStart);
            tasks.add(new Event(description, at));
            taskCount++;
            printAddTaskMessage(tasks.get(taskCount - 1));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The description of an event cannot be empty.");
        }
    }

    public static void markDone(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(5));
            tasks.get(itemNum - 1).setAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(itemNum - 1).toString());
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done is out of range");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! The index of the task to be marked as done is invalid");
        }
    }

    public static void deleteTask(String line) {
        try {
            int itemNum = Integer.parseInt(line.substring(7));
            Task toBeDeleted = tasks.get(itemNum - 1);
            tasks.remove(itemNum - 1);
            taskCount--;
            System.out.println("Noted. I've removed this task:");
            System.out.println(toBeDeleted.toString());
            System.out.println("Now you have " + taskCount + " tasks in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be deleted cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The index of the task to be deleted is out of range");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! The index of the task to be deleted is invalid");
        }

    }


    public static void request() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("---------------------------------------------------------");
            if (line.equals("list")) {
                printList();
            } else if (line.startsWith("todo")) {
                addTodo(line);
            } else if (line.startsWith("deadline")) {
                addDeadline(line);
            } else if (line.startsWith("event")) {
                addEvent(line);
            } else if (line.startsWith("done")) {
                markDone(line);
            } else if (line.startsWith("delete")) {
                deleteTask(line);
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("---------------------------------------------------------");
            line = in.nextLine();
        }
        bidGoodbye();
    }

    public static void main(String[] args) {
        greet();
        request();
    }
}
