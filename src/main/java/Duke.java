import error.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // Commands code constants
    static final int INPUT_CODE_EXIT = -1;
    static final int INPUT_CODE_DEFAULT_INVALID = 0;
    static final int INPUT_CODE_LIST = 1;
    static final int INPUT_CODE_DONE = 2;
    static final int INPUT_CODE_INVALID = 3;
    static final int INPUT_CODE_TODO = 4;
    static final int INPUT_CODE_DEADLINE = 5;
    static final int INPUT_CODE_EVENT = 6;
    static final int INPUT_CODE_DELETE = 7;

    // String message constant
    static final String DIVIDER_LINE = "____________________________________________________________";
    static final String LIST_STARTING_MESSAGE = "Below are all your list of mushroom points:";

    // Variable Used in this program
    private static ArrayList<Task> taskArrayList = new ArrayList<>();
    private static int taskCount = 0;

    public static void printHelloStatement() {
        String logo = "           ____\n" +
                "       _.-'78o `\"`--._\n" +
                "   ,o888o.  .o888o,   ''-.\n" +
                " ,88888P  `78888P..______.]\n" +
                "/_..__..----\"\"        __.'\n" +
                "`-._       /\"\"| _..-''\n" +
                "    \"`-----\\  `\\\n" +
                "            |   ;.-\"\"--..\n" +
                "            | ,8o.  o88. `.\n" +
                "            `;888P  `788P  :\n" +
                "      .o\"\"-.|`-._         ./\n" +
                "     J88 _.-/    \";\"-P----'\n" +
                "     `--'\\`|     /  /\n" +
                "         | /     |  |\n" +
                "         \\|     /   |akn\n" +
                "          `-----`---'\n";
        System.out.println(DIVIDER_LINE);
        System.out.println(logo);
        System.out.println("Welcome Mushroom!");
        System.out.println("What can Mushroom Head do for you?");
        System.out.println(DIVIDER_LINE);
    }

    public static void printBye() {
        System.out.println(DIVIDER_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER_LINE);
    }

    public static void taskWarningMessage(String invalidUserInput) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Invalid command: " + invalidUserInput);
    }

    // Get user command code from input
    private static int getCommandCode(String userInput) {
        String[] words = userInput.split(" ");
        try {
            words[0].toLowerCase();
        } catch (ArrayIndexOutOfBoundsException e) {
            return INPUT_CODE_DEFAULT_INVALID;
        }

        // No Fallthrough intended in this switch statement
        switch (words[0].toLowerCase()) {
        case "bye":
            return INPUT_CODE_EXIT;
        case "list":
            return INPUT_CODE_LIST;
        case "done":
            try {
                return validateDoneCommand(words);
            } catch (DoneCommandException e) {
                taskWarningMessage(userInput);
                System.out.println("There is no task smaller than 1!");
                return INPUT_CODE_INVALID;
            } catch (ArrayIndexOutOfBoundsException e) {
                taskWarningMessage(userInput);
                System.out.println("You need a task number behind done command!!");
                return INPUT_CODE_INVALID;
            }
        case "todo":
            try {
                return validateTodoCommand(words);
            } catch (TodoCommandException e) {
                taskWarningMessage(userInput);
                System.out.println("You need an task behind!");
                return INPUT_CODE_INVALID;
            }
        case "deadline":
            try {
                extractTaskTiming(userInput);
                return validateDeadlineCommand(userInput);
            } catch (DeadlineCommandException e) {
                taskWarningMessage(userInput);
                System.out.println("Your format must be [ /by ]!");
                return INPUT_CODE_INVALID;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter a date or time!");
                return INPUT_CODE_INVALID;
            }
        case "event":
            try {
                extractTaskTiming(userInput);
                return validateEventCommand(userInput);
            } catch (EventCommandException e) {
                taskWarningMessage(userInput);
                System.out.println("Your format must be [ /at ]!");
                return INPUT_CODE_INVALID;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter a date or time!");
                return INPUT_CODE_INVALID;
            }
        case "delete":
            try {
                return validateDeleteCommand(words);
            } catch (DeleteCommandException e) {
                taskWarningMessage(userInput);
                System.out.println("You can not delete a task smaller than 1!");
                return INPUT_CODE_INVALID;
            } catch (ArrayIndexOutOfBoundsException e) {
                taskWarningMessage(userInput);
                System.out.println("You need a task number behind delete command!!");
                return INPUT_CODE_INVALID;
            }
        default:
            return INPUT_CODE_DEFAULT_INVALID;
        }
    }

    private static int validateDoneCommand(String[] words) throws DoneCommandException {
        if (Integer.parseInt(words[1]) < 1 || words.length == 1) {
            throw new DoneCommandException();
        } else {
            return INPUT_CODE_DONE;
        }
    }

    private static int validateTodoCommand(String[] words) throws TodoCommandException {
        if (words.length <= 1) {
            throw new TodoCommandException();
        } else {
            return INPUT_CODE_TODO;
        }
    }

    private static int validateDeadlineCommand(String userInput) throws DeadlineCommandException {
        if (userInput.contains(" /by ")) {
            return INPUT_CODE_DEADLINE;
        } else {
            throw new DeadlineCommandException();
        }
    }

    private static int validateEventCommand(String userInput) throws EventCommandException {
        if (userInput.contains(" /at ")) {
            return INPUT_CODE_EVENT;
        } else {
            throw new EventCommandException();
        }
    }

    private static int validateDeleteCommand(String[] words) throws DeleteCommandException {
        if (Integer.parseInt(words[1]) < 1 || words.length == 1) {
            throw new DeleteCommandException();
        } else {
            return INPUT_CODE_DELETE;
        }
    }

    // Perform the action give the command
    private static void performAction(int commandCode, String userInput) {
        String taskDescription;
        String taskTiming;
        // No Fallthrough intended in this switch statement
        switch (commandCode) {
        case INPUT_CODE_EXIT:
            printBye();
            break;
        case INPUT_CODE_LIST:
            System.out.println(DIVIDER_LINE);
            printEntireCollection();
            System.out.println(DIVIDER_LINE);
            break;
        case INPUT_CODE_DONE:
            System.out.println(DIVIDER_LINE);
            markTaskAsDone(getIndexFromUserInput(userInput));
            System.out.println(DIVIDER_LINE);
            break;
        case INPUT_CODE_TODO:
            System.out.println(DIVIDER_LINE);
            taskDescription = extractTaskDescription(userInput);
            taskArrayList.add(new Todo(taskDescription));
            printAddedTask(taskCount);
            taskCount++;
            System.out.println(DIVIDER_LINE);
            break;
        case INPUT_CODE_DEADLINE:
            System.out.println(DIVIDER_LINE);
            taskDescription = extractTaskDescription(userInput);
            taskTiming = extractTaskTiming(userInput);
            taskArrayList.add(new Deadline(taskDescription, taskTiming));
            printAddedTask(taskCount);
            taskCount++;
            System.out.println(DIVIDER_LINE);
            break;
        case INPUT_CODE_EVENT:
            System.out.println(DIVIDER_LINE);
            taskDescription = extractTaskDescription(userInput);
            taskTiming = extractTaskTiming(userInput);
            taskArrayList.add(new Event(taskDescription, taskTiming));
            printAddedTask(taskCount);
            taskCount++;
            System.out.println(DIVIDER_LINE);
            break;
        case INPUT_CODE_DELETE:
            System.out.println(DIVIDER_LINE);
            deleteTask(getIndexFromUserInput(userInput));
            System.out.println(DIVIDER_LINE);
            break;
        case INPUT_CODE_INVALID:
            System.out.println("Please try again!");
            System.out.println(DIVIDER_LINE);
            break;
        default:
            System.out.println(DIVIDER_LINE);
            System.out.println("Mushroom head could not recognize your command code!");
            System.out.println("Please try again!");
            System.out.println(DIVIDER_LINE);
            break;
        }
    }

    // Print information
    private static void printEntireCollection() {
        if (taskCount == 0) {
            System.out.println("You have no item in Mushroom Head list!");
        } else {
            System.out.println(LIST_STARTING_MESSAGE);
            for (int i = 0; i < taskCount; i++) {
                System.out.print(i + 1 + ".");
                printTaskDetails(i);
            }
        }
    }

    private static void printAddedTask(int index) {
        System.out.println("Got it. I've added this task:");
        printTaskDetails(index);
        System.out.println("Now you have " + (index + 1) + " tasks in the list.");
    }

    private static void printTaskDetails(int index) {
        printTaskSymbol(index);
        printTaskCompletionStatus(index);
        System.out.print(" ");
        printTaskDescription(index);
        System.out.print(taskArrayList.get(index).getTaskTiming());
        System.out.println();
    }

    private static void printTaskSymbol(int index) {
        System.out.print("[");
        System.out.print(taskArrayList.get(index).getTaskSymbol());
        System.out.print("]");
    }

    private static void printTaskCompletionStatus(int index) {
        System.out.print("[");
        System.out.print(taskArrayList.get(index).getTaskCompletionStatus());
        System.out.print("]");
    }

    private static void printTaskDescription(int index) {
        System.out.print(taskArrayList.get(index).getTaskDescription());
    }

    // Extraction of task information
    private static String extractTaskTiming(String userInput) {
        String unfilteredTaskTiming = removeCommandCode(userInput);
        String[] taskTiming = unfilteredTaskTiming.split("/", 2);
        taskTiming = taskTiming[1].split(" ", 2);
        return taskTiming[1];
    }

    private static String extractTaskDescription(String userInput) {
        String taskDescription = removeCommandCode(userInput);
        if (taskDescription.contains("/")) {
            taskDescription = removeDateAndTime(taskDescription);
        }
        return taskDescription;
    }

    private static String removeDateAndTime(String unfilteredDescription) {
        String[] unfilteredDescriptionArray= unfilteredDescription.split("/", 2);
        return unfilteredDescriptionArray[0];
    }

    private static String removeCommandCode(String userInput) {
        String[] userInputArray= userInput.split(" ", 2);
        return userInputArray[1];
    }

    private static int getIndexFromUserInput(String userInput) {
        String[] words = userInput.split(" ");
        int indexResult = Integer.parseInt(words[1]);
        return indexResult;
    }

    // Marking the task as completed
    private static void markTaskAsDone(int indexFromUserInput) {
        if (indexFromUserInput > taskCount) {
            System.out.println("There is no task number " + indexFromUserInput + " to mark done.");
            System.out.println("Please try again!");
        } else {
            if (taskArrayList.get(indexFromUserInput - 1).isDone()) {
                System.out.println("You have already completed this task.");
            } else {
                taskArrayList.get(indexFromUserInput - 1).setTaskAsDone();
                System.out.println("Nice! I've marked the task as done:");
                printTaskDetails(indexFromUserInput - 1);
            }
        }
    }

    // Deleting the task
    private static void deleteTask(int indexFromUserInput) {
        if (indexFromUserInput > taskCount) {
            System.out.println("There is no task number " + indexFromUserInput + " to delete.");
            System.out.println("Please try again!");
        } else {
            System.out.println("Noted. I will remove this task: ");
            printTaskDetails(indexFromUserInput - 1);
            taskArrayList.remove(indexFromUserInput - 1);
            taskCount--;
            System.out.println("Now you have " + taskCount + " tasks in the list.");
        }
    }

    public static void main(String[] args) {
        int commandCode;
        String userInput;
        Scanner in = new Scanner(System.in);

        printHelloStatement();
        do {
            userInput = in.nextLine();
            commandCode = getCommandCode(userInput);
            performAction(commandCode, userInput);
        } while (commandCode != INPUT_CODE_EXIT);
    }
}
