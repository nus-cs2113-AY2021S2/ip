import java.util.Scanner;

/**
 * Makes sense of user command.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Determines the command from user input.
     *
     * @return isExit to determine whether to exit program if user has input bye command.
     */
    public static boolean processInput() {
        String[] command = determineCommand();
        boolean isExit = false;
        switch (command[0].toLowerCase()) {
        case "list":
            TaskList.printList();
            break;
        case "done":
            try {
                TaskList.displayTaskDone(Integer.parseInt(command[1]));
            } catch (IndexOutOfBoundsException oob) {
                Ui.printTaskUnspecifiedMessage();
            }catch (NullPointerException npe) {
                Ui.printInvalidTask();
            } catch (DukeException de){
                Ui.printTaskAlreadyMarkedAsDone();
            }
            break;
        case "deadline":
            TaskList.addDeadline(command);
            break;
        case "event":
            TaskList.addEvent(command);
            break;
        case "todo":
            TaskList.addToDo(command);
            break;
        case "delete":
            TaskList.deleteTask(Integer.parseInt(command[1]));
            break;
        case "bye":
            isExit = true;
            Ui.printByeMessage();
            break;
        default:
            Ui.printInvalidCommandMessage();
            break;
        }

        return isExit;
    }

    /**
     * Splits input line to determine command.
     *
     * @return command the user inputted split into command type and additional description.
     */
    private static String[] determineCommand() {
        Scanner userInput = new Scanner(System.in);
        String inputLine = userInput.nextLine().trim();
        return inputLine.split(" ", 2);
    }
}
