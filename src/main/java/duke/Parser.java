package duke;

import duke.Commands.*;
import duke.Exceptions.*;

public class Parser {

    public Parser() {
    }

    public static Command parse(String userInput) throws DukeException {
        final String[] words = userInput.split(" ", 2);
        final String commandWord = words[0].toLowerCase();
        Command command;
        switch (commandWord) {
        case "list":
            command = new ListCommand();
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
