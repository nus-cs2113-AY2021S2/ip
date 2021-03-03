import Exceptions.IllegalCommandWordException;
import Exceptions.MissingDeadlineException;
import Exceptions.MissingEventTimeException;

public class Parser {
    public Parser() {}

    private static final String COMMAND_WORDS = "list" + "done" + "todo" + "deadline" + "event" + "delete" + "find";

    static void handleInput(String line) throws IllegalCommandWordException {
        /**
         * Handles the immediate input from the user.
         *
         * Priority given:
         * 1. List
         * 2. Done
         * 3. Delete
         * 4. FInd
         * 5. todo/deadline/event
         *
         * Throws an error if the command word is does not belong to any of the above.
         */
        line = line.trim();
        String[] wordsEntered = line.split(" ");
        if (!COMMAND_WORDS.contains(wordsEntered[0].toLowerCase())) {
            throw new IllegalCommandWordException();
        } else if (line.toLowerCase().equals("list")){
            TaskList.listTasks();
        } else if (line.toLowerCase().contains("done")){
            TaskList.markTaskAsDone(line);
        } else if (line.toLowerCase().contains("delete")) {
            TaskList.deleteTask(line);
        } else if (line.toLowerCase().contains("find")) {
            TaskList.findTask(line);
        } else {
            try {
                TaskList.addTask(line);
            } catch (MissingDeadlineException e) {
                System.out.print("\tSorry, please indicate a valid deadline!\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.print("\tSorry, please check if sufficient inputs are given.\n" +
                        "\tTODO: details\n" +
                        "\tDeadline: details, deadline\n" +
                        "\tEvent: details, time of event\n");
            } catch (MissingEventTimeException e) {
                System.out.print("\tSorry, please indicate a valid event time!\n");
            }
        }
    }
}
