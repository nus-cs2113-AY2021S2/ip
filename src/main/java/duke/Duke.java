package duke;

import java.util.Scanner;
import java.util.Random;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void printSeparator() {
        for(int i = 0; i < 60; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void exitMethod() {
        System.out.print("My cover's blown!\n");
        printSeparator();
    }

    /**
     * Echoes the user input with random upper and lower case for mockery.
     *
     * @param line User input.
     */
    public static void mockEcho(String line) {
        Random rd = new Random();
        for(int i = 0; i < line.length(); i++) {
            if(rd.nextBoolean()) {
                System.out.print(Character.toUpperCase(line.charAt(i)));
            } else {
                System.out.print(Character.toLowerCase(line.charAt(i)));
            }
        }
        System.out.print('\n');
    }

    public static void printTasks() throws DukeException{
        if (taskCount < 1) {
            throw new DukeException();
        } else {
            System.out.print("There are " + taskCount + " tasks in your list:\n");
            for(int i = 0; i < taskCount; i++) {
                System.out.print((i+1) + "." + tasks[i] + '\n');
            }
        }
    }

    public static void addTask(Task t) throws DukeException{
        if (t.getDescription().isEmpty()) {
            throw new DukeException();
        } else {
            tasks[taskCount++] = t;
            System.out.print("Got it. I've added this task:\n" + t.toString() + '\n');
        }
    }

    public static void markIndexDone(int taskIndex) throws DukeException{
        try {
            tasks[taskIndex].setDone();
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static void printHelp() {
        System.out.print("I can remember your tasks for you!\n\n" +
                "Available commands:\n" +
                "\ttodo <description>\n" +
                "\tdeadline <description> /by <time due>\n" +
                "\tevent <description> /at <time occuring>\n" +
                "\tlist\n" + "\tdone <taskIndex>\n");
    }

    public static void listMode() {
        String line;
        String[] lineParts;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")){
            lineParts = line.split(" ");
            switch(lineParts[0]) {
            case "-h":
                printHelp();
                break;
            case "list":
                try {
                    printTasks();
                } catch (DukeException e) {
                    System.out.print("You don't have any tasks currently!\n");
                }
                break;
            case "done":
                try {
                    int taskIndex = Integer.parseInt(lineParts[1]) - 1;
                    markIndexDone(taskIndex);
                } catch (NumberFormatException e) {
                    System.out.print(lineParts[1] + " is not a valid number.\n");
                } catch (DukeException e) {
                    System.out.print("That is not a valid task index, please try again.\n");
                }
                break;
            case "todo":
                try {
                    addTask(new Todo(line.replace("todo", "").trim()));
                } catch (DukeException e) {
                    System.out.print("The description of a todo cannot be empty.\n");
                }
                break;
            case "deadline":
                try {
                    int byIndex = line.indexOf("/by");
                    addTask(new Deadline(line.substring(9, byIndex), line.substring(byIndex + 4)));
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("Something went wrong. Please put the due date after /by.\n");
                } catch (DukeException e) {
                    System.out.print("The description of a deadline cannot be empty.\n");
                }
                break;
            case "event":
                try {
                    int atIndex = line.indexOf("/at");
                    addTask(new Event(line.substring(6, atIndex), line.substring(atIndex + 4)));
                } catch (IndexOutOfBoundsException e) {
                    System.out.print("Something went wrong. Please put the event time after /at.\n");
                } catch (DukeException e) {
                    System.out.print("The description of a event cannot be empty.\n");
                }
                break;
            default:
                mockEcho(line);
                System.out.print("Use -h for list or available commands.\n");
            }
            printSeparator();
            line = in.nextLine();
        }
        exitMethod();
    }

    public static void main(String[] args) {
        printSeparator();
        System.out.print("Greetings, fellow humans!\nI am CI.\nHow may I help you?\n");
        printSeparator();
        listMode();
    }
}
