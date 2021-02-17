package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final int TODO_LENGTH = 5;
    private static final int DONE_LENGTH = 5;
    private static final int DEADLINE_LENGTH = 9;
    private static final int EVENT_LENGTH = 6;
    private static final int BY_LENGTH = 3;
    private static final int AT_LENGTH = 3;
    private static final int DELETE_LENGTH = 7;
    private static final String exceptionGreeting = "\ud83d\ude16 OOPS!!! ";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCounter = 0;
    public static final String ROOT_PATH = System.getProperty("user.dir");
    public static final Path FOLDER_PATH = Paths.get(ROOT_PATH, "data");
    public static final Path FILE_PATH = Paths.get(ROOT_PATH, "data", "duke.txt");

    public static void main(String[] args) {
        createFile();
        loadFile();
        showWelcomeMessage();
        inputLoop();
        saveToFile();
    }

    private static void loadFile() {
        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String line : lines) {
                String[] components = line.split("/");
                System.out.println(components[2]);
                Task newTask = new Task(components[2]);
                switch (components[0]) {
                case "T":
                    newTask = new ToDo(components[2]);
                    break;
                case "D":
                    newTask = new Deadline(components[2], components[3]);
                    break;
                case "E":
                    newTask = new Event(components[2], components[3]);
                }
                if (components[1].equals("1")) {
                    newTask.markAsDone();
                }
                tasks.add(newTask);
                taskCounter++;
            }
        } catch (IOException e) {
        }
    }

    private static void createFile() {
        try {
            Files.createDirectory(FOLDER_PATH);
        } catch (FileAlreadyExistsException e) {
        } catch (IOException e) {
        }

        try {
            Files.createFile(FILE_PATH);
        } catch (FileAlreadyExistsException e) {
        } catch (IOException e) {
        }
    }

    private static void saveToFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH.toString());
            for (Task t: tasks) {
                String description = t.getDescription();
                String done = (t.isDone()) ? "1" : "0";
                if (t instanceof ToDo) {
                    fw.write("T" + "/" + done + "/" + description + System.lineSeparator());
                } else if (t instanceof Deadline) {
                    String by = ((Deadline) t).getBy();
                    fw.write("D" + "/" + done + "/" + description + "/" + by + System.lineSeparator());
                } else if (t instanceof Event) {
                    String at = ((Event) t).getAt();
                    fw.write("E" + "/" + done + "/" + description + "/" + at + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
        }
    }

    private static void inputLoop() {
        Scanner in = new Scanner(System.in);
        String line;
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                showExitMessage();
                break;
            } else if (line.equals("list")) {
                printTasksList();
            } else if (line.startsWith("done")) {
                markAsDone(line);
            } else if (line.startsWith("todo")) {
                addNewTodo(line);
            } else if (line.startsWith("deadline")) {
                addNewDeadline(line);
            } else if (line.startsWith("event")) {
                addNewEvent(line);
            } else if (line.startsWith("delete")) {
                removeTask(line);
            } else {
                printInvalidInput();
            }
        }
    }

    private static void removeTask(String line) {
        try {
            int index = Integer.parseInt(line.substring(DELETE_LENGTH)) - 1;
            Task deleted = tasks.get(index);
            tasks.remove(index);
            taskCounter--;
            printRemoveTask(deleted);
        } catch (IndexOutOfBoundsException e) {
            printInvalidTask();
        }
    }

    private static void printInvalidInput() {
        printHorizontalLine();
        System.out.println("\t" + exceptionGreeting + "I'm sorry, but I don't know what that means :-(");
        printHorizontalLine();
    }

    private static void addNewEvent(String line) {
        try {
            int index = line.indexOf("/");
            String description = line.substring(EVENT_LENGTH, index - 1);
            String at = line.substring(index + AT_LENGTH).trim();
            Task newTask = new Event(description, at);
            tasks.add(newTask);
            taskCounter++;
            printNewTask();
        } catch (StringIndexOutOfBoundsException e) {
            printEmptyDescription("event");
        }
    }

    private static void addNewDeadline(String line) {
        try {
            int index = line.indexOf("/");
            String description = line.substring(DEADLINE_LENGTH, index - 1);
            String by = line.substring(index + BY_LENGTH).trim();
            Task newTask = new Deadline(description, by);
            tasks.add(newTask);
            taskCounter++;
            printNewTask();
        } catch (StringIndexOutOfBoundsException e) {
            printEmptyDescription("deadline");
        }
    }

    private static void addNewTodo(String line) {
        try {
            String description = line.substring(TODO_LENGTH);
            Task newTask = new ToDo(description);
            tasks.add(newTask);
            taskCounter++;
            printNewTask();
        } catch (StringIndexOutOfBoundsException e) {
            printEmptyDescription("todo");
        }
    }

    private static void printEmptyDescription(String type) {
        printHorizontalLine();
        System.out.println("\t" + exceptionGreeting + "The description of a " + type + " cannot be empty.");
        printHorizontalLine();
    }

    private static void markAsDone(String line) {
        try {
            int index = Integer.parseInt(line.substring(DONE_LENGTH)) - 1;
            tasks.get(index).markAsDone();
            printDoneTask(index);
        } catch (IndexOutOfBoundsException e) {
            printInvalidTask();
        }
    }

    private static void printInvalidTask() {
        printHorizontalLine();
        System.out.println("\t" + exceptionGreeting + "That task number does not exist.");
        printHorizontalLine();
    }

    private static void printDoneTask(int index) {
        printHorizontalLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.print("\t  ");
        System.out.println(tasks.get(index));
        printHorizontalLine();
    }

    private static void printRemoveTask(Task t) {
        printHorizontalLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.print("\t  ");
        System.out.println(t);
        if (taskCounter == 1) {
            System.out.println("\tNow you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskCounter + " tasks in the list.");
        }
        printHorizontalLine();
    }

    private static void printTasksList() {
        printHorizontalLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= taskCounter; i++) {
            System.out.print("\t" + i + ". ");
            System.out.println(tasks.get(i - 1));
        }
        printHorizontalLine();
    }

    private static void printNewTask() {
        printHorizontalLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasks.get(taskCounter - 1));
        if (taskCounter == 1) {
            System.out.println("\tNow you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("\tNow you have " + taskCounter + " tasks in the list.");
        }
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("\t---------------------------------------------------------------------");
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    private static void showExitMessage() {
        printHorizontalLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
