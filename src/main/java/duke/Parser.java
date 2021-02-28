package duke;

import duke.Commands.*;
import duke.Exceptions.*;

import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {
    }

    /**
     * Full line of user input as input
     * Returns a <code>Command</code> that corresponds to the user input and parses its arguments
     * Catches formatting and input errors and throws DukeExceptions
     * @param userInput The input that is obtained from the user
     * @return A <code>Command</code> object that corresponds to the user input
     * @throws DukeException When there are formatting issues or unknown commands in the user input
     */
    public static Command parse(String userInput) throws DukeException {
        final String[] words = userInput.split(" ", 2);
        final String commandWord = words[0].toLowerCase();
        Command command;
        switch (commandWord) {
        case "list":
            command = new ListCommand();
            break;
        case "find":
            try {
                command = new FindCommand(words[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FindFormatException();
            }
            break;
        case "done":
            try {
                command = new DoneCommand(Integer.parseInt(words[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.showMissingIndexInput();
                throw new DoneFormatException();
            } catch (NumberFormatException e) {
                Ui.showIncorrectIndexType();
                throw new DoneFormatException();
            }
            break;
        case "todo":
            //Fallthrough
        case "deadline":
            //Fallthrough
        case "event":
            try {
                String commandDescription = words[1];
                command = new AddCommand(commandWord, commandDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new AddFormatException(commandWord);
            }
            break;
        case "schedule":
            try {
                command = new ScheduleCommand(words[1]);
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                throw new ScheduleFormatException();
            }
            break;
        case "delete":
            try {
                command = new DeleteCommand(Integer.parseInt(words[1]) - 1);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.showMissingIndexInput();
                throw new DeleteFormatException();
            } catch (NumberFormatException e) {
                Ui.showIncorrectIndexType();
                throw new DeleteFormatException();
            }
            break;
        case "bye":
            command = new ExitCommand();
            break;
        default:
            throw new CommandNotFoundException();
        }
        return command;
    }
}
