package duke;

import java.util.Scanner;

import static duke.Constant.*;

/**
 * To make sense of commands by user.
 */
public class Parser {

    public static final Scanner SCANNER = new Scanner(System.in);

    /**
     * To run the type of command given by the user.
     *
     * @param tasks
     * @throws MissingTaskException
     */
    public static void selectCommand(TaskList tasks) throws MissingTaskException {
        String userInput = "";
        userInput = SCANNER.nextLine();
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
            if (userInput.length() < DONE_COMMAND) {
                Ui.printEmptyCommand(DONE_STR_COMMAND);
                return;
            }
            try {
                TaskList.markTaskDone(userInput);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No index found. Please key an appropriate index");
            }
            Storage.writeToFile(tasks);

        } else if (command.equals(TODO_STR_COMMAND)) {
            if (userInput.length() <= TODO_COMMAND) {
                Ui.printEmptyCommand(TODO_STR_COMMAND);
                return;
            }

            try {
                emptyTask(userInput, TODO_COMMAND);
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
                emptyTask(userInput, DEADLINE_COMMAND);
                by = userInput.substring(getSlashIndex + 4);
                processedDeadlineInput = userInput.substring(9, getSlashIndex).trim();
                TaskList.addDeadlineTask(by, processedDeadlineInput);
                System.out.println("Got it. I've added this task: ");
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
                emptyTask(userInput, EVENT_COMMAND);
                at = userInput.substring(getSlashIndex + 4).trim();
                processedEventInput = userInput.substring(6, getSlashIndex).trim();
                TaskList.addEventTask(at, processedEventInput);
                System.out.println("Got it. I've added this task: ");
                Storage.writeToFile(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Incorrect Slash input! " +
                        "Try again! Do event /at <something>");
            } catch (MissingTaskException e) {
                System.out.println("Please key in an appropriate task.");
            }

        } else if (command.equals(DELETE_STR_COMMAND)) {
            if (userInput.length() < DELETE_COMMAND) {
                Ui.printEmptyCommand(DELETE_STR_COMMAND);
                return;
            } else {
                int processedInput;
                try {
                    processedInput = Integer.parseInt
                            (userInput.replaceAll("[^0-9]", "")) - 1;
                    System.out.println(tasks.getProcessedInputAtIndex(processedInput));
                    tasks.removeTask(processedInput);
                    System.out.println("I've removed the above task for you!");
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    Storage.writeToFile(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("No index found. Please key an appropriate index");
                } catch (NumberFormatException e) {
                    System.out.println("Key in an appropriate number. E.g. [delete 1]");
                }
            }

        } else if (command.equals("find")) {
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
    public static void emptyTask(String userInput, int commandLength) throws MissingTaskException {
        if(userInput.substring(commandLength).replace(" ","").isEmpty()) {
            throw new MissingTaskException();
        }
    }
}
