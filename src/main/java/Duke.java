import Classes.Deadline;
import Classes.Event;
import Classes.Task;
import Classes.Todo;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        try {
            loadFile();
            System.out.println("Save file loaded!");
        } catch (Exception e) {
            System.out.println("No save files found.");
        }

        System.out.println("\t------------------------------------------");
        System.out.println("\tHello there! I'm Duke.");
        System.out.println("\tWhat can I help you with?");
        System.out.println("\t__________________________________________\n");

        String userInput;
        do {
            userInput = sc.nextLine().trim();
            dukeManager(userInput, tasks);
        } while (!userInput.equals("bye"));
    }

    public static void dukeManager(String input, ArrayList<Task> tasks) {
        String [] subString = input.split(" ");
        String command = subString[0];

        switch (command) {
        case "bye":
            exitProgram();
            try {
                saveFile();
            } catch (Exception e) {
                System.out.println("Failed to save file.");
            }
            break;
        case "list":
            listTasks(tasks);
            break;
        case "done":
            markTask(input, tasks);
            break;
        case "delete":
            deleteTask(input, tasks);
            break;
        default:
            addTask(input, tasks);
        }
    }

    public static void parseData(String line) {
        String[] tokens = line.split("-");

        boolean isDone = Integer.parseInt(tokens[1]) == 1;

        switch (tokens[0]) {
        case "T":
            try {
                Todo todo = new Todo(tokens[2], isDone);
                tasks.add(todo);
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case "D":
            try {
                Deadline deadline = new Deadline(tokens[2], isDone, tokens[3]);
                tasks.add(deadline);
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case "E":
            try {
                Event event = new Event(tokens[2], isDone, tokens[3]);
                tasks.add(event);
            } catch (NumberFormatException e) {
                return;
            }
            break;
        default:
            System.out.println("Invalid data!");
            break;
        }
    }

    public static void saveFile() throws IOException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            if (!path.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(path);
        for (Task task : tasks) {
            fileWriter.write(task.formatString());
        }
        fileWriter.close();
    }

    public static void loadFile() throws FileNotFoundException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            throw new FileNotFoundException();
        }
        Scanner sc = new Scanner(path);
        try {
            while (sc.hasNext()) {
                String fileInput = sc.nextLine();
                parseData(fileInput);
            }
        } catch (Exception e) {
            System.out.println("Load failed.");
            tasks.clear();
        }
    }

    public static void exitProgram() {
        // exits the program
        System.out.println("\t------------------------------------------");
        System.out.println("\tSee you soon! Goodbye! ^.^");
        System.out.println("\t__________________________________________\n");
    }

    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("\t------------------------------------------");
        if (tasks.size() == 0) {
            // if list is empty
            System.out.println("\tYour list is empty!");
        }
        else {
            // displays the list of tasks
            System.out.println("\tHere are the tasks in your list: ");
            int i = 1;
            for (Task task : tasks) {
                System.out.println("\t" + i++ + "." + task.toString());
            }
        }
        System.out.println("\t__________________________________________\n");
    }

    public static void markTask(String userInput, ArrayList<Task> tasks) throws NumberFormatException,
            IndexOutOfBoundsException {
        // check if task exists
        System.out.println("\t------------------------------------------");
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex > tasks.size()-1) {
                System.out.println("\tTask " + ++taskIndex + " does not exist! Please try again.");
            }
            else {
                // sets a task as done
                Task task = tasks.get(taskIndex);
                task.markAsDone();
                System.out.println("\tGreat job! I've marked this task as done: ");
                System.out.println("\t" + task.toString());
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\tOOPS!! To mark task, you have to enter an integer following the work done " +
                    "in this format e.g. 'done 3'.");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!! Invalid task input!");
        }
        System.out.println("\t__________________________________________\n");
    }

    public static void addTask(String userInput, ArrayList<Task> tasks) throws StringIndexOutOfBoundsException {
        // adds a task to the list
        System.out.println("\t------------------------------------------");
        switch (userInput.split(" ")[0].toLowerCase()) {
        case "todo":
            // adds to-do tasks
            try {
                Task todo = new Todo(userInput.substring(5), false);
                tasks.add(todo);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + todo.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a todo cannot be empty! It must be in the following" +
                        " format e.g. 'todo Homework");
            }
            break;
        case "deadline":
            // adds tasks with deadline
            try {
                Task deadline = new Deadline(userInput.substring(9, userInput.indexOf("/by")), false,
                        userInput.substring(userInput.indexOf("/by")+4));
                tasks.add(deadline);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + deadline.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a deadline is required in the following format e.g." +
                                " 'deadline CS2113 Project /by Thursday 9pm'.");
            }
            break;
        case "event":
            // adds event tasks
            try {
                Task event = new Event(userInput.substring(6, userInput.indexOf("/at")), false,
                        userInput.substring(userInput.indexOf("/at")+4));
                tasks.add(event);
                System.out.println("\tGot it. I've added this task: ");
                System.out.println("\t" + event.toString());
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
            catch (StringIndexOutOfBoundsException e) {
            System.out.println("\tOOPS!!! The description of an event is required in the following format e.g." +
                    " 'event CS2113 Meeting /at Monday 1pm'.");
            }
            break;
        default:
            // invalid command
            System.out.println("\tOOPS! I'm sorry, but I don't know what that means! :-(");
        }
        System.out.println("\t__________________________________________\n");
    }

    public static void deleteTask(String userInput, ArrayList<Task> tasks) throws StringIndexOutOfBoundsException {
        System.out.println("\t------------------------------------------");
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskIndex > tasks.size()-1) {
                System.out.println("\tTask " + ++taskIndex + " does not exist! Please try again.");
            }
            else {
                // sets a task as done
                Task task = tasks.get(taskIndex);
                System.out.println("\tNoted, I've removed this task: ");
                System.out.println("\t" + task.toString());
                tasks.remove(task);
                System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("\tOOPS!! To delete task, you have to enter an integer following the work delete " +
                    "in this format e.g. 'delete 3'.");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("\tOOPS!! Invalid task input!");
        }
        System.out.println("\t__________________________________________\n");

    }
}
