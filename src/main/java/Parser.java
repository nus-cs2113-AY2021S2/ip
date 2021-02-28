public class Parser {

    public static void lengthCheck(int numberOfTokens, String command) throws DukeException {
        if (numberOfTokens < 2) {
            throw new DukeException(command);
        }
    }

    public static Command parse(String fullCommand) {
        String[] stringTokens = fullCommand.trim().split(" ", 2);
        int numberOfTokens = stringTokens.length;
        Command c = null;
        try {
            String command = stringTokens[0];
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
        } catch (ArrayIndexOutOfBoundsException | DukeException e) {
            Ui.invalidFormatMessage();
        }
        return c;
    }
}

