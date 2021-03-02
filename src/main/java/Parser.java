import Exceptions.IllegalCommandWordException;
import Exceptions.MissingDeadlineException;
import Exceptions.MissingEventTimeException;

public class Parser {
    public static final String COMMAND_WORDS = "list" + "done" + "todo" + "deadline" + "event" + "delete";

    static void handleInput(String line) throws IllegalCommandWordException {
        line = line.trim();
        String[] wordsEntered = line.split(" ");
        if (!COMMAND_WORDS.contains(wordsEntered[0].toLowerCase())) {
            throw new IllegalCommandWordException();
        } else if (line.toLowerCase().equals("list")){
            TaskList.listTasks();
        } else if (line.toLowerCase().contains("done")){
            TaskList.markTaskAsDone(line);
        } else if (line.toLowerCase().contains("delete")) {
            // Insert code for deleting task
            TaskList.deleteTask(line);
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
