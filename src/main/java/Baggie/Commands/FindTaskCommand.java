package Baggie.Commands;

import Baggie.Baggie;
import Baggie.Exceptions.IllegalKeywordException;
import Baggie.UI.PrintMessages;
import Baggie.Task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Baggie.UI.TEXT.DATE_FORMAT;

public class FindTaskCommand extends Baggie {
    private static final String DOT = ". ";

    /**
     * Finds tasks on a specific date
     * Shows error if theres no task on the date
     * @param date
     */
    public static void FindWithDate(String date) {
        try {
            int taskCount = 0;
            LocalDate parsedDate = LocalDate.parse(date);
            String formattedDate = parsedDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            for (Task task : lists) {
                if (formattedDate.equals(task.getTaskTime())) {
                    System.out.println(lists.indexOf(task) + 1 + DOT + task.toString());
                    taskCount++;
                }
            }
            PrintMessages.printTaskCount(taskCount);
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
            int taskCount = 0;
            for (Task task : lists) {
                if (task.getTask().contains(keyword)) {
                    System.out.println(lists.indexOf(task) + 1 + DOT + task.toString());
                    found = true;
                    taskCount++;
                }
            }
            PrintMessages.printTaskCount(taskCount);
            if (!found) {
                throw new IllegalKeywordException();
            }
        } catch (IllegalKeywordException e) {
            PrintMessages.cannotFind(keyword);
        }
    }
}
