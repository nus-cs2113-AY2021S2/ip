package Baggie.Commands;

import Baggie.Baggie;
import Baggie.Exceptions.IllegalKeywordException;
import Baggie.UI.PrintMessages;
import Baggie.Task.*;

public class FindTaskCommand extends Baggie {
    private static final String DOT = ". ";

    /**
     * Finds tasks on a specific date.
     * Shows error if theres no task on the date.
     *
     * @param date Date of the tasks to be found.
     */
    public static void findWithDate(String date) {
        boolean found = false;
        int taskCount = 0;
        for (Task task : lists) {
            if (task instanceof EventTask || task instanceof DeadlineTask) {
                if (task.getTaskTime().contains(date)) {
                    System.out.println(lists.indexOf(task) + 1 + DOT + task.toString());
                    found = true;
                    taskCount++;
                }
            }
        }
        if (!found) {
            PrintMessages.cannotFind(date);
        } else {
            PrintMessages.printTaskCount(taskCount, date);
        }
    }

    /**
     * Finds tasks with a specific keyword.
     * Shows error if theres no task containing the keyword.
     *
     * @param keyword Keyword of the tasks to be found.
     */
    public static void findWithKeyword(String keyword) throws IllegalKeywordException {
        boolean found = false;
        int taskCount = 0;
        for (Task task : lists) {
            if (task.getTask().contains(keyword)) {
                System.out.println(lists.indexOf(task) + 1 + DOT + task.toString());
                found = true;
                taskCount++;
            }
        }
        if (!found) {
            throw new IllegalKeywordException();
        } else {
            PrintMessages.printTaskCount(taskCount, keyword);
        }

    }
}
