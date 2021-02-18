
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    public static int num_of_goals = 0;

    public static Scanner in = new Scanner(System.in);
    public static String user_input;
    public static Task t[] = new Task[100];
    public static final int DONE_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;


    public static void show_welcome_msg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +

                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
    }

    public static void show_exit_msg() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    private static String getString(Scanner in) {
        String user_input;
        user_input = in.nextLine();
        return user_input;

    }


    private static boolean isList() {
        if (user_input.equals("list")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isTodo() {
        if (user_input.length() > 4) {
            if (user_input.substring(0, 4).equals("todo")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDone() {
        if (user_input.length() > 4) {
            if (user_input.substring(0, 4).equals("done")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDelete() {
        if (user_input.length() > 6) {
            if (user_input.substring(0, 6).equals("delete")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEvent() {
        if (user_input.length() > 5) {
            if (user_input.substring(0, 5).equals("event")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDeadline() {
        if (user_input.length() > 8) {
            if (user_input.substring(0, 8).equals("deadline")) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBye() {
        if (user_input.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    private static void repeatTaskAdded() {
        System.out.println(t[num_of_goals]);
        System.out.println("Now you have " + num_of_goals + " tasks in the list.");
    }

    private static int getDeadlineIndex(String input) {
        int index = user_input.indexOf('/');
        return index;
    }

    private static void deleteTask() {
        if (isDelete()) {
            int spaceIndex = user_input.indexOf(' ');
            String deleteString = user_input.substring(spaceIndex + 1);
            int deleteIndex = Integer.parseInt(deleteString);
            System.out.println("I have deleted this task for you: ");
            System.out.println("[" + t[deleteIndex].getStatusIcon() + "] " + t[deleteIndex].getDescription() + "\n");

            if (deleteIndex == 0 && num_of_goals == 1) {
                t[0] = t[1];
            } else {
                for (int i = 0; i < t.length - 1; i++) {
                    t[i] = t[i + 1];
                }
            }
            num_of_goals--;

        }
    }

    private static String getDeadline(String user_input) {
        int timeIndex = getDeadlineIndex(user_input);
        String deadline = user_input.substring(timeIndex + 3, user_input.length());
        return deadline;
    }

    private static void plusNumOfGoals() {
        num_of_goals++;
    }

    private static void doneMessage(int taskIndex) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + t[taskIndex].getStatusIcon() + "] " + t[taskIndex].getDescription() + "\n");
    }

    private static void listTaskMsg() {
        System.out.println("Here are the tasks in your list:");
    }

    private static void enumerateTasks() {
        for (int i = 0; i < num_of_goals; i++) {
            System.out.println((i + 1) + "." + t[i + 1].toString());
        }
    }

    private static int getDoneTaskIndex(String user_input) {
        int taskIndex = Integer.parseInt(user_input.substring(DONE_START, user_input.length()));
        return taskIndex;
    }

    private static void markAsDone(String user_input) {
        int doneTaskIndex = getDoneTaskIndex(user_input);
        t[doneTaskIndex].markAsDone();
        doneMessage(doneTaskIndex);
    }

    private static void addNewTask(String user_input) {
        t[num_of_goals] = new Task(user_input);
    }

    private static void addNewDeadline(String user_input) {
        t[num_of_goals] = new Deadline(user_input.substring(DEADLINE_START, getDeadlineIndex(user_input)), getDeadline(user_input));
    }

    private static void addNewEvent(String user_input) {
        t[num_of_goals] = new Event(user_input.substring(EVENT_START, getDeadlineIndex(user_input)), getDeadline(user_input));
    }

    private static void addNewTodo(String user_input) {
        t[num_of_goals] = new Todo(user_input.substring(TODO_START, user_input.length()));
    }

    private static boolean emptyInput(String user_input, Command command) {
        switch (command) {
            case TODO:
                return (user_input.substring(TODO_START, user_input.length()).strip().equals(""));
            case EVENT:
                return user_input.substring(EVENT_START, getDeadlineIndex(user_input)).strip().equals("");
            case DEADLINE:
                return (user_input.substring(DEADLINE_START, getDeadlineIndex(user_input)).strip().equals(""));
        }
        return false;
    }

    private static boolean validEventTime(String user_input) throws InvalidEventException {
        if (user_input.substring(getDeadlineIndex(user_input), getDeadlineIndex(user_input) + 3).equals("/at")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validDeadlineTime(String user_input) throws InvalidDeadlineException {
        if (user_input.substring(getDeadlineIndex(user_input), getDeadlineIndex(user_input) + 3).equals("/by")) {
            return true;
        } else {
            return false;
        }
    }

    private static void checkValidInput(String user_input, Command command) throws EmptyInputException, InvalidEventException, InvalidDeadlineException {
        if (emptyInput(user_input, command)) {
            throw new EmptyInputException();
        }

        switch (command) {
            case EVENT:
                if (validEventTime(user_input)) {
                    break;
                } else {
                    throw new InvalidEventException();
                }
            case DEADLINE:
                if (validDeadlineTime(user_input)) {
                    break;
                } else {
                    throw new InvalidDeadlineException();
                }
        }
    }

    public static void executeCommand(String input, Command c) throws InvalidCommandException, EmptyInputException, InvalidEventException, InvalidDeadlineException {
        switch (c) {
            case LIST:
                listTaskMsg();
                enumerateTasks();
                break;
            case DONE:
                markAsDone(input);
                break;
            case TODO:
                checkValidInput(input, c);
                plusNumOfGoals();
                addNewTodo(input);
                repeatTaskAdded();
                break;
            case EVENT:
                checkValidInput(input, c);
                plusNumOfGoals();
                addNewEvent(input);
                repeatTaskAdded();
                break;
            case DEADLINE:
                checkValidInput(input, c);
                plusNumOfGoals();
                addNewDeadline(input);
                repeatTaskAdded();
                break;
            case DELETE:
                deleteTask();
                break;
            case INVALID:
                throw new InvalidCommandException();

        }
    }


    public static void main(String[] args) {
        show_welcome_msg();

        user_input = getString(in);
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
            } else if (isDeadline()) {
                command = Command.DEADLINE;
            } else if(isDelete()){
                command =Command.DELETE;
            }
            else {
                command = Command.INVALID;
            }

            try {
                executeCommand(user_input, command);
            } catch (InvalidCommandException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (EmptyInputException e) {
                System.out.println("OOPS!!! The description of a new task cannot be empty.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! You need to add time for new Event or Deadline with '/at' or '/by'!!");
            } catch (InvalidEventException e) {
                System.out.println("OOPS!!! You need to add time for new Event with keyword '/at'!!");
            } catch (InvalidDeadlineException e) {
                System.out.println("OOPS!!! You need to add time for new Deadline with keyword '/by'!!");
            }

            System.out.println();
            user_input = getString(in);
        }


        show_exit_msg();
    }
}