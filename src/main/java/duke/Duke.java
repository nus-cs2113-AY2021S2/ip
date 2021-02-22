package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.File;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Duke {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_SEPARATOR = "CHOPCHOP";
    private static final int SEPARATOR_LENGTH = 60;

    private static void printSeparator() {
        for(int i = 0; i < SEPARATOR_LENGTH; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    private static void exitMethod() {
        System.out.print("My cover's blown!\n");
        printSeparator();
    }

    /**
     * Echoes the user input with random upper and lower case for mockery.
     *
     * @param line User input.
     */
    private static void mockEcho(String line) {
        Random rd = new Random();
        for (int i = 0; i < line.length(); i++) {
            if (rd.nextBoolean()) {
                System.out.print(Character.toUpperCase(line.charAt(i)));
            } else {
                System.out.print(Character.toLowerCase(line.charAt(i)));
            }
        }
        System.out.print('\n');
    }

    private static void printTasks() throws DukeException{
        if (tasks.size() < 1) {
            throw new DukeException();
        } else {
            System.out.print("There are " + tasks.size() + " tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print((i+1) + "." + tasks.get(i) + '\n');
            }
        }
    }

    private static void addTask(Task t) throws DukeException{
        if (t.getDescription().isEmpty()) {
            throw new DukeException();
        } else {
            tasks.add(t);
            System.out.print("Got it. I've added this task:\n" + t.toString() + '\n');
        }
    }

    private static void addTodo(Todo todo) {
        try {
            addTask(todo);
        } catch (DukeException e) {
            System.out.print("The description of a todo cannot be empty.\n");
        }
    }

    private static void addDeadline(Deadline deadline) {
        try {
            addTask(deadline);
        } catch (DukeException e) {
            System.out.print("The description of a deadline cannot be empty.\n");
        }
    }

    private static void addEvent(Event event) {
        try {
            addTask(event);
        } catch (DukeException e) {
            System.out.print("The description of an event cannot be empty.\n");
        }
    }

    private static void deleteTask(int taskIndex) throws DukeException{
        try {
            System.out.print("Got it. I've deleted this task:\n" + tasks.get(taskIndex).toString() + '\n');
            tasks.remove(taskIndex);
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    private static void markIndexDone(int taskIndex) throws DukeException{
        try {
            tasks.get(taskIndex).setDone();
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    private static void printHelp() {
        System.out.print("I can remember your tasks for you!\n\n" +
                "Available commands:\n" +
                "\ttodo <description>\n" +
                "\tdeadline <description> /by <time due>\n" +
                "\tevent <description> /at <time occurring>\n" +
                "\tlist\n" + "\tdone <task index>\n" +
                "\tdelete <task index>\n");
    }

    private static void executeCommand(String line) {
        String[] lineParts;
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
            } catch (IndexOutOfBoundsException e) {
                System.out.print("That is not a valid command. Please enter a number after the word.\n");
            }
            break;
        case "todo":
            addTodo(new Todo(line.replace("todo", "").trim(), false));
            break;
        case "deadline":
            try {
                int byIndex = line.indexOf("/by");
                addDeadline(new Deadline(line.substring(9, byIndex), line.substring(byIndex + 4), false));
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Something went wrong. Please put the due date after /by.\n");
            }
            break;
        case "event":
            try {
                int atIndex = line.indexOf("/at");
                addEvent(new Event(line.substring(6, atIndex), line.substring(atIndex + 4), false));
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Something went wrong. Please put the event time after /at.\n");
            }
            break;
        case "delete":
            try {
                int taskIndex = Integer.parseInt(lineParts[1]) - 1;
                deleteTask(taskIndex);
            } catch (NumberFormatException e) {
                System.out.print(lineParts[1] + " is not a valid number.\n");
            } catch (DukeException e) {
                System.out.print("That is not a valid task index, please try again.\n");
            }
            break;
        default:
            mockEcho(line);
            System.out.print("Use -h for list of available commands.\n");
        }
    }

    private static void listMode() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")){
            executeCommand(line);
            printSeparator();
            line = in.nextLine();
        }
        exitMethod();
    }

    private static void loadFile(Path path) {
        System.out.print("Looking for existing data...\n");
        //java.nio.file.Files.exists(path)
        try {
            File f = new File(path.toString());
            if (!f.createNewFile()) {
                System.out.println("File already exists.\nLoading...");
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String[] lineParts = s.nextLine().split(FILE_SEPARATOR);
                    switch(lineParts[0]) {
                    case "Todo":
                        addTodo(new Todo(lineParts[2], Boolean.parseBoolean(lineParts[1])));
                        break;
                    case "Deadline":
                        addDeadline(new Deadline(lineParts[2], lineParts[3], Boolean.parseBoolean(lineParts[1])));
                        break;
                    case "Event":
                        addEvent(new Event(lineParts[2], lineParts[3], Boolean.parseBoolean(lineParts[1])));
                        break;
                    }
                }
            } else {
                System.out.println("File created: " + path.toAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void writeToFile(Path path) throws IOException {
        FileWriter fw = new FileWriter(path.toString());
        for (Task t: tasks) {
            if("Todo".equals(t.getTaskType())) {
                fw.write(t.getTaskType() + FILE_SEPARATOR + t.getDone() + FILE_SEPARATOR
                        + t.getDescription() + "\n");
            } else {
                fw.write(t.getTaskType() + FILE_SEPARATOR + t.getDone() + FILE_SEPARATOR
                        + t.getDescription() + FILE_SEPARATOR  + t.getDate() + "\n");
            }
        }
        fw.close();
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "Documents","duke.txt");
        printSeparator();
        loadFile(path);
        printSeparator();
        System.out.print("Greetings, fellow humans!\nI am CI.\nHow may I help you?\n");
        printSeparator();
        listMode();
        try {
            writeToFile(path);
            System.out.print("File saved successfully at " + path.toAbsolutePath() + "\n");
        } catch (IOException e) {
            System.out.print("Something went wrong: " + e.getMessage() + "\n");
        }
        printSeparator();
    }
}
