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
     * @throws MissingTaskException
     */
    public static void selectCommand(TaskList tasks) throws ArrayIndexOutOfBoundsException {
        String userInput = "";
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
                emptyTask(userInput, DONE_STR_COMMAND, DONE_COMMAND);
                TaskList.markTaskDone(userInput);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No index found. Please key an appropriate index");
            } catch (MissingTaskException e) {
                System.out.println("Please key in an appropriate task.");
            }
            Storage.writeToFile(tasks);

        } else if (command.equals(TODO_STR_COMMAND)) {
            if (userInput.length() <= TODO_COMMAND) {
                Ui.printEmptyCommand(TODO_STR_COMMAND);
                return;
            }

            try {
                emptyTask(userInput, TODO_STR_COMMAND, TODO_COMMAND);
                TaskList.addTodoTask(userInput);
                Storage.writeToFile(tasks);
            } catch (MissingTaskException e) {
                System.out.println("Please key in an appropriate task.");
            }

        } else if (command.equals(DEADLINE_STR_COMMAND)) {
            if (userInput.length() <= DEADLINE_COMMAND) {
                Ui.printEmptyCommand(DEADLINE_STR_COMMAND);
                return;
            }
            String by;
            String processedDeadlineInput;
            int getSlashIndex = userInput.indexOf("/by");

            try {
                emptyTask(userInput, DEADLINE_STR_COMMAND, DEADLINE_COMMAND);
                by = userInput.substring(getSlashIndex + 4);
                processedDeadlineInput = userInput.substring(9, getSlashIndex).trim();
                System.out.println("Got it. I've added this task: ");
                TaskList.addDeadlineTask(by, processedDeadlineInput);
                Storage.writeToFile(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Incorrect Slash input! " +
                        "Try again! Do deadline /by <something>");
            } catch (MissingTaskException e) {
                System.out.println("Please key in an appropriate task.");
            }

        } else if (command.equals(EVENT_STR_COMMAND)) {
            if (userInput.length() <= EVENT_COMMAND) {
                Ui.printEmptyCommand(EVENT_STR_COMMAND);
                return;
            }

            String at;
            String processedEventInput;
            int getSlashIndex;
            getSlashIndex = userInput.indexOf("/at");
            try {
                emptyTask(userInput, EVENT_STR_COMMAND, EVENT_COMMAND);
                at = userInput.substring(getSlashIndex + 4).trim();
                processedEventInput = userInput.substring(6, getSlashIndex).trim();
                System.out.println("Got it. I've added this task: ");
                TaskList.addEventTask(at, processedEventInput);
                Storage.writeToFile(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Incorrect Slash input! " +
                        "Try again! Do event /at <something>");
            } catch (MissingTaskException e) {
                System.out.println("Please key in an appropriate task.");
            }

        } else if (command.equals(DELETE_STR_COMMAND)) {
            if (userInput.length() <= DELETE_COMMAND) {
                Ui.printEmptyCommand(DELETE_STR_COMMAND);
                return;
            }

            try {
                emptyTask(userInput, DELETE_STR_COMMAND, DELETE_COMMAND);
                TaskList.removeTask(userInput);
                Storage.writeToFile(tasks);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No index found. Please key an appropriate index");
            } catch (MissingTaskException e) {
                System.out.println("Please key in an appropriate task.");
            }


        } else if (command.equals(FIND_STR_COMMAND)) {
            System.out.println("Here are the matching tasks in your list: ");
            TaskList.findTask(userInput.split(" ")[1]);

        } else {
            Ui.printWrongCommand();
        }

        Ui.printBorder();
    }

    /**
     * Checks if the task input by the user is an empty task.
     *
     * @param userInput
     * @param commandLength
     * @throws MissingTaskException
     */
    public static void emptyTask(String userInput, String command,
                                 int commandLength) throws MissingTaskException {
        if(userInput.substring(commandLength).replace(" ","").isEmpty()) {
            throw new MissingTaskException();
        }

        if(command.equals(DEADLINE_STR_COMMAND)) {
            if(userInput.substring(commandLength,
                    userInput.indexOf("/by")).replace(" ","").isEmpty()) {
                throw new MissingTaskException();
            }
        }

        if(command.equals(EVENT_STR_COMMAND)) {
            if(userInput.substring(commandLength,
                    userInput.indexOf("/at")).replace(" ","").isEmpty()) {
                throw new MissingTaskException();
            }
        }
    }

}
