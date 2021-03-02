package Duke.Commands;

import Duke.Exceptions.IllegalKeywordException;
import Duke.UI.PrintMessages;
import Duke.Task.Task;
import Duke.Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Duke.UI.TEXT.DATE_FORMAT;

public class FindTaskCommand extends Duke {
    private static final String DOT = ". ";

    /**
     * Finds tasks on a specific date
     * Shows error if theres no task on the date
     * @param date
     */
    public static void FindWithDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            for (Task task : lists) {
                if (formattedDate.equals(task.getTaskTime())) {
                    System.out.println(lists.indexOf(task) + 1 + DOT + task.toString());
                }
            }
        } catch (Exception e) {
            PrintMessages.cannotFind(date);
        }
    }

    /**
     * Finds tasks with a specific keyword
     * Shows error if theres no task containing the keyword
     * @param keyword
     */
    public static void FindWithKeyword(String keyword) {
        try {
            boolean found = false;
            for (Task task : lists) {
                if (task.getTask().contains(keyword)) {
                    System.out.println(lists.indexOf(task) + 1 + DOT + task.toString());
                    found = true;
                }
            }
            if (!found) {
                throw new IllegalKeywordException();
            }
        } catch (IllegalKeywordException e) {
            PrintMessages.cannotFind(keyword);
        }
    }
}
