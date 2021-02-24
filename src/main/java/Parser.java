import java.util.Scanner;

public class Parser {

    public static void scanInput(Ui ui, TaskList tasks, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        scanLoop(scanner, tasks, storage, ui);
    }

    /**
     * Scanner loop until bye command
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
            }
        }
    }

    /**
     * String before first space taken as command rest as args
     */
    public static boolean scanSwitch(String inputString, Command commandType, TaskList tasks) throws
            NoSuchMethodException, NoCommandLabelException, NoCommandFormatException, NoTaskSpecifiedException,
            IndexOutOfBoundsException {
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

    /**
     * Takes a string and returns a enum Command
     * Treats an unknown command as regular task
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
}
