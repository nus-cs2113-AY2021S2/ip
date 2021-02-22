package duke.date;

import task.list.Deadline;
import task.list.Event;
import ui.UI;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class DukeDate {
    public static final String EMPTY_STRING = "";
    public static String INITIAL_FORMAT = "yyyy/MM/dd HHmm";
    public static String FINAL_FORMAT = "MM-dd-yyy HH:mm";
    public static String SEARCH_FORMAT = "yyyy/MM/dd";

    // reformats date to store as by
    public static String reformatInput(String by) {
        SimpleDateFormat formatter = new SimpleDateFormat(INITIAL_FORMAT);

        boolean isFormatInCorrect;
        Date inputDate;
        do {
            try {
                inputDate = formatter.parse(by.trim());
                formatter.applyPattern(FINAL_FORMAT);
                return formatter.format(inputDate);
            } catch (ParseException e) {
                UI.printInsertCorrectDateTask();
                UI.printDottedLines();
                Scanner in = new Scanner(System.in);
                by = in.nextLine();
                isFormatInCorrect = true;
            }
        } while (isFormatInCorrect);
        return EMPTY_STRING;
    }

    // reformats by to store as date in file
    public static String reformatOutput(String by) {
        if (by.equals(Deadline.NO_BY) || by.equals(Event.NO_BY)) {
            return EMPTY_STRING;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(FINAL_FORMAT);
        boolean isFormatInCorrect;
        Date inputDate;
        do {
            try {
                inputDate = formatter.parse(by);
                formatter.applyPattern(INITIAL_FORMAT);
                return formatter.format(inputDate);
            } catch (ParseException e) {
                isFormatInCorrect = true;
            }
        } while (isFormatInCorrect);
        return EMPTY_STRING;

    }

    // returns correct format of the date to search
    public static String getSearchDateFormat(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(SEARCH_FORMAT);
        boolean isFormatInCorrect;
        Date inputDate;
        do {
            try {
                inputDate = formatter.parse(date.trim());
                return formatter.format(inputDate);
            } catch (ParseException e) {
                UI.printInsertCorrectDateSearch();
                UI.printDottedLines();
                Scanner in = new Scanner(System.in);
                date = in.nextLine();
                isFormatInCorrect = true;
            }
        } while (isFormatInCorrect);
        return EMPTY_STRING;
    }

    // reformats date of task to that needed by search command
    public static String reformatsSearch(String date) {
        if (date.equals(Deadline.NO_BY) || date.equals(Event.NO_BY)) {
            return EMPTY_STRING;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(FINAL_FORMAT);
        Date dateParsed;
        try {
            dateParsed = formatter.parse(date);
        } catch (ParseException e) {
            return EMPTY_STRING;
        }
        formatter.applyPattern(SEARCH_FORMAT);
        return formatter.format(dateParsed);
    }
}
