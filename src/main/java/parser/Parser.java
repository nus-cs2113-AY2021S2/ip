package parser;

import common.Constants;
import common.Messages;
import exceptions.NoResultException;
import ui.TextUi;
import commands.*;
import exceptions.EmptyDescriptionException;
import exceptions.EmptyNumberException;

/**
 * process user inputs
 */
public class Parser {

    public static final Constants constants = new Constants();
    public static final Messages messages = new Messages();
    public static final TextUi textUi = new TextUi();

    public static String[] splitSaveFileInput(String saveFileInput) {
        return saveFileInput.split(" ", 3);
    }

    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] splitCommand = rawUserInput.split(" ", 2);
        return splitCommand.length == 2 ? splitCommand : new String [] {splitCommand[0], ""};
    }

    public static String[] splitDescriptionAndDeadline(String commandArgs) {
        return commandArgs.split(" /by ");
    }

    public static String[] splitDescriptionAndTime(String commandArgs) {
        return commandArgs.split(" at ");
    }

    /**
     * process input from save file
     * @param commandDone whether command is done or not
     * @param commandType type of command
     * @param commandArgs description of the command
     */
    public static void processFileInput(String commandDone, String commandType, String commandArgs) {
        if (commandType.equals(constants.COMMAND_TODO_WORD)) {
            new AddTodoFromFile().execute(commandDone, commandArgs);
        }  else if (commandType.equals(constants.COMMAND_DEADLINE_WORD)) {
            new AddDeadlineFromFile().execute(commandDone, commandArgs);
        }  else if (commandType.equals(constants.COMMAND_EVENT_WORD)) {
            new AddEventFromFile().execute(commandDone, commandArgs);
        } else {
            // this case will not happen unless user edits the save file to an incorrect format
            System.out.println(messages.MESSAGE_INVALID_FILE_LINE_INPUT);
        }
    }

    /**
     * process input from user
     * @param userInput input from user
     */
    public static void processUserInput(String userInput) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInput);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        if (commandType.equals(constants.COMMAND_TODO_WORD)) {
            try {
                new AddTodo().execute(commandArgs);
            } catch (EmptyDescriptionException e) {
                System.out.println(messages.MESSAGE_DESCRIPTION_EMPTY_TODO);
            }
        } else if (commandType.equals(constants.COMMAND_DEADLINE_WORD)) {
            try {
                new AddDeadline().execute(commandArgs);
            } catch (EmptyDescriptionException e) {
                System.out.println(messages.MESSAGE_DESCRIPTION_EMPTY_DEADLINE);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(messages.MESSAGE_INVALID_DEADLINE);
            }
        } else if (commandType.equals(constants.COMMAND_EVENT_WORD)) {
            try {
                new AddEvent().execute(commandArgs);
            } catch (EmptyDescriptionException e) {
                System.out.println(messages.MESSAGE_DESCRIPTION_EMPTY_EVENT);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(messages.MESSAGE_INVALID_EVENT);
            }
        } else if (commandType.equals(constants.COMMAND_LIST_WORD)) {
            new ShowTasksList().execute();
        } else if (commandType.equals(constants.COMMAND_DONE_WORD)) {
            try {
                new DoneTask().execute(commandArgs);
            } catch (EmptyNumberException e) {
                System.out.println(messages.MESSAGE_DESCRIPTION_EMPTY_DONE);
            } catch (NumberFormatException e) {
                System.out.println(messages.MESSAGE_INVALID_COMMAND_DONE);
            } catch (NullPointerException e) {
                System.out.println(messages.MESSAGE_INVALID_NUMBER_DONE);
            }
        } else if (commandType.equals(constants.COMMAND_DELETE_WORD)) {
            try {
                new DeleteTask().execute(commandArgs);
            } catch (EmptyNumberException e) {
                System.out.println(messages.MESSAGE_DESCRIPTION_EMPTY_DELETE);
            } catch (NumberFormatException e) {
                System.out.println(messages.MESSAGE_INVALID_COMMAND_DELETE);
            } catch (NullPointerException e) {
                System.out.println(messages.MESSAGE_INVALID_NUMBER_DELETE);
            }
        } else if (commandType.equals(constants.COMMAND_FIND_WORD)) {
            try {
                new Find().execute(commandArgs);
            } catch (EmptyDescriptionException e) {
                System.out.println(messages.MESSAGE_KEYWORD_EMPTY_FIND);
            } catch (NoResultException e) {
                System.out.println(messages.MESSAGE_KEYWORD_INVALID_FIND);
            }
        } else if (commandType.equals(constants.COMMAND_HELP_WORD)) {
            new Help().execute();
        } else if (commandType.equals(constants.COMMAND_EXIT_WORD)) {
            new ExitProgram().execute();
        } else {
            System.out.println(messages.MESSAGE_INVALID_COMMAND);
        }
    }
}
