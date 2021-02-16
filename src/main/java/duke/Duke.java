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

    static final String LINE_DIVIDER = "____________________________________________________________";
    static String by;
    static String at;
    static ArrayList <Task> tasks = new ArrayList<>();
    static ArrayList <String> keywords = new ArrayList<>();
    static int numberOfTasks = 0;

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
        tasks.add(new Todo(command.replaceFirst("todo", "")));
        Todo.printTodoDescription();
        keywords.add("T");
        tasks.get(numberOfTasks++).printDescription();
        System.out.print("\n");
        printTotalTasks();

    }

    public static void executeDeadline(String command) {
        try {
            by = command.substring(command.indexOf("/") + 3);
            command = command.substring(8, command.indexOf("/"));
            tasks.add(new Deadline(command, by));
            Deadline.printDeadlineDescription();
            keywords.add("D");
            tasks.get(numberOfTasks++).printDescription();
            System.out.println("(by:" + by + ")");
            printTotalTasks();
        } catch (IndexOutOfBoundsException e) {
            DukeException.deadlineIsEmpty();
        }
    }

    public static void executeEvent(String command) {
        try {
            at = command.substring(command.indexOf("/") + 3);
            command = command.substring(5, command.indexOf("/"));
            tasks.add(new Event(command, at));
            Event.printEventDescription();
            keywords.add("E");
            tasks.get(numberOfTasks++).printDescription();
            System.out.println("(at:" + at + ")");
            printTotalTasks();
        } catch (IndexOutOfBoundsException e) {
            DukeException.eventIsEmpty();
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
            }
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void saveData() {
        try {
            File path = new File("duke.txt");
            FileWriter fw = new FileWriter(path);
            for (int i = 0; i < numberOfTasks; ++i) {
                fw.write(keywords.get(i) + " | " + tasks.get(i).getDescription());
                if (keywords.get(i) == "E") {
                    fw.write(" | " + at);
                } else if (keywords.get(i) == "D") {
                    fw.write(" | " + by);
                }
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred in saving");
        }
    }

    public static void loadData() throws FileNotFoundException {
        String command;
        File f = new File("duke.txt");
        Scanner input = new Scanner(f);
        command = input.nextLine();

        while (!command.equals("bye")) {
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
            }
            else if (command.contains("todo")) {
                if (!DukeException.checkTodo(command)) {
                    executeTodo(command);
                }
            } else if (command.contains("deadline")) {
                executeDeadline(command);
            } else if (command.contains("event")) {
                executeEvent(command);
            } else {
                DukeException.commandIsInvalid();
            }

            try {
                command = input.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        UserInterface.printHello();
        try {
            loadData();
        } catch (FileNotFoundException e) {
            System.out.println("Error in loading");
        }
        saveData();
        UserInterface.printBye();
    }
}

