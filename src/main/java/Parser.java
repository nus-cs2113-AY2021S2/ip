/**
 * Reads user inputs and processes them
 */

public class Parser {

    /**
     * Runs a loop to run the parser until the bye command is received
     * @param tasks list of tasks of Bob class
     * @param storage storage capabilities of Bob class
     * @param ui text ui of Bob class
     */
    public static void scanLoop(TaskList tasks, Storage storage, Ui ui) {
        boolean isScanning = true;
        Command commandType;

        while (isScanning) {
            commandType = scanInput(tasks, storage, ui);
            isScanning = checkExit(commandType);
        }
    }

    /**
     * Terminates the loop if the bye command is received
     * @param commandType Command enum parsed by scanSwitch
     * @return True if Command is not bye, False is Command is bye
     */
    private static boolean checkExit(Command commandType) {
        return commandType != Command.BYE;
    }

    /**
     * Reads user input and processes it
     * Loops until bye command is received
     * Prints out exceptions that may be caught
     * @param tasks list of tasks of Bob class
     * @param storage storage capabilities of Bob class
     * @param ui text ui of Bob class
     * @return Command enum parsed by scanSwitch
     */
    private static Command scanInput(TaskList tasks, Storage storage, Ui ui) {
        Command commandType = null;
        String inputString;

        try {
            inputString = ui.getScanner().nextLine();
            commandType = getCommandType(inputString);
            scanSwitch(inputString, commandType, tasks, ui);
            storage.saveData(tasks);

        } catch (NoSuchMethodException  e) {
            ui.printNoSuchMethod();
        } catch (NoTaskLabelException e) {
            ui.printNoTaskLabel(commandType);
        } catch (NoCommandFormatException e) {
            ui.printNoCommandFormat(commandType);
        } catch (NoTaskSpecifiedException e) {
            ui.printNoTaskSpecified();
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBounds();
        } catch (NoSearchQueryException e) {
            ui.printNoSearchQuery();
        }
        return commandType;
    }

    /**
     * String before first space taken as command rest as args
     * @param inputString User input
     * @param commandType enum Command returned by getCommandType
     * @param tasks list of tasks of Bob class
     * @throws NoSuchMethodException If command type is invalid
     * @throws NoTaskLabelException If task label is not stated for construction
     * @throws NoCommandFormatException If time marker is missing for deadline or event
     * @throws NoTaskSpecifiedException If task number is not specified for done or delete
     * @throws IndexOutOfBoundsException If task number specified is out of TaskList bounds
     * @throws NoSearchQueryException If search query is not specified for search
     */
    private static void scanSwitch(String inputString, Command commandType, TaskList tasks, Ui ui) throws
            NoSuchMethodException, NoTaskLabelException, NoCommandFormatException, NoTaskSpecifiedException,
            IndexOutOfBoundsException, NoSearchQueryException {
        String[] inputStringArr = inputString.split(" ");

        switch (commandType) {
        case LIST:
            tasks.printList();
            break;
        case DONE:
            setDone(tasks, inputStringArr);
            break;
        case DELETE:
            deleteTask(tasks, inputStringArr);
            break;
        case FIND:
            findTask(tasks, inputString, inputStringArr.length);
            break;
        case TODO:
            tasks.addTodo(inputString);
            break;
        case DEADLINE:
            tasks.addDeadline(inputString);
            break;
        case EVENT:
            tasks.addEvent(inputString);
            break;
        case HELP:
            ui.printHelp();
            break;
        case BYE:
            break;
        default:
            throw new NoSuchMethodException();
        }
    }

    /**
     * Returns a enum Command
     * @param commandString User input
     * @return enum Command
     * @throws NoSuchMethodException if command does not match any in enum Command
     */
    public static Command getCommandType(String commandString) throws NoSuchMethodException{
        Command command;
        String[] inputStringArr = commandString.split(" ");

        switch (inputStringArr[0]) {
        case "list":
            command = Command.LIST;
            break;
        case "done":
            command = Command.DONE;
            break;
        case "delete":
            command = Command.DELETE;
            break;
        case "find":
            command = Command.FIND;
            break;
        case "todo":
            command = Command.TODO;
            break;
        case "deadline":
            command = Command.DEADLINE;
            break;
        case "event":
            command = Command.EVENT;
            break;
        case "help":
            command = Command.HELP;
            break;
        case "bye":
            command = Command.BYE;
            break;
        default:
            throw new NoSuchMethodException();
        }
        return command;
    }

    private static void setDone(TaskList tasks, String[] inputStringArr) throws NoTaskSpecifiedException,
            IndexOutOfBoundsException {
        if (inputStringArr.length == 2) {
            tasks.completeTask(inputStringArr[1]);
        } else {
            throw new NoTaskSpecifiedException();
        }
    }

    private static void deleteTask(TaskList tasks, String[] inputStringArr) throws NoTaskSpecifiedException,
            IndexOutOfBoundsException {
        if (inputStringArr.length == 2) {
            tasks.deleteTask(inputStringArr[1]);
        } else {
            throw new NoTaskSpecifiedException();
        }
    }

    private static void findTask(TaskList tasks, String inputString, int length)
            throws NoSearchQueryException {
        String commandType = Command.FIND.name().toLowerCase();
        if (length >= 2) {
            String query = inputString.replaceFirst(commandType, "").trim();
            tasks.findTask(query);
        } else {
            throw new NoSearchQueryException();
        }
    }
}
