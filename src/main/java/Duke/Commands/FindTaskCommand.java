package Duke.Commands;

import Duke.Exceptions.IllegalKeywordException;
import Duke.UI.PrintMessages;
import Duke.Task.Task;
import Duke.Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FindTaskCommand extends Duke {
    /**
     * Finds tasks on a specific date
     * Shows error if theres no task on the date
     * @param date
     */
    public static void FindWithDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            for (Task task : lists) {
                if (formattedDate.equals(task.getTaskTime())) {
                    System.out.println(lists.indexOf(task) + 1 + ". " + task.toString());
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
                    System.out.println(lists.indexOf(task) + 1 + ". " + task.toString());
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
