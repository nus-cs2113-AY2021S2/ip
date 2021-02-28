package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import constant.Constant;
import error.DeadlineCommandException;
import error.DeleteCommandException;
import error.DoneCommandException;
import error.EmptyTaskDescriptionException;
import error.EmptyTimeDescriptionException;
import error.EventCommandException;
import error.FindCommandException;
import ui.Ui;

/**
 * Parser class in charge of parsing, validating, and extraction of input.
 */
public class Parser {

    /**
     * Parse the input from the user to the respective commands.
     *
     * @param userInput is the input from the console terminal.
     * @return A fixed constant tagged to the respective command.
     */
    public static int getCommandCode(String userInput) {
        String[] words = userInput.split(" ");
        String commandCode;
        try {
            commandCode = words[0].toLowerCase();
        } catch (ArrayIndexOutOfBoundsException e) {
            return Constant.INPUT_CODE_DEFAULT_INVALID;
        }
        // No Fallthrough intended in this switch statement
        switch (commandCode) {
        case "bye":
            return Constant.INPUT_CODE_EXIT;
        case "list":
            return Constant.INPUT_CODE_LIST;
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
            return initCheckFind(userInput, words);
        default:
            return Constant.INPUT_CODE_DEFAULT_INVALID;
        }
    }

    /**
     * Returns the done command code if valid or prints the error message if invalid.
     *
     * @param userInput is the input from the console terminal.
     * @param words contains the individual words from userInput.
     * @return valid or invalid command code for 'done' command.
     */
    private static int initCheckDone(String userInput, String[] words) {
        try {
            return validateDoneCommand(words);
        } catch (DoneCommandException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("There is no task smaller than 1!");
            return Constant.INPUT_CODE_INVALID;
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("You need a task number behind done command!");
            return Constant.INPUT_CODE_INVALID;
        } catch (NumberFormatException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("You need task number not a word!");
            return Constant.INPUT_CODE_INVALID;
        }
    }

    /**
     * Returns the todo command code if valid or prints the error message if invalid.
     *
     * @param userInput is the input from the console terminal.
     * @param words contains the individual words from userInput.
     * @return valid or invalid command code for 'todo' command.
     */
    private static int initCheckTodo(String userInput, String[] words) {
        try {
            return validateTodoCommand(words);
        } catch (EmptyTaskDescriptionException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("You need an task behind!");
            return Constant.INPUT_CODE_INVALID;
        }
    }

    /**
     * Returns the deadline command code if valid or prints the error message if invalid.
     *
     * @param userInput is the input from the console terminal.
     * @return valid or invalid command code for 'deadline' command.
     */
    private static int initCheckDeadline(String userInput) {
        try {
            return validateDeadlineCommand(userInput);
        } catch (DeadlineCommandException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("Please use a correct format with ' /by '!");
            return Constant.INPUT_CODE_INVALID;
        } catch (EmptyTimeDescriptionException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("Please enter a date!");
            return Constant.INPUT_CODE_INVALID;
        } catch (EmptyTaskDescriptionException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("Please enter a task description!");
            return Constant.INPUT_CODE_INVALID;
        } catch (DateTimeParseException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("Your date format is wrong!");
            return Constant.INPUT_CODE_INVALID;
        }
    }

    /**
     * Returns the event command code if valid or prints the error message if invalid.
     *
     * @param userInput is the input from the console terminal.
     * @return valid or invalid command code for 'event' command.
     */
    private static int initCheckEvent(String userInput) {
        try {
            return validateEventCommand(userInput);
        } catch (EventCommandException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("Please use a correct format with ' /at '!");
            return Constant.INPUT_CODE_INVALID;
        } catch (EmptyTimeDescriptionException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("Please enter a date or time!");
            return Constant.INPUT_CODE_INVALID;
        } catch (EmptyTaskDescriptionException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("Please enter a task description!");
            return Constant.INPUT_CODE_INVALID;
        }
    }

    /**
     * Returns the delete command code if valid or prints the error message if invalid.
     *
     * @param userInput is the input from the console terminal.
     * @param words contains the individual words from userInput.
     * @return valid or invalid command code for 'delete' command.
     */
    private static int initCheckDelete(String userInput, String[] words) {
        try {
            return validateDeleteCommand(words);
        } catch (DeleteCommandException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("You cannot delete a task smaller than 1!");
            return Constant.INPUT_CODE_INVALID;
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("You need a task number behind delete command!");
            return Constant.INPUT_CODE_INVALID;
        } catch (NumberFormatException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("You need task number not a word!");
            return Constant.INPUT_CODE_INVALID;
        }
    }

    /**
     * Returns the find command code if valid or prints the error message if invalid.
     *
     * @param userInput is the input from the console terminal.
     * @param words contains the individual words from userInput.
     * @return valid or invalid command code for 'find' command.
     */
    private static int initCheckFind(String userInput, String[] words) {
        try {
            return validateFindCommand(words);
        } catch (FindCommandException e) {
            Ui.printTaskWarningMessage(userInput);
            System.out.println("You need a key word to search!");
            return Constant.INPUT_CODE_INVALID;
        }
    }

    /**
     * Validate that the done command is in correct format.
     *
     * @param words contains the individual words from userInput.
     * @throws DoneCommandException if the task number given is smaller than 1.
     * @throws ArrayIndexOutOfBoundsException if there is no task number given.
     * @return valid or invalid command code for 'done' command.
     */
    private static int validateDoneCommand(String[] words) throws DoneCommandException,
            ArrayIndexOutOfBoundsException, NumberFormatException {
        if (Integer.parseInt(words[1]) < 1) {
            throw new DoneCommandException();
        } else {
            return Constant.INPUT_CODE_DONE;
        }
    }

    /**
     * Validate that the todo command is in correct format.
     *
     * @param words contains the individual words from userInput.
     * @throws EmptyTaskDescriptionException if the task description is empty.
     * @return valid or invalid command code for 'todo' command.
     */
    private static int validateTodoCommand(String[] words) throws EmptyTaskDescriptionException {
        if (words.length <= 1) {
            throw new EmptyTaskDescriptionException();
        } else {
            return Constant.INPUT_CODE_TODO;
        }
    }

    /**
     * Validate that the deadline command is in correct format.
     *
     * @param userInput is the input from the console terminal.
     * @throws DeadlineCommandException if the deadline identifier format is wrong.
     * @throws EmptyTimeDescriptionException if the time field is empty.
     * @throws EmptyTaskDescriptionException if the task description is empty.
     * @throws DateTimeParseException if the time field format is wrong.
     * @return valid or invalid command code for 'deadline' command.
     */
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
        return Constant.INPUT_CODE_DEADLINE;
    }

    /**
     * Validate that the event command is in correct format.
     *
     * @param userInput is the input from the console terminal.
     * @throws EventCommandException if the event identifier format is wrong.
     * @throws EmptyTimeDescriptionException if the time field is empty.
     * @throws EmptyTaskDescriptionException if the task description is empty.
     * @return valid or invalid command code for 'event' command.
     */
    private static int validateEventCommand(String userInput) throws EventCommandException,
            EmptyTimeDescriptionException, EmptyTaskDescriptionException {
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

        return Constant.INPUT_CODE_EVENT;
    }

    /**
     * Validate that the delete command is in correct format.
     *
     * @param words contains the individual words from userInput.
     * @throws DeleteCommandException if the task number given is smaller than 1.
     * @throws ArrayIndexOutOfBoundsException if there is no task number given.
     * @return valid or invalid command code for 'delete' command.
     */
    private static int validateDeleteCommand(String[] words) throws DeleteCommandException,
            ArrayIndexOutOfBoundsException, NumberFormatException {
        if (Integer.parseInt(words[1]) < 1) {
            throw new DeleteCommandException();
        } else {
            return Constant.INPUT_CODE_DELETE;
        }
    }

    /**
     * Validate that the find command is in correct format.
     *
     * @param words contains the individual words from userInput.
     * @throws FindCommandException if no keyword is given.
     * @return valid or invalid command code for 'find' command.
     */
    private static int validateFindCommand(String[] words) throws FindCommandException {
        if (words.length == 1) {
            throw new FindCommandException();
        } else {
            return Constant.INPUT_CODE_FIND;
        }
    }

    /**
     * Extract the task timing from the input.
     *
     * @param userInput is the input from the console terminal.
     * @return a string containing the task timing.
     */
    public static String extractTaskTiming(String userInput) {
        String unfilteredTaskTiming = removeCommandCode(userInput);
        String[] taskTiming = unfilteredTaskTiming.split("/", 2);
        taskTiming = taskTiming[1].split(" ", 2);
        return taskTiming[1];
    }

    /**
     * Remove the command code from the input.
     *
     * @param userInput is the input from the console terminal.
     * @return a string without the command code.
     */
    private static String removeCommandCode(String userInput) {
        String[] userInputArray = userInput.split(" ", 2);
        return userInputArray[1];
    }

    /**
     * Extract the task description from the input.
     *
     * @param userInput is the input from the console terminal.
     * @return a string containing the task description.
     */
    public static String extractTaskDescription(String userInput) {
        String taskDescription = removeCommandCode(userInput);
        if (taskDescription.contains("/")) {
            taskDescription = removeDateAndTime(taskDescription);
        }
        return taskDescription;
    }

    /**
     * Remove the date and time from the input.
     *
     * @param unfilteredDescription is the input without the command code.
     * @return a string without the time and date.
     */
    private static String removeDateAndTime(String unfilteredDescription) {
        String[] unfilteredDescriptionArray = unfilteredDescription.split("/", 2);
        return unfilteredDescriptionArray[0];
    }

    /**
     * Extract the task number from the input.
     *
     * @param userInput is the input from the console terminal.
     * @return a integer of the task number.
     */
    public static int getIndexFromUserInput(String userInput) {
        String[] words = userInput.split(" ");
        return Integer.parseInt(words[1]);
    }
}
