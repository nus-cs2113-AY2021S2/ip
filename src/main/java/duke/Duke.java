package duke;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.DukeException;

//import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {

    public static final String WELCOME_MESSAGE = "\n" +
            "██╗░░██╗███████╗██╗░░░░░██╗░░░░░░█████╗░\n" +
            "██║░░██║██╔════╝██║░░░░░██║░░░░░██╔══██╗\n" +
            "███████║█████╗░░██║░░░░░██║░░░░░██║░░██║\n" +
            "██╔══██║██╔══╝░░██║░░░░░██║░░░░░██║░░██║\n" +
            "██║░░██║███████╗███████╗███████╗╚█████╔╝\n" +
            "╚═╝░░╚═╝╚══════╝╚══════╝╚══════╝░╚════╝░\n" + "from\n" + "\n" +
            "██████╗░██╗░░░██╗██╗░░██╗███████╗\n" +
            "██╔══██╗██║░░░██║██║░██╔╝██╔════╝\n" +
            "██║░░██║██║░░░██║█████═╝░█████╗░░\n" +
            "██║░░██║██║░░░██║██╔═██╗░██╔══╝░░\n" +
            "██████╔╝╚██████╔╝██║░╚██╗███████╗\n" +
            "╚═════╝░░╚═════╝░╚═╝░░╚═╝╚══════╝\n";

    public static final String GOODBYE_MESSAGE = "\n" +
            "░██████╗░░█████╗░░█████╗░██████╗░██████╗░██╗░░░██╗███████╗\n" +
            "██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝\n" +
            "██║░░██╗░██║░░██║██║░░██║██║░░██║██████╦╝░╚████╔╝░█████╗░░\n" +
            "██║░░╚██╗██║░░██║██║░░██║██║░░██║██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
            "╚██████╔╝╚█████╔╝╚█████╔╝██████╔╝██████╦╝░░░██║░░░███████╗\n" +
            "░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚═════╝░░░░╚═╝░░░╚══════╝\n" + "Hope to see you again soon!";
    public static final String LINE  = "_________________________________________________________________________";

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void goodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public static void welcome() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(LINE);
    }

    public static void printInvalidMessage(String type) {
        System.out.println("\uD83D\uDE1E OOPS! " + type + " is not valid, please try again!");
    }

    public static void validateInput(String input, String type) throws DukeException {
        try {
            if (type.equals("done") && (!(input.strip()).matches("[-+]?\\\\d*\\\\.?\\\\d+\n"))) {
                throw new DukeException();
            } else if (type.equals("todo") && (input.strip().length() <= 0)) {
                throw new DukeException();
            } else {
                if (!input.contains("/")) {
                    if (type.equals("deadline")) {
                        throw new DukeException();
                    } else if (type.equals("event")) {
                        throw new DukeException();
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            printInvalidMessage(type);
        }
    }

    public static void showList() {
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                System.out.print(i + 1 + ". " + currentTask.getTaskType() + currentTask.getStatusIcon() + " "
                        + currentTask.getDescription());
                if (currentTask.getTaskType().equals("[D]"))
                    System.out.print(" (by: " + currentTask.getDateTime() + ")");
                else if (currentTask.getTaskType().equals("[E]")) {
                    System.out.print(" (at: " + currentTask.getDateTime() + ")");
                }
                System.out.print("\n");
            }
        } else {
            System.out.println("Please input a task.");
        }
    }

    public static void markAsDone(String taskDone) {
        try {
            int taskIndex = Integer.parseInt(taskDone) - 1;
            if (taskIndex >= tasks.size() || taskIndex < 0) {
                System.out.println("You have not added task " + taskIndex + " yet! Please try again.");
            } else if (tasks.get(taskIndex).getIsDone() == true) {
                System.out.println("Task have already been set as done before. Please try to complete other tasks.");
            } else {
                tasks.get(taskIndex).setDone();
                System.out.println("Nice! I've marked this task as done:");
                Task currentTask = tasks.get(taskIndex);
                System.out.println(currentTask.getTaskType() + currentTask.getStatusIcon()
                        + " " + currentTask.getDescription());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e + "invalid! Please try again");
        }
    }

    public static void addToDo(String taskToAdd) {
        try {
            validateInput(taskToAdd, "todo");
            Todo t = new Todo(taskToAdd);
            tasks.add(t);
            System.out.println("Got it. I've added this task:");
            System.out.println(t.getTaskType() + t.getStatusIcon() + " " + t.getDescription());
            if (tasks.size() > 1) {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " task in the list.");
            }
        } catch (DukeException | IndexOutOfBoundsException e) {
            printInvalidMessage("todo");
        }
    }

    public static void addEvent(String taskToAdd) {
        try {
            validateInput(taskToAdd, "event");
            int splitIndex = taskToAdd.indexOf('/');
            String description = taskToAdd.substring(0, splitIndex);
            String dateTime = taskToAdd.substring(splitIndex + 3);
            Event e = new Event(description, dateTime);
            tasks.add(e);
            System.out.println("Got it. I've added this task:");
            System.out.println(e.getTaskType() + e.getStatusIcon() + " " + e.getDescription() + " at(: "
                    + e.getDateTime() + ")");
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException | IndexOutOfBoundsException e) {
            printInvalidMessage("event");
        }
    }

    public static void addDeadline(String taskToAdd) {
        try {
            validateInput(taskToAdd, "deadline");
            int splitIndex = taskToAdd.indexOf('/');
            String description = taskToAdd.substring(0, splitIndex);
            String dateTime = taskToAdd.substring(splitIndex + 3);
            Deadline d = new Deadline(description, dateTime);
            tasks.add(d);
            System.out.println("Got it. I've added this task:");
            System.out.println(d.getTaskType() + d.getStatusIcon() + " " + d.getDescription() + " (by: "
                    + d.getDateTime() + ")");
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException | IndexOutOfBoundsException e) {
            printInvalidMessage("deadline");
        }
    }

    public static void deleteTask(String inputCommand) {
        int taskIndex = Integer.parseInt(inputCommand) - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            System.out.println("Task does not exists!");
        } else {
            System.out.println("Noted. I've removed this task:");
            Task currentTask = tasks.get(taskIndex);
            System.out.println(currentTask.getTaskType() + currentTask.getStatusIcon() + " "
                    + currentTask.getDescription());
            tasks.remove(taskIndex);
            if (tasks.size() > 1) {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " task in the list.");
            }
        }
    }

    public static void loadData() {
        try {
            Path dataFilePath = Paths.get("data/");
            Files.createDirectories(dataFilePath);
            File data = new File("data/duke.txt");
            Scanner readFile = new Scanner(data);
            while (readFile.hasNextLine()) {
                String input = readFile.nextLine();
                //split string by whitespace + "|" + whitespace
                String[] taskSplit = input.split("\\s\\|\\s");
                switch (taskSplit[0]) {
                case "[D]":
                    Deadline d = new Deadline(taskSplit[2], taskSplit[3]);
                    tasks.add(d);
                    if (taskSplit[1].equals("1")) {
                        d.setDone();
                    }
                    break;
                case "[T]":
                    Todo t = new Todo(taskSplit[2]);
                    tasks.add(t);
                    if (taskSplit[1].equals("1")) {
                        t.setDone();
                    }
                    break;
                case "[E]":
                    Event e = new Event(taskSplit[2], taskSplit[3]);
                    tasks.add(e);
                    if (taskSplit[1].equals("1")) {
                        e.setDone();
                    }
                }
            }
            readFile.close();
        } catch (IOException e) {
            System.out.println("Error when creating data directory. Please try again.");
        }
    }

    public static boolean saveData() {
        try {

            System.out.println("Saving your data...");
            File file = new File("duke.txt");
            FileWriter fileWriter = new FileWriter("data/duke.txt");

            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                fileWriter.write(currentTask.getTaskType() + " | " + ((currentTask.getIsDone()) ? "1" : "0") + " | "
                        + currentTask.getDescription() + ((currentTask.hasDateTime()) ? " | "
                        + currentTask.getDateTime() : "") + "\n");
            }
            fileWriter.close();
            System.out.println("Data saved");
            return true;
        } catch (IOException e) {
            System.out.println("Error occurred when saving your data. Please try again.");
            return false;
        }
    }

    public static void getInput() {
        boolean isExit = false;
        while (!isExit) {
            Scanner sc = new Scanner(System.in);
            String inputCommand = sc.nextLine();
            switch (inputCommand) {
            case "list":
                showList();
                break;
            case "bye":
                goodbye();
                isExit = true;
                break;
            case "save":
                saveData();
                break;
            default:
                try {
                    String taskToHandle = inputCommand.split(" ", 2)[1];
                    if (inputCommand.startsWith("done")) {
                        markAsDone(taskToHandle);
                    } else if (inputCommand.startsWith("todo")) {
                        addToDo(taskToHandle);
                    } else if (inputCommand.startsWith("deadline")) {
                        addDeadline(taskToHandle);
                    } else if (inputCommand.startsWith("event")) {
                        addEvent(taskToHandle);
                    } else if (inputCommand.startsWith("delete")) {
                        deleteTask(taskToHandle);
                    } else {
                        printInvalidMessage(inputCommand);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    printInvalidMessage(inputCommand);
                }
            }
            System.out.println(LINE);
        }
    }

    public static void main(String[] args) {
        welcome();
        loadData();
        getInput();
    }
}