import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import exceptions.DukeException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    static final int COMMAND_TODO = 0;
    static final int COMMAND_DEADLINE = 1;
    static final int COMMAND_EVENT = 2;
    static final String DIVIDER_LINE = "____________________________________________________________";
    static final String INVALID_NUMBER = "OOPS!!! Invalid task number =/\n" + DIVIDER_LINE;
    static final String UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means =/\n" + DIVIDER_LINE;
    static final String INVALID_TODO = "OOPS!!! Invalid todo description\n" + DIVIDER_LINE;
    static final String INVALID_DEADLINE = "OOPS!!! Invalid deadline description\n" + DIVIDER_LINE;
    static final String INVALID_EVENT = "OOPS!!! Invalid event description\n" + DIVIDER_LINE;
    static final String LIST_EMPTY = "The list is currently empty!\n" + DIVIDER_LINE;
    static final String LIST_FULL = "The list is currently full!\n" + DIVIDER_LINE;

    private static ArrayList<Task> tasks = new ArrayList<>();

    // Prints horizontal line
    public static void printDividerLine() {
        System.out.println(DIVIDER_LINE);
    }
    // Prints hello message
    public static void printHello() {
        printDividerLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke!\n" + logo);
        System.out.println("What can I do for you?\n" + "List of commands: todo, deadline, event, done, bye");
        printDividerLine();
    }
    // Prints bye message
    public static void printBye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividerLine();
    }
    // Prints list of tasks
    public static void printList() throws DukeException {
        printDividerLine();
        int taskNumber = 1;
        int taskCount = tasks.size();
        if (taskCount == 0) {
            throw new DukeException();
        }
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.print(taskNumber++ + ".");
            task.printTask();
        }
        printDividerLine();
    }
    // Adds task to list
    public static void addTask(String userInput, int command) throws DukeException {
        String description;
        String date;
        int indexOfSlash = userInput.indexOf("/");
        int stringLength = userInput.length();
        int indexOfSpace = userInput.indexOf(" ");
        int taskCount = tasks.size();

        printDividerLine();
        if (taskCount == 100) {
            System.out.println(LIST_FULL);
            throw new DukeException();
        }
        switch (command) {
        case COMMAND_TODO:
            if (stringLength < 6 || indexOfSpace != 4) {
                System.out.println(INVALID_TODO);
                throw new DukeException();
            }
            description = userInput.substring(5);
            Todo todo = new Todo(description);
            tasks.add(todo);
            break;
        case COMMAND_DEADLINE:
            if (stringLength < 10 || indexOfSpace != 8 || indexOfSlash == -1) {
                System.out.println(INVALID_DEADLINE);
                throw new DukeException();
            }
            description = userInput.substring(9, indexOfSlash);
            date = userInput.substring(indexOfSlash + 3);
            Deadline deadline = new Deadline(description, date);
            tasks.add(deadline);
            break;
        case COMMAND_EVENT:
            if (stringLength < 7 || indexOfSpace != 5 || indexOfSlash == -1) {
                System.out.println(INVALID_EVENT);
                throw new DukeException();
            }
            description = userInput.substring(6, indexOfSlash);
            date = userInput.substring(indexOfSlash + 3);
            Event event = new Event(description, date);
            tasks.add(event);
            break;
        default:
        }

        taskCount = tasks.size();
        System.out.print("Got it. I've added this task:\n  ");
        tasks.get(taskCount-1).printTask();
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printDividerLine();
    }
    // Deletes task
    public static void deleteTask(String userInput) throws DukeException {
        String number = userInput.substring(7);
        int taskCount = tasks.size();
        int taskIndex = Integer.parseInt(number) - 1;

        if (taskIndex < taskCount && taskIndex >= 0) {
            System.out.print("Noted. I've removed this task:\n  ");
            tasks.get(taskIndex).printTask();
            tasks.remove(taskIndex);
            System.out.println("Now you have " + --taskCount + " tasks in the list.");
            printDividerLine();
        } else {
            throw new DukeException();
        }
    }
    // Marks task as done
    public static void markTask(String userInput) throws DukeException {
        String number = userInput.substring(5);
        int taskCount = tasks.size();
        int taskIndex = Integer.parseInt(number) - 1;

        if (taskIndex < taskCount && taskIndex >= 0) {
            tasks.get(taskIndex).markAsDone();
            System.out.print("Nice! I've marked this task as done:\n  ");
            tasks.get(taskIndex).printTask();
            printDividerLine();
        }
        else {
            throw new DukeException();
        }
    }

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        printHello();

        while(true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                printBye();
                break;
            } else if (userInput.equals("list")) {
                try {
                    printList();
                } catch (DukeException e) {
                    System.out.println(LIST_EMPTY);
                }
            } else if (userInput.startsWith("done")) {
                try {
                    markTask(userInput);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_NUMBER);
                } catch (DukeException e) {
                    System.out.println(INVALID_NUMBER);
                }
            } else if (userInput.startsWith("todo")) {
                try {
                    addTask(userInput, COMMAND_TODO);
                } catch (DukeException e) {
                }
            } else if (userInput.startsWith("deadline")) {
                try {
                    addTask(userInput, COMMAND_DEADLINE);
                } catch (DukeException e) {
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println(INVALID_DEADLINE);
                }
            } else if (userInput.startsWith("event")) {
                try {
                    addTask(userInput, COMMAND_EVENT);
                } catch (DukeException e) {
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(INVALID_EVENT);
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    deleteTask(userInput);
                } catch (DukeException e) {
                    System.out.println(INVALID_NUMBER);
                }
            }
            else {
                System.out.println(UNKNOWN_COMMAND);
            }
        }
    }
}
