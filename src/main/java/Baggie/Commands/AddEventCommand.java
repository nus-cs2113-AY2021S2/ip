package Baggie.Commands;

import Baggie.Baggie;
import Baggie.UI.PrintMessages;
import Baggie.Task.EventTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static Baggie.UI.PrintMessages.taskDescriptionEmpty;
import static Baggie.UI.PrintMessages.timeWrongFarmat;
import static Baggie.UI.TEXT.*;

public class AddEventCommand extends Baggie {
    public static boolean timeCorrect = false;

    /**
     * Adds Event Task into the list.
     * Shows error if theres no time input.
     *
     * @param taskDescription Task description of the event task.
     */public static void execute(String taskDescription) {
        try {
            String[] taskAndTime = taskDescription.split(TIME_DIVIDER, 2);
            String task = taskAndTime[0].trim();
            String time = taskAndTime[1].trim();
            if (task.equals(NULL)) {
                taskDescriptionEmpty();
            }
            if (time.equals(NULL)) {
                throw new Exception();
            }
            String parsedTime = timeFormatCheck(time);
            if (timeCorrect) {
                time = parsedTime;
                lists.add(new EventTask(task, time));
                PrintMessages.taskAddedText();
                taskCount++;
            } else {
                timeWrongFarmat();
            }
        } catch (Exception e) {
            PrintMessages.taskWithoutTime();
        }
    }

    /**
     * Checks whether the time format is correct before adding a task.
     * Sets boolean variable 'timeCorrect' if time is correct.
     * Returns time in correct format if applicable.
     *
     * @param time Time information from input in String.
     * @return Time in String.
     */
    public static String timeFormatCheck(String time) {
        try {
            LocalDate parsedDate = LocalDate.parse(time);
            time = parsedDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            timeCorrect = true;
        } catch (DateTimeParseException e) {
            timeCorrect = false;
        }
        return time;
    }
}