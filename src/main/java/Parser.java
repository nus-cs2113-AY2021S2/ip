import java.util.Scanner;

/**
 * Reads user inputs and processes them
 */

public class Parser {

    /**
     * Initializes a scanner and starts the loop
     * @param tasks list of tasks of Bob class
     * @param storage storage capabilities of Bob class
     * @param ui text ui of Bob class
     */
    public static void scanInput(TaskList tasks, Storage storage, Ui ui) {
        Scanner scanner = new Scanner(System.in);
        scanLoop(scanner, tasks, storage, ui);
    }

    /**
     * Reads user input and processes it
     * Loops until bye command is received
     * Prints out exceptions that maybe be caught
     * @param scanner Scanner initialized in scanInput
     * @param tasks list of tasks of Bob class
     * @param storage storage capabilities of Bob class
     * @param ui text ui of Bob class
     */
    private static void scanLoop(Scanner scanner, TaskList tasks, Storage storage, Ui ui) {
        boolean isScanning = true;
        String inputString;
        Command commandType = null;

        while (isScanning) {
            try {
                inputString = scanner.nextLine();
                commandType = getCommandType(inputString);
                isScanning = scanSwitch(inputString, commandType, tasks);
                storage.saveData(tasks);
            } catch (java.util.InputMismatchException e) {
                ui.printInputMismatch();
            } catch (NoSuchMethodException  e) {
                ui.printNoSuchMethod();
            } catch (NoCommandLabelException e) {
                ui.printNoCommandLabel(commandType);
            } catch (NoCommandFormatException e) {
                ui.printNoCommandFormat(commandType);
            } catch (NoTaskSpecifiedException e) {
                ui.printNoTaskSpecified();
            } catch (IndexOutOfBoundsException e) {
                ui.printIndexOutOfBounds();
            } catch (NoSearchQueryException e) {
                ui.printNoSearchQuery();
            }
        }
    }

    /**
     * String before first space taken as command rest as args
     * @param inputString User input
     * @param commandType enum Command returned by getCommandType
     * @param tasks list of tasks of Bob class
     * @return isScanning which is true unless Command is Bye, then it would be false
     * @throws NoSuchMethodException If command type is invalid
     * @throws NoCommandLabelException If task label is not stated for construction
     * @throws NoCommandFormatException If time marker is missing for deadline or event
     * @throws NoTaskSpecifiedException If task number is not specified for done or delete
     * @throws IndexOutOfBoundsException If task number specified is out of TaskList bounds
     * @throws NoSearchQueryException If search query is not specified for search
     */
    private static boolean scanSwitch(String inputString, Command commandType, TaskList tasks) throws
            NoSuchMethodException, NoCommandLabelException, NoCommandFormatException, NoTaskSpecifiedException,
            IndexOutOfBoundsException, NoSearchQueryException {
        String[] inputStringArr = inputString.split(" ");
        boolean isScanning = true;

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
        case BYE:
            isScanning = false;
            break;
        default:
            throw new NoSuchMethodException();
        }
        return isScanning;
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
