package Duke.Commands;

import Duke.Errors.Errors;
import Duke.Task.Task;
import Duke.Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FindTaskCommand extends Duke {
    /**
     * Find tasks on a specific date
     * show error if theres no task on the date
     * @param date
     */
    public static void FindWithDate(String date) {
        try {
            LocalDate d1 = LocalDate.parse(date);
            String d2 = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            for (Task t : lists) {
                if (d2.equals(t.getTaskTime())) {
                    System.out.println(lists.indexOf(t) + 1 + ". " + t.toString());
                }
            }
        } catch (Exception e) {
            Errors.cannotRecognise(date);
        }
    }

    /**
     * Find tasks with a specific keyword
     * show error if theres no task on the date
     * @param keyword
     */
    public static void FindWithKeyword(String keyword) {
        try {
            for (Task t : lists) {
                if (t.getTask().contains(keyword)) {
                    System.out.println(lists.indexOf(t) + 1 + ". " + t.toString());
                }
            }
        } catch (Exception e) {
            Errors.cannotRecognise(keyword);
        }
    }
}
