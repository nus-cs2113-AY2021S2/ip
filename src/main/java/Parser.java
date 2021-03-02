import java.util.Scanner;

public class Parser {
    public Parser() {
    }

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

    private static String[] determineCommand() {
        Scanner userInput = new Scanner(System.in);
        String inputLine = userInput.nextLine().trim();
        return inputLine.split(" ", 2);
    }
}
