import exceptions.EmptyDescriptionException;
import exceptions.EmptyNumberException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final ArrayList<Task> taskList = new ArrayList<>();

    // These are the prefix strings to define the data type of a command parameter
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_DEADLINE_WORD = "deadline";
    private static final String COMMAND_LIST_WORD = "list";
    private static final String COMMAND_EXIT_WORD = "bye";
    private static final String COMMAND_DONE_WORD = "done";

    // error messages
    private static final String MESSAGE_DESCRIPTION_EMPTY_TODO = "The description of a todo cannot be empty.";
    private static final String MESSAGE_DESCRIPTION_EMPTY_DEADLINE = "The description of a deadline cannot be empty.";
    private static final String MESSAGE_DESCRIPTION_EMPTY_EVENT = "The description of a event cannot be empty.";
    private static final String MESSAGE_DESCRIPTION_EMPTY_DONE = "The number of the task to be marked as done cannot be empty.";
    private static final String MESSAGE_INVALID_COMMAND = "I'm sorry, but I don't know what that means.";
    private static final String MESSAGE_INVALID_DEADLINE = "No deadline provided or wrong splitter.";
    private static final String MESSAGE_INVALID_EVENT = "No event date and time provided or wrong splitter.";
    private static final String MESSAGE_INVALID_COMMAND_DONE = "Number not provided for the task to be marked as done.";
    private static final String MESSAGE_INVALID_NUMBER_DONE = "task.Task number does not exist.";
    private static final String MESSAGE_SAVE_FILE_LOADED = "Save file loaded.";
    private static final String MESSAGE_SAVE_FILE_CREATED = "Save file created.";
    private static final String MESSAGE_SAVE_FILE_SAVED = "File saved.";
    private static final String MESSAGE_SAVE_FILE_ERROR = "Error while saving file.";

    /**
     * Main entry point of the application.
     * Initializes the application and starts the interaction with the user.
     */
    public static void main(String[] args) {
        showLogo();
        showGreeting();
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            // do nothing since no file to load
        }
        while(true) {
            String userCommand = getUserInput();
            executeCommand(userCommand);
        }
    }

    private static void loadFile() throws FileNotFoundException {
        printHorizontalLine();
        File f = new File("duke.txt");
        if (!f.exists()) { // if file does not exist
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            final String input = s.nextLine();
            final String[] commandDoneAndTypeAndParams = splitSaveFileInput(input);
            final String commandDone = commandDoneAndTypeAndParams[0];
            final String commandType = commandDoneAndTypeAndParams[1];
            final String commandArgs = commandDoneAndTypeAndParams[2];
            switch (commandType) {
            case COMMAND_TODO_WORD:
                addTodo(commandDone, commandArgs);
                break;
            case COMMAND_DEADLINE_WORD:
                addDeadline(commandDone, commandArgs);
                break;
            case COMMAND_EVENT_WORD:
                addEvent(commandDone, commandArgs);
                break;
            }
        }
        System.out.println(MESSAGE_SAVE_FILE_LOADED);
        printHorizontalLine();
    }

    /**
     * Splits save file input into command done, command word and command arguments string
     * @param rawUserInput
     * @return size 3 array; [command done, command type, command arguments]
     *
     */
    private static String[] splitSaveFileInput(String rawUserInput) {
        return rawUserInput.trim().split(" ", 3);
    }

    /**
     * add todo from save file
     * @param commandArgs input of todo task
     */
    private static void addTodo(String commandDone, String commandArgs) {
        Task task = new Todo(commandArgs);
        if (commandDone.equals("1")) {
            task.markAsDone();
        }
        taskList.add(task);
    }

    /**
     * add deadline from save file
     * @param commandArgs input of deadline task
     */
    private static void addDeadline(String commandDone, String commandArgs) {
        final String[] descriptionAndDeadline = splitDescriptionAndDeadline(commandArgs);
        Task task = new Deadline(descriptionAndDeadline[0], descriptionAndDeadline[1]);
        if (commandDone.equals("1")) {
            task.markAsDone();
        }
        taskList.add(task);
    }

    /**
     * add event from save file
     * @param commandArgs
     */
    private static void addEvent(String commandDone, String commandArgs) {
        final String[] descriptionAndTime = splitDescriptionAndTime(commandArgs);
        Task task = new Event(descriptionAndTime[0], descriptionAndTime[1]);
        if (commandDone.equals("1")) {
            task.markAsDone();
        }
        taskList.add(task);
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void showLogo() {
        // logo/loading
        printHorizontalLine();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }


    private static void showGreeting() {
        // greeting
        printHorizontalLine();
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greeting);
        printHorizontalLine();
    }

    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        return inputLine;
    }

    /**
     * Executes the command as specified by the user
     *
     * @param userInputString raw input from user
     * prints out the interaction between the program and the user's inputs
     */
    private static void executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case COMMAND_TODO_WORD:
            try {
                executeTodo(commandArgs);
            } catch (EmptyDescriptionException e) {
                System.out.println(MESSAGE_DESCRIPTION_EMPTY_TODO);
            }
            break;
        case COMMAND_DEADLINE_WORD:
            try {
                executeDeadline(commandArgs);
            } catch (EmptyDescriptionException e) {
                System.out.println(MESSAGE_DESCRIPTION_EMPTY_DEADLINE);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(MESSAGE_INVALID_DEADLINE);
            }
            break;
        case COMMAND_EVENT_WORD:
            try {
                executeEvent(commandArgs);
            } catch (EmptyDescriptionException e) {
                System.out.println(MESSAGE_DESCRIPTION_EMPTY_EVENT);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(MESSAGE_INVALID_EVENT);
            }
            break;
        case COMMAND_LIST_WORD:
            executeList();
            break;
        case COMMAND_DONE_WORD:
            try {
                executeDone(commandArgs);
            } catch (EmptyNumberException e) {
                System.out.println(MESSAGE_DESCRIPTION_EMPTY_DONE);
            } catch (NumberFormatException e) {
                System.out.println(MESSAGE_INVALID_COMMAND_DONE);
            } catch (NullPointerException e) {
                System.out.println(MESSAGE_INVALID_NUMBER_DONE);
            }
            break;
        case COMMAND_EXIT_WORD:
            executeExit();
            break;
        default:
            System.out.println(MESSAGE_INVALID_COMMAND);
            break;
        }
    }

    /**
     * Splits raw user input into command word and command arguments string
     * @param rawUserInput
     * @return size 2 array; first elemetn is the command type and second element is the arguments string
     */
    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split = rawUserInput.trim().split(" ", 2);
        return split.length == 2 ? split : new String[] { split[0] , ""};
    }

    /**
     * Add task under task.Todo class and feedback display message when task.Todo task added
     * @param commandArgs Description of the task
     */
    private static void executeTodo(String commandArgs) throws EmptyDescriptionException{
        final String description = commandArgs;
        if (description.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            Task task = new Todo(description);
            taskList.add(task);
            getMessageForTodo(task);
        }
    }

    private static void getMessageForTodo(Task task) {
        printHorizontalLine();
        showAddTask();
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printHorizontalLine();
    }

    public static void showAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Add task under task.Deadline class and feedback display message when task.Deadline task added
     * @param commandArgs description and deadline of the task
     */
    private static void executeDeadline(String commandArgs) throws EmptyDescriptionException{
        if (commandArgs.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            final String[] descriptionAndDeadline = splitDescriptionAndDeadline(commandArgs);
            Task task = new Deadline(descriptionAndDeadline[0], descriptionAndDeadline[1]);
            taskList.add(task);
            getMessageForDeadline(task);
        }
    }


    private static String[] splitDescriptionAndDeadline(String commandArgs) {
        final String[] split = commandArgs.split(" /by ");
        return split;
    }

    private static void getMessageForDeadline(Task task) {
        printHorizontalLine();
        showAddTask();
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Add task under task.Event class and feedback display message when task.Event task added
     * @param commandArgs
     */
    private static void executeEvent(String commandArgs) throws EmptyDescriptionException {
        if (commandArgs.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            final String[] descriptionAndTime = splitDescriptionAndTime(commandArgs);
            Task task = new Event(descriptionAndTime[0], descriptionAndTime[1]);
            taskList.add(task);
            getMessageForEvent(task);
        }
    }

    private static String[] splitDescriptionAndTime(String commandArgs) {
        final String[] split = commandArgs.split(" /at ");
        return split;
    }

    private static void getMessageForEvent(Task task) {
        printHorizontalLine();
        showAddTask();
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        printHorizontalLine();
    }

    /**
     * Generate feedback for list of task and task status
     */
    private static void executeList() {
        printHorizontalLine();
        showTasksList(taskList);
        printHorizontalLine();
    }

    private static void showTasksList(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        int taskCount = 1;
        for (Task task : taskList) {
            System.out.println(taskCount + "." + task.toString());
            ++taskCount;
        }
    }

    /**
     * Mark task as done and display message when status of task is changed
     * @param commandArgs
     */
    private static void executeDone(String commandArgs) throws EmptyNumberException {
        if (commandArgs.equals("")) {
            throw new EmptyNumberException();
        }
        int taskIndex = Integer.parseInt(commandArgs) - 1; // minus 1 to adhere to array indexing
        Task taskToBeDone = taskList.get(taskIndex);
        taskToBeDone.markAsDone();
        showTaskDone(taskToBeDone);
    }

    private static void showTaskDone(Task task) {
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.toString());
        printHorizontalLine();
    }

    /**
     * exit the program
     */
    private static void executeExit() {
        try {
            saveFile();
        } catch (IOException e) {
            System.out.println(MESSAGE_SAVE_FILE_ERROR);
        }
        printHorizontalLine();
        showGoodbye();
        System.exit(0);
    }

    private static void saveFile() throws IOException {
        printHorizontalLine();
        File f = new File("duke.txt");
        if (f.createNewFile()) {
            System.out.println(MESSAGE_SAVE_FILE_CREATED);
        }
        FileWriter fw = new FileWriter("duke.txt");
        for (Task task : taskList) {
            fw.write(task.saveToFile());
        }
        fw.close();
        System.out.println(MESSAGE_SAVE_FILE_SAVED);
    }

    private static void showGoodbye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

}
