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

public class Parser {
    public static Command parse(String userInput) throws DukeException {

        String[] splittedInputs = splitInput(userInput);
        String command = splittedInputs[0];
        String argument = splittedInputs[1];

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

    private static int getIndexNumber(String index) throws DukeException {
        if (checkIsNumber(index)) {
            return Integer.parseInt(index) - 1;
        } else {
            throw new DukeException("The parameter must be a number :-(");
        }
    }

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

    private static void checkIsEmpty(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("The index number cannot be empty :-(");
        }
    }

    private static String[] splitParameter(String parameter, String delimiter, int number)
            throws DukeException {
        String[] parameters = parameter.split(delimiter);

        if (validateNumberOfInputParameters(parameters, number)) {
            return parameters;
        } else {
            throw new DukeException("The parameter format for this command is incorrect!");
        }
    }

    private static boolean validateNumberOfInputParameters(String[] parameters, int number) {
        if (parameters.length < number) {
            return false;
        } else {
            return true;
        }
    }
}
