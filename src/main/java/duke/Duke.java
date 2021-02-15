package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printGreeting(logo);

        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();
            String taskType = "";

            try {
                if (line.equals("list")) {
                    printList();
                    continue;
                } else if (line.equals("bye")) {
                    printBye();
                    break;
                } else if (line.contains("done")) {
                    taskType = "done";
                    markDone(line);
                    continue;
                } else if (line.contains("todo")) {
                    taskType = "todo";
                    addTodo(line);
                    continue;
                } else if (line.contains("deadline")) {
                    taskType = "deadline";
                    addDeadline(line);
                    continue;
                } else if (line.contains("event")) {
                    taskType = "event";
                    addEvent(line);
                    continue;
                } else if (line.contains("delete")) {
                    taskType = "delete";
                    deleteTask(line);
                    continue;
                } else {
                    throw new DukeException();
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a " + taskType + " cannot be empty.");

            } catch (DukeException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    private static void deleteTask(String line) {
        String[] words = line.split(" ", 2);
        int deleteIndex = Integer.parseInt(words[1]);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(deleteIndex - 1).toString());
        tasks.remove(deleteIndex - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String line) {
        String[] words = line.split(" ", 2);
        String detailWords = words[1];
        String[] info = detailWords.split(" /at ", 2);
        String taskDescription = info[0];
        String atTime = info[1];
        tasks.add(tasks.size(), new Event(taskDescription, atTime));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadline(String line) {
        String[] words = line.split(" ", 2);
        String detailWords = words[1];
        String[] info = detailWords.split(" /by ", 2);
        String taskDescription = info[0];
        String byDate = info[1];
        tasks.add(tasks.size(), new Deadline(taskDescription, byDate));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addTodo(String line) {
        String[] words = line.split(" ", 2);
        String taskDescription = words[1];
        tasks.add(tasks.size(), new Todo(taskDescription));
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void markDone(String line) {
        String[] words = line.split(" ", 2);
        int doneIndex = Integer.parseInt(words[1]);
        Task doneTask = tasks.get(doneIndex - 1);
        doneTask.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + doneTask.getStatusIcon() + "] " + doneTask.description);
    }

    private static void printList() {
        System.out.println("Here are the tasks in your list: ");
        int index = 0;
        while (index < tasks.size()) {
            System.out.println((index + 1) + "." + tasks.get(index).toString());
            index++;
        }
    }

    public static void printBye() {
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }

    private static void printGreeting(String logo) {
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }
}
