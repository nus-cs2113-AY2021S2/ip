package duke.parser;

import duke.Command;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.InvalidCommandException;

/**
 * Parses user inputs.
 */
public class Parser {

    /**
     * Parses user input into command for execution
     *
     * @param input full user input string
     * @return the command based on the user input
     * @throws InvalidCommandException  If wrong input entered by user
     */
    public static Command getCommand(String input) throws InvalidCommandException {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.startsWith("done ")) {
            return Command.DONE;
        } else if (input.startsWith("todo ")) {
            return Command.TODO;
        } else if (input.startsWith("deadline ")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event ")) {
            return Command.EVENT;
        } else if (input.startsWith("delete ")) {
            return Command.DELETE;
        } else if (input.startsWith("find ")) {
            return Command.FIND;
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Checks if the description entered by user is valid for the respective command.
     *
     * @param description description part of the user input
     * @param command user's intended command
     * @throws EmptyDescriptionException If description is empty
     */
    public static void validateDescription(String description, Command command) throws EmptyDescriptionException {
        boolean isEmptyDescription = false;
        if (description.equals("")) {
            isEmptyDescription = true;
        }
        switch (command) {
        case DONE:
            if (description.replace("done ", "").equals("")) {
                isEmptyDescription = true;
            }
            break;
        case DELETE:
            if (description.replace("delete ", "").equals("")) {
                isEmptyDescription = true;
            }
            break;
        case FIND:
            if (description.replace("find ", "").equals("")) {
                isEmptyDescription = true;
            }
            break;
        case EVENT:
            String[] stringArray = description.split("/at");
            for (String keyword : stringArray) {
                isEmptyDescription = isEmptyDescription || keyword.trim().isEmpty();
            }
            break;
        case DEADLINE:
            stringArray = description.split("/by");
            for (String keyword : stringArray) {
                isEmptyDescription = isEmptyDescription || keyword.trim().isEmpty();
            }
            break;
        default:
            break;
        }
        if (isEmptyDescription){
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Parses the user input to get task number. Only for Done and Delete command.
     *
     * @param input full user input string
     * @param command user's intended command
     * @return the task number indicated by user
     */
    public static int getTaskNum(String input, Command command) throws NumberFormatException {
        switch (command) {
        case DONE:
            return Integer.parseInt(input.replaceFirst("done ", ""));
        case DELETE:
            return Integer.parseInt(input.replaceFirst("delete ", ""));
        default:
            return -1;
        }
    }
}
