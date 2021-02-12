package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Event;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

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
        tasks.add(task);
        showAddTaskMessage(task);
    }

    /* Create new todo task */
    private static void createTodoTask(String parameter) {
        Task task = new Todo(parameter);
        addTask(task);
    }

    /* Create new event task */
    private static void createEventTask(String parameter) throws DukeException {
        String[] eventParameters = splitParameter(parameter, "/at ");
        if (validateNumberOfInputParameters(eventParameters, 2)) {
            String description = eventParameters[0];
            String at = eventParameters[1];
            Task task = new Event(description, at);
            addTask(task);
        }
    }

    /* Create new deadline task */
    private static void createDeadlineTask(String parameter) throws DukeException {
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
    private static boolean validateNumberOfInputParameters(String[] parameters, int number)
        throws DukeException {
        if (parameters.length < number) {
            throw new DukeException("The parameter format for this command is incorrect!");
        }

        return true;
    }

    /* Show add new task message */
    private static void showAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /* Print all tasks */
    private static void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    /* Mark specified task as done */
    private static void markAsDone(String index) throws DukeException {
        if (validateIndexNumber(index)) {
            int indexNumber = Integer.parseInt(index);
            Task task = tasks.get(indexNumber - 1);
            task.markAsDone(true);
            showMarkAsDoneMessage(task);
        }
    }

    /* Validate index number */
    private static boolean validateIndexNumber(String index) throws DukeException {
        if (checkIsNumber(index)) {
            int indexNumber = Integer.parseInt(index);
            checkIsInRange(indexNumber);
        }

        return true;
    }

    /* Validate index range */
    private static void checkIsInRange(int index) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("The index number is out of range :-(");
        }
    }

    /* Validate whether input is a number */
    private static boolean checkIsNumber(String input) throws DukeException {
        checkIsEmpty(input);

        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return true;
        } else {
            throw new DukeException("The parameter must be a number :-(");
        }
    }

    /* Validate whether input is empty */
    private static void checkIsEmpty(String input) throws DukeException {
        if (input == "") {
            throw new DukeException("The index number cannot be empty :-(");
        }
    }

    /* Show mark task as done message */
    private static void showMarkAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /* Print exception error message */
    private static void printErrorMessage(DukeException de) {
        System.out.println(de.getMessage());
    }

    /* Execute command based on input from parseCommand */
    private static void executeCommand(String command, String parameter) throws DukeException {
        switch (command) {
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
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /* Parse input into command command */
    private static String[] parseCommand(String input) {
        int firstSpacePosition = input.indexOf(" ");
        String lowercaseCommand = input;
        String parameter = "";

        if (firstSpacePosition > 0) {
            lowercaseCommand = input.substring(0, firstSpacePosition).toLowerCase();
            parameter = input.substring(firstSpacePosition + 1);
        }

        return new String[]{lowercaseCommand, parameter};
    }

    /* Interacts with user until bye is met */
    private static void interactWithUser() {
        Scanner line = new Scanner(System.in);

        while (line.hasNextLine()) {
            String input = line.nextLine();
            showDivider();
            if (input.toLowerCase().equals("bye")) {
                break;
            }

            String[] parsedCommand = parseCommand(input);

            try {
                executeCommand(parsedCommand[0], parsedCommand[1]);
            } catch (DukeException de) {
                printErrorMessage(de);
            }
            showDivider();
        }
    }

    public static void main(String[] args) {
        showGreeting();
        interactWithUser();
        showExit();
    }
}
