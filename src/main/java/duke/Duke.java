package duke;

import duke.exceptions.EmptyListException;
import duke.exceptions.TaskDoneException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static void readFile(List<Task> tasks) {
        try {
            FileInputStream file = new FileInputStream("src/main/java/duke/duke.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|", 0);
                String type = parts[0];
                String isDone = parts[1];
                String task = parts[2];
                if (type.equals("T")) {
                    Task t = new Todo(task);
                    tasks.add(t);
                    if (isDone.equals("1")) {
                        t.markAsDone();
                    }
                } else if (type.equals("D")) {
                    String time = parts[3];
                    Deadline t = new Deadline(task, time);
                    tasks.add(t);
                    if (isDone.equals("1")) {
                        t.markAsDone();
                    }
                } else {
                    String time = parts[3];
                    Event t = new Event(task, time);
                    tasks.add(t);
                    if (isDone.equals("1")) {
                        t.markAsDone();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("src/main/java/duke/duke.txt",false);
            for (Task t : tasks) {
                if (t instanceof Todo) {
                    writer.write(t.getType() + "|" + t.getTextIcon().trim() + "|" + t.getTask().trim());
                } else {
                    writer.write(t.getType() + "|" + t.getTextIcon().trim() + "|" + t.getTask().trim() + "|" + t.getTime().trim());
                }
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
    }

    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static String getFirstWord(String text) {
        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }

    private static void showExitMessage() {
        System.out.println("\tBye fellow coder! Hope to see you again soon!");
    }

    private static void listTasks(List<Task> tasks) {
        int i = 0;
        if (tasks.size() == 0) {
            System.out.println("\tWow! You have time on your hands! Go do something you enjoy :)");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            while (i < tasks.size()) {
                int num = i + 1;
                System.out.println("\t" + num + ". " + tasks.get(i).toString());
                i++;
            }
        }
    }

    private static void markAsDone(List<Task> tasks, int index) throws EmptyListException, TaskDoneException {
        if (tasks.size() == 0) {
            throw new EmptyListException();
        } else if (tasks.get(index).isDone()) {
            throw new TaskDoneException();
        } else {
            tasks.get(index).markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + tasks.get(index).toString());
        }
    }

    private static void deleteTask(List<Task> tasks, int index) {
        tasks.remove(index);
        System.out.println("\tDone! One less worry for you :)");
    }

    private static void printAddedTask(List<Task> tasks, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + tasks.get(taskCounter).toString());
        int counter = taskCounter + 1;
        System.out.println("Now you have " + counter + " tasks in the list.\n");
    }

    public static void main(String[] args) {

        welcomeMessage();

        boolean isOn = true;
        List<Task> tasks = new ArrayList<Task>();

        readFile(tasks);
        int taskCounter = tasks.size();

        while(isOn) {
            String line;
            line = getUserInput().trim();
            String[] parts = line.split("(?<=\\D)(?=\\d)");
            int commandIndex = line.indexOf(' ');
            int timeIndex = line.indexOf('/');
            String command = getFirstWord(line);

            if (line.equals("bye")) {
                writeToFile(tasks);
                showExitMessage();
                isOn = false;
            } else if (line.equals("list")) {
                listTasks(tasks);
            } else if (parts[0].equals("done ")) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    markAsDone(tasks, index);
                } catch (EmptyListException e) {
                    System.out.println("\tIm afraid the item does not exist!");
                } catch (TaskDoneException e) {
                    System.out.println("\tTask has already been completed!");
                }
            } else if (parts[0].equals("delete ")) {
                    try {
                        int index = Integer.parseInt(parts[1]) - 1;
                        deleteTask(tasks, index);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("\tYou can't delete something that does not exist!");
                    }
            } else if (command.equals("todo")) {
                try {
                    String task = line.substring(commandIndex + 1);
                    tasks.add(new Todo(task));

                    printAddedTask(tasks, taskCounter);
                    taskCounter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have nothing to do? :)");
                }
            } else if (command.equals("deadline")) {
                try {
                    String task = line.substring(commandIndex + 1, timeIndex - 1);
                    String time = line.substring(timeIndex + 4);
                    tasks.add(new Deadline(task, time));

                    printAddedTask(tasks, taskCounter);
                    taskCounter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have no deadlines to meet? :)");
                }
            } else if (command.equals("event")) {
                try {
                    String task = line.substring(commandIndex + 1, timeIndex - 1);
                    String time = line.substring(timeIndex + 4);
                    tasks.add(new Event(task, time));

                    printAddedTask(tasks, taskCounter);
                    taskCounter++;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\tAre you sure you have nothing going on? :)");
                }
            } else {
                System.out.println("\tI am afraid a computer program is not able to understand what you're saying!");
            }
        }
    }
}
