package duke;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final int DONE_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;
    public static final int DELETE_START = 7;

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int maxTaskIndex = 0;

    public static Scanner in = new Scanner(System.in);
    public static String input;

    public static void main(String[] args) {
        welcomeMessage();

        input = getString(in);
        Command command;

        while (!isBye()) {
            if (isList()) {
                command = Command.LIST;
            } else if (isDone()) {
                command = Command.DONE;
            } else if (isTodo()) {
                command = Command.TODO;
            } else if (isEvent()) {
                command = Command.EVENT;
            } else if (isDelete()) {
                command = Command.DELETE;
            } else if (isDeadline()) {
                command = Command.DEADLINE;
            } else {
                command = Command.INVALID;
            }

            try {
                executeCommand(input, command);
            } catch (InvalidCommandException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (EmptyInputException e) {
                System.out.println("OOPS!!! The description of a new task cannot be empty.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! You need to add time for new Event or Deadline with '/at' or '/by'!!");
            } catch (InvalidEventTimeException e) {
                System.out.println("OOPS!!! You need to add time for new Event with keyword '/at'!!");
            } catch (InvalidDeadlineTimeException e) {
                System.out.println("OOPS!!! You need to add time for new Deadline with keyword '/by'!!");
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! I don't recognise the number to process Task Index to be done or deleted!!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! You need to add valid Task Index to be done or deleted!!");
            }

            System.out.println();
            input = getString(in);
        }


        exitMessage();

    }

    public static void executeCommand(String input, Command c) throws InvalidCommandException, EmptyInputException, InvalidEventTimeException, InvalidDeadlineTimeException {
        switch (c) {
        case LIST:
            listBeginMessage();
            enumerateTasks();
            break;
        case DONE:
            markTaskAsDone(input);
            break;
        case DELETE:
            deleteTask(input);
            decrementTasks();
            break;
        case TODO:
            verifyValidInput(input, c);
            addNewTodo(input);
            confirmNewTask();
            incrementTasks();
            break;
        case EVENT:
            verifyValidInput(input, c);
            addNewEvent(input);
            confirmNewTask();
            incrementTasks();
            break;
        case DEADLINE:
            verifyValidInput(input, c);
            addNewDeadline(input);
            confirmNewTask();
            incrementTasks();
            break;
        case INVALID:
            throw new InvalidCommandException();

        }
    }

    private static void deletedTaskMessage(int deletedTaskIndex) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(deletedTaskIndex));
        System.out.println("Now you have " + (maxTaskIndex-1) + " tasks in the list." + "\n");
    }

    private static void deleteTask(String input) {
        int taskNumberToDelete = (Integer.parseInt(input.substring(DELETE_START, input.length()).strip())-1);
        deletedTaskMessage(taskNumberToDelete);
        tasks.remove(taskNumberToDelete);
    }

    private static void verifyValidInput(String input, Command c) throws EmptyInputException, InvalidEventTimeException, InvalidDeadlineTimeException {
        if (isEmptyInput(input, c)) {
            throw new EmptyInputException();
        }

        switch (c) {
        case EVENT:
            if (validEventTime(input)) {
                break;
            } else {
                throw new InvalidEventTimeException();
            }
        case DEADLINE:
            if (validDeadlineTime(input)) {
                break;
            } else {
                throw new InvalidDeadlineTimeException();
            }
        }
    }

    private static boolean validDeadlineTime(String input) {
        return input.substring(getTimePosition(input), getTimePosition(input) + 3).equals("/by");
    }

    private static boolean validEventTime(String input) {
        return (input.substring(getTimePosition(input), getTimePosition(input) + 3).equals("/at"));
    }


    private static boolean isEmptyInput(String input, Command c) {
        return switch (c) {
            case TODO -> (input.substring(TODO_START, input.length()).strip().equals(""));
            case EVENT -> input.substring(EVENT_START, getTimePosition(input)).strip().equals("");
            case DEADLINE -> (input.substring(DEADLINE_START, getTimePosition(input)).strip().equals(""));
            default -> false;
        };
    }


    private static void markTaskAsDone(String input) {
        int completedTaskIndex = getCompletedTaskIndex(input);
        tasks.get(completedTaskIndex).markAsDone();
        completedTaskMessage(completedTaskIndex);
    }

    private static void addNewDeadline(String input) {
        tasks.add(new Deadline(input.substring(DEADLINE_START, getTimePosition(input)), getTime(input)));
    }

    private static void addNewEvent(String input) {
        tasks.add(new Event(input.substring(EVENT_START, getTimePosition(input)), getTime(input)));
    }

    private static void addNewTodo(String input) {
        tasks.add(new ToDo(input.substring(TODO_START, input.length())));
    }

    private static int getCompletedTaskIndex(String input) {
        return (Integer.parseInt(input.substring(DONE_START, input.length()))-1);
    }

    private static void enumerateTasks() {
        int currentTaskIndex;
        for (int i = 0; i < maxTaskIndex; i++) {
            currentTaskIndex = i + 1;
            System.out.println(currentTaskIndex + "." + tasks.get(i).toString());
        }
    }

    private static void listBeginMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    private static void completedTaskMessage(int completedTaskIndex) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + tasks.get(completedTaskIndex).getStatusIcon() + "] " + tasks.get(completedTaskIndex).getDescription() + "\n");
    }

    private static void incrementTasks() {
        maxTaskIndex++;
    }

    private static void decrementTasks() {
        maxTaskIndex--;
    }

    private static int getTimePosition(String input) {
        return input.indexOf('/');
    }

    private static String getTime(String input) {
        int timePosition = getTimePosition(input);
        return input.substring(timePosition + 3, input.length());
    }

    private static void confirmNewTask() {
        int numTasks = getNumTasks();
        System.out.println(tasks.get(maxTaskIndex));
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    private static int getNumTasks() {
        return maxTaskIndex + 1;
    }

    private static boolean isBye() {
        return input.equals("bye");
    }

    private static boolean isDeadline() {
        return input.length() > 8 && input.substring(0, 8).equals("deadline");
    }

    private static boolean isEvent() {
        return input.length() > 5 && input.substring(0, 5).equals("event");
    }

    private static boolean isTodo() {
        return input.length() > 4 && input.substring(0, 4).equals("todo");
    }

    private static boolean isList() {
        return input.equals("list");
    }

    private static boolean isDelete() {
        return input.length() > 6 && input.substring(0, 6).equals("delete");
    }

    private static boolean isDone() {
        return input.length() > 4 && input.substring(0, 4).equals("done");
    }

    private static String getString(Scanner in) {
        String input;
        input = in.nextLine();
        return input;
    }

    private static void welcomeMessage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    private static void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

}
