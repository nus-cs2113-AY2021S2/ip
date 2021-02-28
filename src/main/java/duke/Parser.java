package duke;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DeadlineCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles parsing of user input into commands.
 */
public class Parser {
    /**
     * Parses user input to corresponding command class based on command type in user input.
     *
     * @param userInput Raw argument taken from user.
     * @return Command class based on the corresponding command type in user input.
     * @throws DukeException If user input is in wrong format.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] splitInputs = splitInput(userInput);
        String command = splitInputs[0];
        String argument = splitInputs[1];

        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            return new DoneCommand(getIndexNumber(argument));
        } else if (command.equals("find")) {
            return new FindCommand(argument);
        } else if (command.equals("todo")) {
            return new TodoCommand(argument);
        } else if (command.equals("event")) {
            String[] parameters = splitParameter(argument,"/at ", 2);
            String description = parameters[0];
            String at = parameters[1];
            return new EventCommand(description, at);
        } else if (command.equals("deadline")) {
            String[] parameters = splitParameter(argument,"/by ", 2);
            String description = parameters[0];
            String by = parameters[1];
            return new DeadlineCommand(description, by);
        } else if (command.equals("delete")) {
            return new DeleteCommand(getIndexNumber(argument));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Splits raw user input into command type and parameter.
     * The split is done based on the first space encountered in user input.
     *
     * @param input Raw user input.
     * @return A string array containing the split command type and parameter.
     */
    private static String[] splitInput(String input) {
        int firstSpacePosition = input.indexOf(" ");
        String lowercaseCommand = input;
        String parameter = "";

        if (firstSpacePosition > 0) {
            lowercaseCommand = input.substring(0, firstSpacePosition).toLowerCase();
            parameter = input.substring(firstSpacePosition + 1);
        }

        return new String[]{lowercaseCommand, parameter};
    }

    /**
     * Returns integer index number based on the string index number provided.
     *
     * @param index String index number provided by the user as parameter.
     * @return Integer index number based on 0-based indexing.
     * @throws DukeException If the string index provided is not a number.
     */
    private static int getIndexNumber(String index) throws DukeException {
        if (checkIsNumber(index)) {
            return Integer.parseInt(index) - 1;
        } else {
            throw new DukeException("The parameter must be a number :-(");
        }
    }

    /**
     * Validates if the input string is a number and returns boolean value as result.
     *
     * @param input String parameter that will be parsed into integer.
     * @return A boolean value indicating whether the input string is a number.
     * @throws DukeException If string is empty.
     */
    private static boolean checkIsNumber(String input) throws DukeException {
        checkIsEmpty(input);

        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether an input string is empty
     *
     * @param input String parameter obtained for "done" and "delete" command.
     * @throws DukeException If input string is empty.
     */
    private static void checkIsEmpty(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("The index number cannot be empty :-(");
        }
    }

    /**
     * Splits parameter based on the delimiter specified by command type.
     * If the parameter is less than number of required parameters, throws DukeException.
     *
     * @param parameter String parameter that needs to be split into multiple parts.
     * @param delimiter Token that is used as keyword for splitting the parameter.
     * @param numberOfParameters The number of parameters required for command to work.
     * @return A string array consisting of all successful split parameters.
     * @throws DukeException If the number of parameters is < the number of required parameters.
     */
    private static String[] splitParameter(String parameter, String delimiter, int numberOfParameters)
            throws DukeException {
        String[] parameters = parameter.split(delimiter);

        if (validateNumberOfInputParameters(parameters, numberOfParameters)) {
            return parameters;
        } else {
            throw new DukeException("The parameter format for this command is incorrect!");
        }
    }

    /**
     * Validates if the number of parameters in string array matches minimum amount requirements.
     *
     * @param parameters String array consisting of split parameters.
     * @param numberOfParameters The number of parameters required for command to work.
     * @return A boolean value indicating whether the number of parameters meet minimum amount.
     */
    private static boolean validateNumberOfInputParameters(String[] parameters, int numberOfParameters) {
        if (parameters.length < numberOfParameters) {
            return false;
        } else {
            return true;
        }
    }
}
