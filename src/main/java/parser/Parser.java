package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import constant.Constant;
import error.*;
import printer.Printer;

public class Parser {

    private static final Constant constant = new Constant();
    private static final Printer printer = new Printer();

    public static int getCommandCode(String userInput) {
        String[] words = userInput.split(" ");
        try {
            words[0].toLowerCase();
        } catch (ArrayIndexOutOfBoundsException e) {
            return constant.INPUT_CODE_DEFAULT_INVALID;
        }
        // No Fallthrough intended in this switch statement
        switch (words[0].toLowerCase()) {
        case "bye":
            return constant.INPUT_CODE_EXIT;
        case "list":
            return constant.INPUT_CODE_LIST;
        case "done":
            return initCheckDone(userInput, words);
        case "todo":
            return initCheckTodo(userInput, words);
        case "deadline":
            return initCheckDeadline(userInput);
        case "event":
            return initCheckEvent(userInput);
        case "delete":
            return initCheckDelete(userInput, words);
        case "find":
            System.out.println("testing only la chill");
            return initCheckFind(userInput, words);
        default:
            return constant.INPUT_CODE_DEFAULT_INVALID;
        }
    }

    private static int initCheckDone(String userInput, String[] words) {
        try {
            return validateDoneCommand(words);
        } catch (DoneCommandException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("There is no task smaller than 1!");
            return constant.INPUT_CODE_INVALID;
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("You need a task number behind done command!");
            return constant.INPUT_CODE_INVALID;
        }
    }

    private static int initCheckTodo(String userInput, String[] words) {
        try {
            return validateTodoCommand(words);
        } catch (TodoCommandException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("You need an task behind!");
            return constant.INPUT_CODE_INVALID;
        }
    }

    private static int initCheckDeadline(String userInput) {
        try {
            return validateDeadlineCommand(userInput);
        } catch (DeadlineCommandException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("Your format must be [ /by ]!");
            return constant.INPUT_CODE_INVALID;
        } catch (EmptyTimeDescriptionException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("Please enter a date!");
            return constant.INPUT_CODE_INVALID;
        } catch (EmptyTaskDescriptionException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("Please enter a task description!");
            return constant.INPUT_CODE_INVALID;
        } catch (DateTimeParseException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("Your date format is wrong!");
            return constant.INPUT_CODE_INVALID;
        }
    }

    private static int initCheckEvent(String userInput) {
        try {
            return validateEventCommand(userInput);
        } catch (EventCommandException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("Your format must be [ /at ]!");
            return constant.INPUT_CODE_INVALID;
        } catch (EmptyTimeDescriptionException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("Please enter a date or time!");
            return constant.INPUT_CODE_INVALID;
        } catch (EmptyTaskDescriptionException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("Please enter a task description!");
            return constant.INPUT_CODE_INVALID;
        }
    }

    private static int initCheckDelete(String userInput, String[] words) {
        try {
            return validateDeleteCommand(words);
        } catch (DeleteCommandException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("You cannot delete a task smaller than 1!");
            return constant.INPUT_CODE_INVALID;
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("You need a task number behind delete command!");
            return constant.INPUT_CODE_INVALID;
        }
    }

    private static int initCheckFind(String userInput, String[] words) {
        try {
            return validateFindCommand(words);
        } catch (FindCommandException e) {
            printer.printTaskWarningMessage(userInput);
            System.out.println("You need a key word to search!");
            return constant.INPUT_CODE_INVALID;
        }
    }

    private static int validateDoneCommand(String[] words) throws DoneCommandException {
        if (Integer.parseInt(words[1]) < 1 || words.length == 1) {
            throw new DoneCommandException();
        } else {
            return constant.INPUT_CODE_DONE;
        }
    }

    private static int validateTodoCommand(String[] words) throws TodoCommandException {
        if (words.length <= 1) {
            throw new TodoCommandException();
        } else {
            return constant.INPUT_CODE_TODO;
        }
    }

    private static int validateDeadlineCommand(String userInput) throws DeadlineCommandException,
            EmptyTimeDescriptionException, EmptyTaskDescriptionException, DateTimeParseException {
        if (!userInput.contains(" /by ")) {
            throw new DeadlineCommandException();
        }

        String[] splitUserInput = userInput.split(" /by ", 2);
        if (splitUserInput[1].isEmpty()) {
            throw new EmptyTimeDescriptionException();
        }

        String taskDescription = splitUserInput[0].replaceAll("deadline", "");
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }

        LocalDate.parse(splitUserInput[1]);
        return constant.INPUT_CODE_DEADLINE;
    }

    private static int validateEventCommand(String userInput) throws EventCommandException,
            EmptyTimeDescriptionException, EmptyTaskDescriptionException, DateTimeParseException {
        if (!userInput.contains(" /at ")) {
            throw new EventCommandException();
        }

        String[] splitUserInput = userInput.split(" /at ", 2);
        if (splitUserInput[1].isEmpty()) {
            throw new EmptyTimeDescriptionException();
        }

        String taskDescription = splitUserInput[0].replaceAll("event", "");
        if (taskDescription.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }

        return constant.INPUT_CODE_EVENT;
    }

    private static int validateDeleteCommand(String[] words) throws DeleteCommandException {
        if (Integer.parseInt(words[1]) < 1 || words.length == 1) {
            throw new DeleteCommandException();
        } else {
            return constant.INPUT_CODE_DELETE;
        }
    }

    private static int validateFindCommand(String[] words) throws FindCommandException {
        if (words.length == 1) {
            throw new FindCommandException();
        } else {
            return constant.INPUT_CODE_FIND;
        }
    }

    public static String extractTaskTiming(String userInput) {
        String unfilteredTaskTiming = removeCommandCode(userInput);
        String[] taskTiming = unfilteredTaskTiming.split("/", 2);
        taskTiming = taskTiming[1].split(" ", 2);
        return taskTiming[1];
    }

    private static String removeCommandCode(String userInput) {
        String[] userInputArray= userInput.split(" ", 2);
        return userInputArray[1];
    }

    public static String extractTaskDescription(String userInput) {
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

    public static int getIndexFromUserInput(String userInput) {
        String[] words = userInput.split(" ");
        int indexResult = Integer.parseInt(words[1]);
        return indexResult;
    }
}
