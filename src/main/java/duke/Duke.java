package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static final int DONE_START = 5;
    public static final int TODO_START = 5;
    public static final int EVENT_START = 6;
    public static final int DEADLINE_START = 9;

    public static Task[] tasks = new Task[100];
    public static int numTasks = 0;

    public static Scanner in = new Scanner(System.in);
    public static String input;

    public static String filepath = "data.txt";

    public static void main(String[] args) {
        try {
            autoload(filepath);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

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
            }

            autosave();

            System.out.println();
            input = getString(in);
        }


        exitMessage();

    }

    private static void autosave() {
        File f = new File(filepath);
        try {
            String data = getAllTaskListData();
            writeToFile(filepath, data);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No items to add to file");
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void processFileInput(String fileLine){
        char taskType = fileLine.charAt(1);
        char isDone = fileLine.charAt(4);
        String description;
        incrementTasks();

        switch(taskType) {
        case ('T'):
            addNewTodo("todo" + fileLine.substring(6));
            break;
        case ('D'):
            int deadlineIndex = fileLine.indexOf("(by:");
            description = fileLine.substring(6, deadlineIndex);
            String deadline = fileLine.substring(deadlineIndex+4, fileLine.length() - 1);
            addNewDeadline("deadline" + description + "/by" + deadline);
            break;
        case ('E'):
            int eventIndex = fileLine.indexOf("(at:");
            description = fileLine.substring(6, eventIndex);
            String event = fileLine.substring(eventIndex+4, fileLine.length() - 1);
            addNewEvent("event" + description + "/at" + event);
            break;
        }


        if (isDone == 'Y'){
            tasks[numTasks].markAsDone();
        }

    }

    private static void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            processFileInput(line);
        }
    }

    private static void autoload(String filepath) throws IOException {
        File f = new File(filepath);
        if (f.createNewFile()) {
            System.out.println("Welcome to Duke. Is this your first time using Duke on this machine?");
        } else {
            System.out.println("Your previous Task list from Duke has been loaded! :-)");
            readFileContents(filepath);
        }
        //boolean existingData = f.exists();
    }


    private static String getAllTaskListData() {
        String data = "";
        for (int i = 1; i <= numTasks; i++){
            data = data + (tasks[i].toString()) + "\n";
        }
        return data;
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
        case TODO:
            verifyValidInput(input, c);
            incrementTasks();
            addNewTodo(input);
            confirmNewTask();
            break;
        case EVENT:
            verifyValidInput(input, c);
            incrementTasks();
            addNewEvent(input);
            confirmNewTask();
            break;
        case DEADLINE:
            verifyValidInput(input, c);
            incrementTasks();
            addNewDeadline(input);
            confirmNewTask();
            break;
        case INVALID:
            throw new InvalidCommandException();

        }
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

    private static boolean validDeadlineTime(String input) throws InvalidEventTimeException {
        return input.substring(getTimePosition(input), getTimePosition(input) + 3).equals("/by");
    }

    private static boolean validEventTime(String input) {
        return (input.substring(getTimePosition(input), getTimePosition(input) + 3).equals("/at"));
    }


    private static boolean isEmptyInput(String input, Command c) {
        switch (c) {
        case TODO:
            return (input.substring(TODO_START, input.length()).strip().equals(""));
        case EVENT:
            return input.substring(EVENT_START, getTimePosition(input)).strip().equals("");
        case DEADLINE:
            return (input.substring(DEADLINE_START, getTimePosition(input)).strip().equals(""));
        }
        return false;
    }


    private static void markTaskAsDone(String input) {
        int completedTaskIndex = getCompletedTaskIndex(input);
        tasks[completedTaskIndex].markAsDone();
        completedTaskMessage(completedTaskIndex);
    }

    private static void addNewDeadline(String input) {

        tasks[numTasks] = new Deadline(input.substring(DEADLINE_START, getTimePosition(input)), getTime(input));
    }

    private static void addNewEvent(String input) {
        tasks[numTasks] = new Event(input.substring(EVENT_START, getTimePosition(input)), getTime(input));
    }

    private static void addNewTodo(String input) {
        tasks[numTasks] = new ToDo(input.substring(TODO_START, input.length()));
    }

    private static int getCompletedTaskIndex(String input) {
        int completedTaskIndex = Integer.parseInt(input.substring(DONE_START, input.length()));
        return completedTaskIndex;
    }

    private static void enumerateTasks() {
        for (int i = 1; i <= numTasks; i++) {
            System.out.println(i + "." + tasks[i].toString());
        }
    }

    private static void listBeginMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    private static void completedTaskMessage(int completedTaskIndex) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + tasks[completedTaskIndex].getStatusIcon() + "] " + tasks[completedTaskIndex].getDescription() + "\n");
    }

    private static void incrementTasks() {
        numTasks++;
    }

    private static int getTimePosition(String input) {
        return input.indexOf('/');
    }

    private static String getTime(String input) {
        int timePosition = getTimePosition(input);
        return input.substring(timePosition + 3, input.length());
    }

    private static void confirmNewTask() {
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks[numTasks]);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
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
