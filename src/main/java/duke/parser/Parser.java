package duke.parser;

import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Makes sense of user command.
 */
public class Parser {

    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructs Parser.
     *
     * @param taskList Current list of tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
        ui = new Ui();
    }

    /**
     * Determines the command from user input.
     *
     * @return Command to exit program.
     */
    public boolean determineCommand() {

        int COMMAND_TASK_SEPARATOR = 2;
        boolean isExit = false;
        String[] command = splitInputLine(COMMAND_TASK_SEPARATOR);

        ui.printHorizontalLine();
        switch (command[0].toLowerCase()) {
        case "list":
            taskList.printListMessage();
            break;
        case "done":
            taskList.markTaskAsDone(command);
            break;
        case "deadline":
            taskList.addDeadline(command);
            break;
        case "event":
            taskList.addEvent(command);
            break;
        case "todo":
            taskList.addToDo(command);
            break;
        case "delete":
            taskList.deleteTask(command);
            break;
        case "find":
            taskList.findTask(command);
            break;
        case "bye":
            isExit = true;
            ui.printByeMessage();
            break;
        default:
            printCommandDoesNotExistMessage();
            break;
        }

        ui.printHorizontalLine();

        return isExit;
    }

    /**
     * Prints message when command does not exist.
     */
    public void printCommandDoesNotExistMessage() {
        System.out.println(" ERROR: there is no such command, try again!");
    }

    /**
     * Splits input line to determine command.
     *
     * @param COMMAND_TASK_SEPARATOR Separate input line to command and task details.
     * @return Type of command that user inputted.
     */
    public String[] splitInputLine(int COMMAND_TASK_SEPARATOR) {
        Scanner userInput = new Scanner(System.in);
        String inputLine = userInput.nextLine().trim();
        String[] command = inputLine.split(" ", COMMAND_TASK_SEPARATOR);
        return command;
    }
}
