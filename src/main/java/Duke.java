import java.util.Scanner;

public class Duke {

    static final int INPUT_CODE_EXIT = -1;
    static final int INPUT_CODE_DEFAULT = 0;
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
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
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

    private static int getCommandCode(String userCommand) {
        String[] words = userCommand.split(" ");

        // No Fallthrough intended in this switch statement
        switch (words[0].toLowerCase()) {
        case "bye":
            return INPUT_CODE_EXIT;
        case "list":
            return INPUT_CODE_LIST;
        case "done":
            return validateDoneCommand(words);
        case "todo":
            return validateTodoCommand(words);
        case "deadline":
            return validateDeadlineCommand(userCommand);
        case "event":
            return validateEventCommand(userCommand);
        default:
            return INPUT_CODE_DEFAULT;
        }
    }

    private static int validateEventCommand(String userCommand) {
        try {
            if (userCommand.contains("/")) {
                return INPUT_CODE_EVENT;
            } else {
                return INPUT_CODE_INVALID;
            }
        } catch(NumberFormatException e){
            return INPUT_CODE_INVALID;
        }
    }

    private static int validateDeadlineCommand(String userCommand) {
        try {
            if (userCommand.contains("/")) {
                return INPUT_CODE_DEADLINE;
            } else {
                return INPUT_CODE_INVALID;
            }
        } catch(NumberFormatException e){
            return INPUT_CODE_INVALID;
        }
    }

    private static int validateTodoCommand(String[] words) {
        try {
            if (words.length <= 1) {
                return INPUT_CODE_INVALID;
            } else {
                return INPUT_CODE_TODO;
            }
        } catch(NumberFormatException e){
            return INPUT_CODE_INVALID;
        }
    }

    private static int validateDoneCommand(String[] words) {
        try {
            if (words.length == 1 || Integer.parseInt(words[1]) < 1) {
                return INPUT_CODE_INVALID;
            } else {
                return INPUT_CODE_DONE;
            }
        } catch(NumberFormatException e){
            return INPUT_CODE_INVALID;
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
        case INPUT_CODE_INVALID:
            printDividerLine();
            System.out.println("You have enter an invalid command, please try again.");
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
        default:
            printDividerLine();
            Task newTask = new Task(userInput);
            newTask.addTask();
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
