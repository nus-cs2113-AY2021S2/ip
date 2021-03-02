/**
 * Public class "Parser" parses the user input to be used in other functions
 */
public class Parser {

    /**
     * Checks the number of tokens and throws an exception based on the command
     *
     * @param numberOfTokens number of tokens in the string
     * @param command interpreted command from the user to be used in error finding
     * @throws DukeException exception based on command
     */
    public static void lengthCheck(int numberOfTokens, String command) throws DukeException {
        if (numberOfTokens < 2) {
            throw new DukeException(command);
        }
    }

    /**
     * Interprets the user's input and creates a command following the interpretation.
     *
     * @param fullCommand users input
     * @return Command
     */
    public static Command parse(String fullCommand) {
        // breaks the string into two sections.
        String[] stringTokens = fullCommand.trim().split(" ", 2);
        int numberOfTokens = stringTokens.length;
        String command = stringTokens[0];
        Command c = null;
        try {
            switch (command) {
                case "list":
                    c = new ListTasksCommand();
                    break;
                case "done":
                    lengthCheck(numberOfTokens, command);
                    c = new MarkAsDoneCommand(Integer.parseInt(stringTokens[1].trim()));
                    break;
                case "todo":
                    lengthCheck(numberOfTokens, command);
                    c = new AddTodoCommand(stringTokens[1].trim());
                    break;
                case "event":
                    lengthCheck(numberOfTokens, command);
                    c = new AddEventCommand(stringTokens[1].trim());
                    break;
                case "deadline":
                    lengthCheck(numberOfTokens, command);
                    c = new AddDeadlineCommand(stringTokens[1].trim());
                    break;
                case "delete":
                    lengthCheck(numberOfTokens, command);
                    c = new DeleteTaskCommand(Integer.parseInt(stringTokens[1].trim()));
                    break;
                case "find":
                    lengthCheck(numberOfTokens, command);
                    c = new FindCommand(stringTokens[1].trim());
                    break;
                case "help":
                    c = new ShowHelpCommand();
                    break;
                case "bye":
                    c = new ByeCommand();
                    break;
                default:
                    Ui.unrecognizedCommandMessage();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.invalidFormatMessage();
        } catch (DukeException e) {
            e.getError(command);
        }
        return c;
    }
}

