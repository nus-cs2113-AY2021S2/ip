import Task.Task;
import Task.Deadline;
import Task.Todo;
import Task.Event;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    static int MAX_CAPACITY = 100;
    static Task[] tasks = new Task[MAX_CAPACITY];
    static int taskCount = 0;

    /* Show divider */
    private static void showDivider() {
        System.out.println("---------------------------------------------------------------");
    }

    /* Show greeting message */
    private static void showGreeting() {
        showDivider();
        System.out.println("Hello! I'm Duke :)");
        System.out.println("What can I do for you?");
        showDivider();
    }

    /* Show exit message */
    private static void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
        showDivider();
    }

    /* Add new task to tasks array */
    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
        showAddTaskMessage(task);
    }

    /* Create new todo task */
    private static void createTodoTask(String parameter) {
        Task task = new Todo(parameter);
        addTask(task);
    }

    /* Create new event task */
    private static void createEventTask(String parameter) {
        String[] eventParameters = splitParameter(parameter, "/at ");
        if (validateNumberOfInputParameters(eventParameters, 2)) {
            String description = eventParameters[0];
            String at = eventParameters[1];
            Task task = new Event(description, at);
            addTask(task);
        }
    }

    /* Create new deadline task */
    private static void createDeadlineTask(String parameter) {
        String[] deadlineParameters = splitParameter(parameter, "/by ");
        if (validateNumberOfInputParameters(deadlineParameters, 2)) {
            String description = deadlineParameters[0];
            String by = deadlineParameters[1];
            Task task = new Deadline(description, by);
            addTask(task);
        }
    }

    /* Split parameter string into array of parameters */
    private static String[] splitParameter(String parameter, String delimiter) {
        String[] parameters = parameter.split(delimiter);
        return parameters;
    }

    /* Validate number of input parameters */
    private static boolean validateNumberOfInputParameters(String[] parameters, int number) {
        if (parameters.length < number) {
            showInvalidInputFormatError();
            return false;
        } else {
            return true;
        }
    }

    /* Show add new task message */
    private static void showAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /* Print all tasks */
    private static void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }

    /* Mark specified task as done */
    private static void markAsDone(String index) {
        if (validateIndexNumber(index)) {
            int indexNumber = Integer.parseInt(index);
            Task task = tasks[indexNumber - 1];
            task.markAsDone(true);
            showMarkAsDoneMessage(task);
        }
    }

    /* Validate index number */
    private static boolean validateIndexNumber(String index) {
        boolean isNumber = checkIsNumber(index);
        boolean isInRange = false;
        int indexNumber = 0;
        boolean isValidIndexNumber = false;

        if (isNumber) {
            indexNumber = Integer.parseInt(index);
            isInRange = checkIsInRange(indexNumber);
        } else {
            showInvalidInputFormatError();
            return isValidIndexNumber;
        }

        if (isInRange) {
            isValidIndexNumber = true;
        } else {
            showIndexOutOfBoundError();
        }

        return isValidIndexNumber;
    }

    /* Validate index range */
    private static boolean checkIsInRange(int index) {
        return index >= 0 && index <= taskCount;
    }

    /* Validate whether input is a number */
    private static boolean checkIsNumber(String input) {
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        return matcher.find();
    }

    /* Show mark task as done message */
    private static void showMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /* Show invalid input format error */
    private static void showInvalidInputFormatError() {
        System.out.println("Invalid input format. Please try again with correct format.");
    }

    /* Show invalid index out of bound error */
    private static void showIndexOutOfBoundError() {
        System.out.println("Invalid index number. Please enter the correct index number.");
    }

    /* Show invalid command error */
    private static void showInvalidCommandError() {
        System.out.println("Invalid command. Please try again with valid command.");
    }

    /* Parse input into command command */
    private static void parseCommand(String input) {
        int firstSpacePosition = input.indexOf(" ");
        String lowercaseCommand = input;
        String parameter = "";

        if (firstSpacePosition > 0) {
            lowercaseCommand = input.substring(0, firstSpacePosition).toLowerCase();
            parameter = input.substring(firstSpacePosition + 1);
        }

        executeCommand(lowercaseCommand, parameter);
    }

    /* Execute command based on input from parseCommand */
    private static void executeCommand(String command, String parameter) {
        switch(command) {
        case "list":
            showTasks();
            break;
        case "done":
            markAsDone(parameter);
            break;
        case "todo":
            createTodoTask(parameter);
            break;
        case "event":
            createEventTask(parameter);
            break;
        case "deadline":
            createDeadlineTask(parameter);
            break;
        default:
            showInvalidCommandError();
        }
    }

    public static void main(String[] args) {
        showGreeting();

        Scanner line = new Scanner(System.in);

        while (line.hasNextLine()) {
            String input = line.nextLine();
            showDivider();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            parseCommand(input);
            showDivider();
        }

        showExit();
    }
}
