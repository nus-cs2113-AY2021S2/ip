package duke;

import java.util.Scanner;

public class Duke {

    static final int INPUT_CODE_EXIT = -1;
    static final int INPUT_CODE_DEFAULT_INVALID = 0;
    static final int INPUT_CODE_LIST = 1;
    static final int INPUT_CODE_DONE = 2;
    static final int INPUT_CODE_INVALID = 3;
    static final int INPUT_CODE_TODO = 4;
    static final int INPUT_CODE_DEADLINE = 5;
    static final int INPUT_CODE_EVENT = 6;

    public static void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

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
        printDividerLine();
        System.out.println(logo);
        System.out.println("Welcome Mushroom!");
        System.out.println("What can Mushroom Head do for you?");
        printDividerLine();
    }

    public static void printBye() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print("____________________________________________________________");
    }

    public static void taskWarningMessage(String invalidUserInput) {
        printDividerLine();
        System.out.println("Invalid command: " + invalidUserInput);
    }

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
                System.out.println("There is no task 0!");
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
                getTaskTiming(userInput);
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
                getTaskTiming(userInput);
                return validateEventCommand(userInput);
            } catch (EventCommandException e) {
                taskWarningMessage(userInput);
                System.out.println("Your format must be [ /at ]!");
                return INPUT_CODE_INVALID;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter a date or time!");
                return INPUT_CODE_INVALID;
            }
        default:
            return INPUT_CODE_DEFAULT_INVALID;
        }
    }

    private static int validateDoneCommand(String[] words) throws DoneCommandException {
        if (words.length == 1 || Integer.parseInt(words[1]) < 1) {
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

    private static void performAction(int commandCode, String userInput, Task taskList) {
        String taskDescription;
        String taskTiming;
        // No Fallthrough intended in this switch statement
        switch (commandCode) {
        case INPUT_CODE_EXIT:
            printBye();
            break;
        case INPUT_CODE_LIST:
            printDividerLine();
            taskList.listTask();
            printDividerLine();
            break;
        case INPUT_CODE_DONE:
            printDividerLine();
            taskList.markDone(getDoneIndexFromUserInput(userInput));
            printDividerLine();
            break;
        case INPUT_CODE_TODO:
            printDividerLine();
            taskDescription = getTaskDescription(userInput);
            Task todoTask = new Todo(taskDescription);
            todoTask.addTask();
            printDividerLine();
            break;
        case INPUT_CODE_DEADLINE:
            printDividerLine();
            taskDescription = getTaskDescription(userInput);
            taskTiming = getTaskTiming(userInput);
            Task deadlineTask = new Deadline(taskDescription, taskTiming);
            deadlineTask.addTask();
            printDividerLine();
            break;
        case INPUT_CODE_EVENT:
            printDividerLine();
            taskDescription = getTaskDescription(userInput);
            taskTiming = getTaskTiming(userInput);
            Task eventTask = new Event(taskDescription, taskTiming);
            eventTask.addTask();
            printDividerLine();
            break;
        case INPUT_CODE_INVALID:
            System.out.println("Please try again!");
            printDividerLine();
            break;
        default:
            printDividerLine();
            System.out.println("Mushroom head could not recognize your command code!");
            System.out.println("Please try again!");
            printDividerLine();
            break;
        }
    }

    private static String getTaskTiming(String userInput) {
        String unfilteredTaskTiming = removeCommandCode(userInput);
        String[] taskTiming = unfilteredTaskTiming.split("/", 2);
        taskTiming = taskTiming[1].split(" ", 2);
        return taskTiming[1];
    }

    private static String getTaskDescription(String userInput) {
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

    private static int getDoneIndexFromUserInput(String userInput) {
        String[] words = userInput.split(" ");
        int indexResult = Integer.parseInt(words[1]);
        return indexResult;
    }

    public static void main(String[] args) {
        int commandCode;
        String userInput;
        Scanner in = new Scanner(System.in);
        Task taskList = new Task();

        printHelloStatement();
        do {
            userInput = in.nextLine();
            commandCode = getCommandCode(userInput);
            performAction(commandCode, userInput, taskList);
        } while (commandCode != INPUT_CODE_EXIT);
    }
}
