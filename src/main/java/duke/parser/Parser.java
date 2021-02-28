package duke.parser;

import duke.command.*;
import duke.exception.DukeException;

import java.time.format.DateTimeParseException;
import java.util.function.BiFunction;

/**
 * Implements user input parser.
 *
 * @author Leonardo Irvin Pratama
 */
public class Parser {
    private static final BiFunction<String[], Integer, Boolean> CHECK_LENGTH = (s, i) -> s.length <= i;
    private static String errorMessage = null;
    private enum Commands {
        BYE,
        LIST,
        DONE,
        MARK,
        DELETE,
        REMOVE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    };

    private static Command handleDoneOrDelete(Commands commandType, String[] separatedWords) {
        Command command = null;
        if (CHECK_LENGTH.apply(separatedWords, 1)) {
            errorMessage = " Task index must be specified!";
            return command;
        }
        int index = Integer.parseInt(separatedWords[1]);
        switch (commandType) {
            case MARK:
            case DONE:
                command = new DoneCommand(index);
                break;
            case REMOVE:
            case DELETE:
                command = new DeleteCommand(index);
                break;
            default:
                errorMessage = " Command type not found";
        }
        return command;
    }

    /**
     * Executes task addition.
     *
     * @param commandType   User chosen type of command.
     * @param input         User input.
     * @param time          Time signature attached to the task.
     */
    private static Command handleAddTask(Commands commandType, String input, String time) {
        StringBuilder description = new StringBuilder();
        Command command = null;
        String[] separatedWords = time.equals("") ? new String[]{input} : input.split(time);
        String[] getDescription = separatedWords[0].split(" ");
        if (CHECK_LENGTH.apply(getDescription, 1)) {
            errorMessage = " Task description cannot be empty!";
            return command;
        }
        if (CHECK_LENGTH.apply(separatedWords, 1) && !commandType.equals(Commands.TODO)) {
            errorMessage = " Date cannot be empty!";
            return command;
        }
        for (int i = 1; i < getDescription.length; i++) {
            description.append(getDescription[i]).append(" ");
        }
        switch (commandType) {
            case TODO:
                command = new TodoCommand(description.toString().trim());
                break;
            case DEADLINE:
                time = separatedWords[1].trim();
                command = new DeadlineCommand(description.toString().trim(), time);
                break;
            case EVENT:
                time = separatedWords[1].trim();
                command = new EventCommand(description.toString().trim(), time);
                break;
            default:
                errorMessage = " Command type not found";
        }
        return command;
    }

    /**
     * Executes task searching.
     *
     * @param separatedWords   Keyword determined by the user's input.
     */
    private static Command handleFind(String[] separatedWords) {
        Command command = null;
        if (CHECK_LENGTH.apply(separatedWords, 1)) {
            errorMessage = " Keyword cannot be empty!";
            return command;
        }
        String keyword = separatedWords[1];
        command = new FindCommand(keyword);
        return command;
    }

    /**
     * Parses user input into command.
     *
     * @param input User input.
     * @return Command to be executed.
     * @throws DukeException If there is parsing error.
     */
    public static Command parse(String input) throws DukeException {
        try {
            String[] separatedWords = input.split("\\s+");
            Commands commandType = Commands.valueOf(separatedWords[0].toUpperCase());
            Command command;
            switch (commandType) {
                case BYE:
                    command = new GoodbyeCommand();
                    break;
                case LIST:
                    command = new ListCommand();
                    break;
                case MARK:
                case DONE:
                case REMOVE:
                case DELETE:
                    command = handleDoneOrDelete(commandType, separatedWords);
                    break;
                case TODO:
                    command = handleAddTask(commandType, input, "");
                    break;
                case DEADLINE:
                    command = handleAddTask(commandType, input, "/by");
                    break;
                case EVENT:
                    command = handleAddTask(commandType, input, "/at");
                    break;
                case FIND:
                    command = handleFind(separatedWords);
                    break;
                default:
                    throw new DukeException(" Command type not found");
            }
            if (errorMessage != null) {
                throw new DukeException(errorMessage);
            }
            return command;
        } catch (NumberFormatException error) {
            throw new DukeException(" Task index must be an integer!");
        } catch (IllegalArgumentException error) {
            throw new DukeException(" Command not recognized :(");
        } catch (DateTimeParseException error) {
            throw new DukeException(" I cannot recognize the date you put in.");
        } finally {
            errorMessage = null;
        }
    }
}
