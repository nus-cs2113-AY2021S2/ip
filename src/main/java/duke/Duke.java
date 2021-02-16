package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final int DONE_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;
    public static final int DELETE_START = 7;
    public static final int NEW_TASK_INDEX = 6;

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int maxTaskIndex = 0;

    public static Scanner in = new Scanner(System.in);
    public static String input;

    public static String filepath = "data.txt";

    public static void main(String[] args) {
        try {
            loadData(filepath);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        welcomeMessage();


        input = getInput(in);
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

            saveData();

            System.out.println();
            input = getInput(in);
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

    private static void loadData(String filepath) throws IOException {
        File f = new File(filepath);
        if (f.createNewFile()) {
            System.out.println("Welcome to Duke. Is this your first time using Duke on this machine?");
        } else {
            System.out.println("Your previous Task list from Duke has been loaded! :-)");
            processFileContents(filepath);
        }
    }

    private static void saveData() {
        try {
            String data = getAllTaskListData();
            writeToFile(filepath, data);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No items to add to file");
        }
    }

    private static void processFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            processFileInputLines(line);
        }
    }

    private static void processFileInputLines(String fileLine) {
        char taskType = getTaskType(fileLine);
        char isDone = getDone(fileLine);

        incrementTasks();
        addNewTaskFromFile(fileLine, taskType);
        markCompletedTaskFromFile(isDone);

    }

    private static void markCompletedTaskFromFile(char isDone) {
        if (isDone == 'Y') {
            tasks.get(maxTaskIndex-1).markAsDone();
        }
    }

    private static void addNewTaskFromFile(String fileLine, char taskType) {
        String description;
        switch (taskType) {
        case ('T'):
            addNewTodo("todo" + fileLine.substring(NEW_TASK_INDEX));
            break;
        case ('D'):
            int deadlineIndex = fileLine.indexOf("(by:");
            description = fileLine.substring(NEW_TASK_INDEX, deadlineIndex);
            String deadline = getTimeFromFile(fileLine, deadlineIndex);
            addNewDeadline("deadline" + description + "/by" + deadline);
            break;
        case ('E'):
            int eventIndex = fileLine.indexOf("(at:");
            description = fileLine.substring(NEW_TASK_INDEX, eventIndex);
            String event = getTimeFromFile(fileLine, eventIndex);
            addNewEvent("event" + description + "/at" + event);
            break;
        }
    }

    private static String getTimeFromFile(String fileLine, int timeIndex) {
        return fileLine.substring(timeIndex + 4, fileLine.length() - 1);
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static String getAllTaskListData() {
        String data = "";
        for (int i = 0; i < maxTaskIndex; i++) {
            data = data + (tasks.get(i).toString()) + "\n";
        }
        return data;
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

    private static void deleteTask(String input) {
        int taskNumberToDelete = (Integer.parseInt(input.substring(DELETE_START).strip()) - 1);
        deletedTaskMessage(taskNumberToDelete);
        tasks.remove(taskNumberToDelete);
    }

    private static void deletedTaskMessage(int deletedTaskIndex) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(deletedTaskIndex));
        System.out.println("Now you have " + (maxTaskIndex - 1) + " tasks in the list." + "\n");
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
            case TODO -> (input.substring(TODO_START).strip().equals(""));
            case EVENT -> input.substring(EVENT_START, getTimePosition(input)).strip().equals("");
            case DEADLINE -> (input.substring(DEADLINE_START, getTimePosition(input)).strip().equals(""));
            default -> false;
        };
    }


    private static char getTaskType(String fileLine) {
        return fileLine.charAt(1);
    }

    private static char getDone(String fileLine) {
        return fileLine.charAt(4);
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
        tasks.add(new ToDo(input.substring(TODO_START)));
    }

    private static int getTimePosition(String input) {
        return input.indexOf('/');
    }

    private static String getTime(String input) {
        int timePosition = getTimePosition(input);
        return input.substring(timePosition + 3);
    }

    private static int getCompletedTaskIndex(String input) {
        return (Integer.parseInt(input.substring(DONE_START)) - 1);
    }

    private static void completedTaskMessage(int completedTaskIndex) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + tasks.get(completedTaskIndex).getStatusIcon() + "] " + tasks.get(completedTaskIndex).getDescription() + "\n");
    }

    private static void confirmNewTask() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(maxTaskIndex));

        int numTasks = getNumTasks();
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    private static int getNumTasks() {
        return maxTaskIndex + 1;
    }

    private static void incrementTasks() {
        maxTaskIndex++;
    }

    private static void decrementTasks() {
        maxTaskIndex--;
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

    private static String getInput(Scanner in) {
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
