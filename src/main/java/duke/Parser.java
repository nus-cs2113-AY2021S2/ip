package duke;

import java.util.Scanner;

import static duke.Constant.*;

/**
 * To make sense of commands by user.
 */
public class Parser {

    public static final Scanner scanner = new Scanner(System.in);

    /**
     * To run the type of command given by the user.
     *
     * @param tasks
     * @throws ArrayIndexOutOfBoundsException
     */
    public static void selectCommand(TaskList tasks) throws ArrayIndexOutOfBoundsException {
        String userInput;
        userInput = scanner.nextLine();
        Ui.printBorder();
        String command = userInput.split(" ")[0];

        if (userInput.trim().equals(BYE_STR_COMMAND)) {
            Storage.writeToFile(tasks);
            Ui.printBye();
            Duke.isRunning = false;
            return;

        } else if (userInput.trim().equals(LIST_STR_COMMAND)) {
            TaskList.listTasks();

        } else if (command.equals(DONE_STR_COMMAND)) {
            if (userInput.length() <= DONE_COMMAND) {
                Ui.printEmptyCommand(DONE_STR_COMMAND);
                return;
            }

            try {
                incompleteCommand(userInput, DONE_STR_COMMAND, DONE_COMMAND);
                TaskList.markTaskDone(userInput);
            } catch (IndexOutOfBoundsException e) {
                Ui.printWrongIndexMessage();
            } catch (IncompleteCommandException e) {
                Ui.printErrorMessageOnMissingTask();
            }
            Storage.writeToFile(tasks);

        } else if (command.equals(TODO_STR_COMMAND)) {
            if (userInput.length() <= TODO_COMMAND) {
                Ui.printEmptyCommand(TODO_STR_COMMAND);
                return;
            }

            try {
                incompleteCommand(userInput, TODO_STR_COMMAND, TODO_COMMAND);
                Ui.printAddTaskMessage();
                TaskList.addTodoTask(userInput);
                Storage.writeToFile(tasks);
            } catch (IncompleteCommandException e) {
                Ui.printErrorMessageOnMissingTask();
            }

        } else if (command.equals(DEADLINE_STR_COMMAND)) {
            if (userInput.length() <= DEADLINE_COMMAND) {
                Ui.printEmptyCommand(DEADLINE_STR_COMMAND);
                return;
            }
            String by;
            String processedDeadlineInput;
            int getSlashIndex = userInput.indexOf(SLASH_BY_STR);

            try {
                incompleteCommand(userInput, DEADLINE_STR_COMMAND, DEADLINE_COMMAND);
                by = userInput.substring(getSlashIndex + 3);
                processedDeadlineInput = userInput.substring(9, getSlashIndex).trim();
                Ui.printAddTaskMessage();
                TaskList.addDeadlineTask(by, processedDeadlineInput);
                Storage.writeToFile(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printWrongSlashInput(DEADLINE_STR_COMMAND, SLASH_BY_STR);
            } catch (IncompleteCommandException e) {
                Ui.printErrorMessageOnMissingTask();
            }

        } else if (command.equals(EVENT_STR_COMMAND)) {
            if (userInput.length() <= EVENT_COMMAND) {
                Ui.printEmptyCommand(EVENT_STR_COMMAND);
                return;
            }

            String at;
            String processedEventInput;
            int getSlashIndex;
            getSlashIndex = userInput.indexOf(SLASH_AT_STR);
            try {
                incompleteCommand(userInput, EVENT_STR_COMMAND, EVENT_COMMAND);
                at = userInput.substring(getSlashIndex + 3).trim();
                processedEventInput = userInput.substring(6, getSlashIndex).trim();
                Ui.printAddTaskMessage();
                TaskList.addEventTask(at, processedEventInput);
                Storage.writeToFile(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printWrongSlashInput(EVENT_STR_COMMAND, SLASH_AT_STR);
            } catch (IncompleteCommandException e) {
                Ui.printErrorMessageOnMissingTask();
            }

        } else if (command.equals(DELETE_STR_COMMAND)) {
            if (userInput.length() <= DELETE_COMMAND) {
                Ui.printEmptyCommand(DELETE_STR_COMMAND);
                return;
            }

            try {
                incompleteCommand(userInput, DELETE_STR_COMMAND, DELETE_COMMAND);
                TaskList.removeTask(userInput);
                Storage.writeToFile(tasks);
            } catch (IndexOutOfBoundsException e) {
                Ui.printWrongIndexMessage();
            } catch (IncompleteCommandException e) {
                Ui.printErrorMessageOnMissingTask();
            }

        } else if (command.equals(FIND_STR_COMMAND)) {
            if (userInput.length() <= FIND_COMMAND) {
                Ui.printEmptyCommand(FIND_STR_COMMAND);
                return;
            }

            try {
                incompleteCommand(userInput, FIND_STR_COMMAND, FIND_COMMAND);
                TaskList.findTask(userInput.split(" ")[1]);
            } catch (IncompleteCommandException e) {
                Ui.printErrorMessageOnMissingTask();
            }

        } else {
            Ui.printWrongCommand();
        }

        Ui.printBorder();
    }

    /**
     * Checks if command entered by user is complete.
     * A complete command follows the format for the command.
     * Descriptions as whitespaces in command is deemed as incomplete command.
     * This prevents user from adding meaningless tasks to the list.
     *
     * @param userInput
     * @param command
     * @param commandLength
     * @throws IncompleteCommandException
     */
    public static void incompleteCommand(String userInput, String command,
                                         int commandLength) throws IncompleteCommandException {

        // checks if command is incomplete
        if (userInput.substring(commandLength).replace(" ","").isEmpty()) {
            throw new IncompleteCommandException();
        }

        if (command.equals(DEADLINE_STR_COMMAND)) {
            // checks if command has a missing task description
            if (userInput.substring(commandLength,
                    userInput.indexOf(SLASH_BY_STR))
                    .replace(" ","").isEmpty()) {
                throw new IncompleteCommandException();
            }
            //checks if command has a missing timing description
            int indexAfterSlashBy = userInput.indexOf(SLASH_BY_STR) + 3;
            if (userInput.substring(indexAfterSlashBy)
                    .replace(" ","").isEmpty()) {
                throw new IncompleteCommandException();
            }
        }

        if (command.equals(EVENT_STR_COMMAND)) {
            if (userInput.substring(commandLength,
                    userInput.indexOf(SLASH_AT_STR))
                    .replace(" ","").isEmpty()) {
                throw new IncompleteCommandException();
            }

            int indexAfterSlashAt = userInput.indexOf(SLASH_AT_STR) + 3;
            if (userInput.substring(indexAfterSlashAt)
                    .replace(" ","").isEmpty()) {
                throw new IncompleteCommandException();
            }
        }
    }

}
