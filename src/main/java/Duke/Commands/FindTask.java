package Duke.Commands;

import Duke.Exceptions.DukeException;
import Duke.Task.Task;
import Duke.Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Duke.Duke.lists;

public class FindTask extends Duke {
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
            DukeException.cannotRecognise(date);
        }
    }

    public static void FindWithKeyword(String keyword) {
        try {
            for (Task t : lists) {
                if (t.getTask().contains(keyword)) {
                    System.out.println(lists.indexOf(t) + 1 + ". " + t.toString());
                }
            }
        } catch (Exception e) {
            DukeException.cannotRecognise(keyword);
        }
    }
}
