import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;
import io.DukePrint;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    public static void main(String[] args) {
        DukePrint.printLogo();

        Scanner sc = new Scanner(System.in);
        String phrase;

        try {
            loadFile();
            System.out.println("Save data loaded!");
        } catch (Exception e) {
            System.out.println("No save files found.");
        }

        DukePrint.printDivider();
        System.out.println("What's up! I'm Duke");
        System.out.println("What can I do for you?");
        DukePrint.printEndDivider();

        do {
            phrase = sc.nextLine();
            DukePrint.printDivider();
            inputCommand(phrase, tasks);
            DukePrint.printEndDivider();
        } while (!phrase.equals("bye"));
    }

    public static void inputCommand(String phrase, ArrayList<Task> tasks) {
        String[] subStrings = phrase.split(" ");
        int dividerPosition = phrase.indexOf("/");

        switch (subStrings[0]) {
        case ("bye"):
            // Exits program
            System.out.println("Alright cheers mate!");
            // Saves task list
            try {
                saveFile();
            } catch (Exception e) {
                System.out.println("Failed to save file!");
            }
            break;
        case ("list"):
            // List all tasks
            System.out.println("Here are the tasks in your list:");
            int i = 0;
            for (Task task : tasks) {
                System.out.println(++i + ". " + task.toString());
            }
            break;
        case ("done"):
            // Set a task as done
            try {
                int taskIndex = phrase.charAt(phrase.length() - 1) - '0';

                Task task = tasks.get(taskIndex - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
            } catch (NumberFormatException e) {
                System.out.println("\uD83D\uDE2D Please input a valid number!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\uD83D\uDE2D Sorry task index is out of range!");
            }
            break;
        case ("todo"):
            // Add a To-Do
            try {
                phrase = phrase.substring(5);
                Todo todo = new Todo(phrase);
                System.out.println("Got it! I've added this task:");
                tasks.add(todo);
                System.out.println(todo);
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\uD83D\uDE2D Description of To-Do cannot be empty!");
            }
            break;
        case ("deadline"):
            try {
                // Add a models.Deadline
                String by = phrase.substring(dividerPosition + 4);
                phrase = phrase.substring(9, dividerPosition);
                Deadline deadline = new Deadline(phrase, by);
                System.out.println("Got it! I've added this task:");
                tasks.add(deadline);
                System.out.println(deadline);
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\uD83D\uDE2D Please enter deadline in the format: 'deadline <name> /by <day> <time>'");
            }
            break;
        case ("event"):
            // Add an models.Event
            try {
                String eventTime = phrase.substring(dividerPosition + 4);
                phrase = phrase.substring(6, dividerPosition);
                Event event = new Event(phrase, eventTime);
                System.out.println("Got it! I've added this task:");
                tasks.add(event);
                System.out.println(event);
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\uD83D\uDE2D Please enter event in the format: 'event <name> /at <day> <time>'");
            }
            break;
        default:
            // Invalid models.Task
            System.out.println("\uD83D\uDE2D Sorry mate I do not understand your request. Please specify task");
            break;
        }
    }

    public static void parseData(String line) {
        String[] tokens = line.split("#");

        switch (tokens[0]) {
        case "T":
            try {
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                Todo todo = new Todo(tokens[2], isDone);
                tasks.add(todo);
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case "D":
            try {
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                Deadline deadline = new Deadline(tokens[2], tokens[3], isDone);
                tasks.add(deadline);
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case "E":
            try {
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                Event event = new Event(tokens[2], tokens[3], isDone);
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
        for (int i = 0; i < taskCount; i++) {
            fileWriter.write(tasks.get(i).formatData());
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public static void loadFile() throws FileNotFoundException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(path);
        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                parseData(line);
            }
        } catch (Exception e) {
            System.out.println("Failed to load!");
            taskCount = 0;
            tasks.clear();
        }

    }
}
