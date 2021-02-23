package command.parser;

import duke.date.DukeDate;
import exceptions.IllegalCommandException;

import task.list.Deadline;
import task.list.Event;
import task.list.TaskList;
import ui.UI;

import java.util.ArrayList;

/**
 * Represents the search command
 */
public class SearchCommand extends Command {

    // search events and dates that matches date input in search command
    public static void searchTasksDates(ArrayList<TaskList> tasks, String[] sentence) throws IllegalCommandException {
        if (sentence.length != NUMBER_OF_COMMAND_ARGUMENTS) {
            throw new IllegalCommandException();
        }
        String date = DukeDate.getSearchDateFormat(sentence[DESCRIPTION_INDEX_IN_COMMANDS]);
        int counter = 0;
        printMatchFound(tasks, date, counter);
    }

    // Prints matches obtained
    private static void printMatchFound(ArrayList<TaskList> tasks, String date, int counter) {
        for (TaskList task : tasks) {
            counter = incrementCounter(date, counter, task);
        }
        if (counter == EMPTY) {
            UI.printNoMatchFound();
        } else {
            UI.printSearchComplete();
        }
    }

    // Increments counter if a match is found
    private static int incrementCounter(String date, int counter, TaskList task) {
        String taskDate;
        if (task instanceof Event) {
            Event temp = (Event) task;
            taskDate = DukeDate.reformatsSearch(temp.getTaskBy());
            if (taskDate.equals(date)) {
                counter++;
                temp.printTask();
            }

        } else if (task instanceof Deadline) {
            Deadline temp = (Deadline) task;
            taskDate = DukeDate.reformatsSearch(temp.getTaskBy());
            if (taskDate.equals(date)) {
                counter++;
                temp.printTask();
            }
        }
        return counter;
    }
}
