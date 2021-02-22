package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static String by;
    static String at;
    static int numberOfTasks = 0;
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<String> keywords = new ArrayList<>();
    public static final String LINE_DIVIDER = "____________________________________________________________";
    static final int START_INDEX_OF_BY = 3;
    static final int START_INDEX_OF_AT = 3;
    static final int START_INDEX_OF_EVENT = 5;
    static final int START_INDEX_OF_DEADLINE = 8;

    public static void printDone(int description) {
        System.out.println("This task has been done. Good job!");
        tasks.get(description).printDescription();
        System.out.println("\n" + LINE_DIVIDER);
    }

    public static void printDeleted() {
        System.out.println("The task has been deleted.");
        System.out.println(LINE_DIVIDER);
    }

    public static void printTotalTasks() {
        if (numberOfTasks >= 2) {
            System.out.println(numberOfTasks + "  tasks left in the list");
        } else if (numberOfTasks == 1) {
            System.out.println("Only 1 task in the list");
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void executeTodo(String command) {
        if (command.length() == 4) {
            System.out.println("Woopies! The description of a todo cannot be empty.");
            return;
        }
        tasks.add(new Todo(command.replaceFirst("todo", "")));
        Todo.printTodoDescription();
        keywords.add("T");
        tasks.get(numberOfTasks++).printDescription();
        System.out.print("\n");
        printTotalTasks();
    }

    public static void executeDeadline(String command) {
        try {
            by = command.substring(command.indexOf("/") + START_INDEX_OF_BY);
            command = command.substring(START_INDEX_OF_DEADLINE, command.indexOf("/"));
            tasks.add(new Deadline(command, by));
            Deadline.printDeadlineDescription();
            keywords.add("D");
            tasks.get(numberOfTasks++).printDescription();
            System.out.println("(by:" + by + ")");
            printTotalTasks();
        } catch (IndexOutOfBoundsException e) {
            DukeException.printDeadlineIsEmpty();
        }
    }

    public static void executeEvent(String command) {
        try {
            at = command.substring(command.indexOf("/") + START_INDEX_OF_AT);
            command = command.substring(START_INDEX_OF_EVENT, command.indexOf("/"));
            tasks.add(new Event(command, at));
            Event.printEventDescription();
            keywords.add("E");
            tasks.get(numberOfTasks++).printDescription();
            System.out.println("(at:" + at + ")");
            printTotalTasks();
        } catch (IndexOutOfBoundsException e) {
            DukeException.printEventIsEmpty();
        }
    }

    public static void executeDone(String command) {
        String[] words;
        words = command.split(" ");
        tasks.get(Integer.parseInt(words[1]) - 1).markAsDone();
        printDone(Integer.parseInt(words[1]) - 1);
    }

    public static void executeDelete(String command) {
        String[] words;
        words = command.split(" ");
        tasks.remove(Integer.parseInt(words[1]) - 1);
        printDeleted();
    }

    public static void listTasks() throws IndexOutOfBoundsException {
        if (tasks.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 1; i <= tasks.size(); ++i) {
            System.out.print(i + ". ");
            switch (keywords.get(i - 1)) {
            case "T":
                System.out.print("[T]");
                tasks.get(i - 1).printDescription();
                System.out.print("\n");
                break;
            case "D":
                System.out.print("[D]");
                tasks.get(i - 1).printDescription();
                System.out.println("(by:" + by + ")");
                break;
            case "E":
                System.out.print("[E]");
                tasks.get(i - 1).printDescription();
                System.out.println("(at:" + at + ")");
                break;
            default:
                System.out.println("Oops! An error occurred");
                break;
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void saveData() {
        try {
            File path = new File("dukeSave.txt");
            FileWriter fw = new FileWriter(path);
            writeData(fw);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred in saving");
        }
    }

    public static void writeData(FileWriter fw) throws IOException {
        for (int i = 0; i < tasks.size(); ++i) {
            fw.write(keywords.get(i) + " | " + tasks.get(i).getDescription());
            if (keywords.get(i) == "E") {
                fw.write(" | " + at);
            } else if (keywords.get(i) == "D") {
                fw.write(" | " + by);
            }
            fw.write(System.lineSeparator());
        }
    }

    public static void loadData() throws FileNotFoundException, NoSuchElementException {
        String loadedCommand;
        System.out.println("Loading data....");
        File f = new File("dukeLoad.txt");
        Scanner input = new Scanner(f);

        loadedCommand = input.nextLine();
        filterCommands(loadedCommand);
        while (input.hasNextLine()) {
            loadedCommand = input.nextLine();
            filterCommands(loadedCommand);
        }
    }

    public static void processUserData() {
        String userCommand;
        Scanner input = new Scanner(System.in);
        userCommand = input.nextLine();
        while (!userCommand.equals("bye")) {
            filterCommands(userCommand);
            userCommand = input.nextLine();
        }
    }

    public static void filterCommands(String command) {
        // makes the input case-insensitive
        command = command.toLowerCase();
        if (command.equals("list")) {
            try {
                listTasks();
            } catch (IndexOutOfBoundsException e) {
                DukeException.listIsEmpty();
            }
        } else if (command.contains("done")) {
            executeDone(command);
        } else if (command.contains("delete")) {
            executeDelete(command);
        } else if (command.contains("todo")) {
            executeTodo(command);
        } else if (command.contains("deadline")) {
            executeDeadline(command);
        } else if (command.contains("event")) {
            executeEvent(command);
        } else {
            DukeException.printCommandIsInvalid();
        }
    }

    public static void main(String[] args) {
        UserInterface.printHello();
        try {
            loadData();
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading. File not found");
            System.out.println(LINE_DIVIDER);
        } catch (NoSuchElementException e) {
            System.out.println("The loaded file is empty. Please enter an input");
            System.out.println(LINE_DIVIDER);
        }
        processUserData();
        saveData();
        UserInterface.printBye();
    }
}

