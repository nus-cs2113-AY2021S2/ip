package duke;
import duke.tasks.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

//    public static int taskCount = 1;
//    public static Task[] tasks = new Task[101];
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void goodbye() {
        System.out.println("\n" +
                "░██████╗░░█████╗░░█████╗░██████╗░██████╗░██╗░░░██╗███████╗\n" +
                "██╔════╝░██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗░██╔╝██╔════╝\n" +
                "██║░░██╗░██║░░██║██║░░██║██║░░██║██████╦╝░╚████╔╝░█████╗░░\n" +
                "██║░░╚██╗██║░░██║██║░░██║██║░░██║██╔══██╗░░╚██╔╝░░██╔══╝░░\n" +
                "╚██████╔╝╚█████╔╝╚█████╔╝██████╔╝██████╦╝░░░██║░░░███████╗\n" +
                "░╚═════╝░░╚════╝░░╚════╝░╚═════╝░╚═════╝░░░░╚═╝░░░╚══════╝\n" +
                "Hope to see you again soon!");
    }

    public static void welcome() {
        System.out.println("\n" +
                "██╗░░██╗███████╗██╗░░░░░██╗░░░░░░█████╗░\n" +
                "██║░░██║██╔════╝██║░░░░░██║░░░░░██╔══██╗\n" +
                "███████║█████╗░░██║░░░░░██║░░░░░██║░░██║\n" +
                "██╔══██║██╔══╝░░██║░░░░░██║░░░░░██║░░██║\n" +
                "██║░░██║███████╗███████╗███████╗╚█████╔╝\n" +
                "╚═╝░░╚═╝╚══════╝╚══════╝╚══════╝░╚════╝░\n" +
                "from\n" + "\n" +
                "██████╗░██╗░░░██╗██╗░░██╗███████╗\n" +
                "██╔══██╗██║░░░██║██║░██╔╝██╔════╝\n" +
                "██║░░██║██║░░░██║█████═╝░█████╗░░\n" +
                "██║░░██║██║░░░██║██╔═██╗░██╔══╝░░\n" +
                "██████╔╝╚██████╔╝██║░╚██╗███████╗\n" +
                "╚═════╝░░╚═════╝░╚═╝░░╚═╝╚══════╝\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("_________________________________________________________________________");
    }

    public static void printInvalidMessage(String type) {
        System.out.println("\uD83D\uDE1E OOPS! " + type + " is not valid, please try again!");
    }

    public static void validateInput (String input, String type) throws DukeException {
        try {
            if (type.equals("done") && (!(input.strip()).matches("[-+]?\\\\d*\\\\.?\\\\d+\n"))) {
                throw new DukeException();
            } else if (type.equals("todo") && (input.strip().length() <= 0)) {
                throw new DukeException();
            } else {
                if (!input.contains("/")) {
                    if (type.equals("deadline")) {
                        throw new DukeException();
                    } else if (type.equals("event")){
                        throw new DukeException ();
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
                System.out.println(i+1 + ". " + currentTask.getTaskType() + currentTask.getStatusIcon() + " "
                    + currentTask.getDescription() + " " + currentTask.getDateTime());
            }
        } else {
            System.out.println("Please input a task.");
        }
    }

    public static void markAsDone(String taskDone) {
        int taskIndex = Integer.parseInt(taskDone) - 1;
        if (taskIndex >= tasks.size() || taskIndex < 0){
            System.out.println("You have not added task " + taskIndex + " yet! Please try again.");
        } else {
            tasks.get(taskIndex - 1).setDone();
            System.out.println("Nice! I've marked this task as done:");
            Task currentTask = tasks.get(taskIndex);
            System.out.println(currentTask.getTaskType() + currentTask.getStatusIcon()
                    + currentTask.getDescription());
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
                System.out.println("Now you have " +tasks.size() + " task in the list.");
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
            System.out.println(e.getTaskType() + e.getStatusIcon() + " " + e.getDescription() + " " + e.getDateTime());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DukeException | IndexOutOfBoundsException e) {
            printInvalidMessage("event");
        }
    }

    public static void addDeadline(String taskToAdd){
        try {
            validateInput(taskToAdd, "deadline");
            int splitIndex = taskToAdd.indexOf('/');
            String description = taskToAdd.substring(0, splitIndex);
            String dateTime = taskToAdd.substring(splitIndex + 3);
            Deadline d = new Deadline(description, dateTime);
            tasks.add(d);
            System.out.println("Got it. I've added this task:");
            System.out.println(d.getTaskType() + d.getStatusIcon() + " " + d.getDescription() + " " + d.getDateTime());
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
            } else  {
                System.out.println("Now you have " + tasks.size() + " task in the list.");
            }
        }
    }

    public static void getInput() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String inputCommand = sc.nextLine();
            if (inputCommand.equals("list")) {
                showList();
            } else if (inputCommand.equals("bye")) {
                goodbye();
                break;
            } else {
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
            System.out.println("_________________________________________________________________________");
        }
    }

    public static void main(String[] args) {
        welcome();
        getInput();
    }
}