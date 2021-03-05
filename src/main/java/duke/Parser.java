package duke;

import java.util.Scanner;

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
        if (userInput.trim().equals("bye")) {
            Storage.writeToFile(tasks);
            Ui.printBye();
            Duke.isRunning = false;
            return;
        } else if (userInput.startsWith("list")) {
            TaskList.listTasks();
        } else if (userInput.split(" ")[0].equals("done")) {
            if (userInput.length() < 6) {
                Ui.printEmptyCommand("done");
                return;
            }
            try {
                TaskList.markTaskDone(userInput);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No index found. Please key an appropriate index");
            }

            Storage.writeToFile(tasks);
        } else if (userInput.split(" ")[0].equals("todo")) {
            if (userInput.length() < 6) {
                Ui.printEmptyCommand("todo");
                return;
            }
                if(userInput.split(" ")[1].replace(" ","").isEmpty()) {
                    throw new MissingTaskException("Please key in an appropriate task.");
                }
                TaskList.addTodoTask(userInput);
                Storage.writeToFile(tasks);

        } else if (userInput.split(" ")[0].equals("deadline")) {
            if (userInput.length() < 10) {
                Ui.printEmptyCommand("deadline");
                return;
            }
            String by = "";
            String processedDeadlineInput;
            int getSlashIndex = 0;
            for (int i = 0; i < userInput.length(); i++) {
                char getSlash = userInput.charAt(i);
                if (getSlash == '/') {
                    getSlashIndex = i;
                    break;
                }
            }
            try {
                by = userInput.substring(getSlashIndex + 4);
                processedDeadlineInput = userInput.substring(9, getSlashIndex).trim();
                TaskList.addDeadlineTask(by, processedDeadlineInput);
                System.out.println("Got it. I've added this task: ");
                Storage.writeToFile(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Incorrect Slash input! Try again! Do event /at <something>");
            }
        } else if (userInput.split(" ")[0].equals("event")) {
            if (userInput.length() < 7) {
                Ui.printEmptyCommand("event");
                return;
            }

            String at;
            String processedEventInput;
            int getSlashIndex;
            getSlashIndex = userInput.indexOf("/at");
            try {
                at = userInput.substring(getSlashIndex + 4).trim();
                processedEventInput = userInput.substring(6, getSlashIndex).trim();
                TaskList.addEventTask(at, processedEventInput);
                System.out.println("Got it. I've added this task: ");
                Storage.writeToFile(tasks);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Incorrect Slash input! Try again! Do event /at <something>");
            }

        } else if (userInput.split(" ")[0].equals("delete")) {
            if (userInput.length() < 8) {
                Ui.printEmptyCommand("delete");
                return;
            } else {
                int processedInput;
                processedInput = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                try {
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println("  " + tasks.getProcessedInputAtIndex(processedInput));
                    tasks.removeTask(processedInput);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    Storage.writeToFile(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("No index found. Please key an appropriate index");
                }
            }
        } else if (userInput.split(" ")[0].equals("find")) {
            System.out.println("Here are the matching tasks in your list: ");
            TaskList.findTask(userInput.split(" ")[1]);
        } else {
            Ui.printWrongCommand();
        }

        Ui.printBorder();
    }
}
