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
    public static String initialFormat = "yyyy/MM/dd HHmm";
    public static String finalFormat = "MM-dd-yyy HH:mm";

    public static String reformatInput(String by) {
        SimpleDateFormat formatter = new SimpleDateFormat(initialFormat);

        boolean isFormatInCorrect;
        Date inputDate;
        do {
            try {
                inputDate = formatter.parse(by.trim());
                formatter.applyPattern(finalFormat);
                return formatter.format(inputDate);
            } catch (ParseException e) {
                UI.printInsertCorrectDate();
                Scanner in = new Scanner(System.in);
                by = in.nextLine();
                isFormatInCorrect = true;
            }
        } while (isFormatInCorrect);
        return EMPTY_STRING;
    }

    public static String reformatOutput(String by) {
        if (by.equals(Deadline.NO_BY) || by.equals(Event.NO_BY)) {
            return EMPTY_STRING;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(finalFormat);
        boolean isFormatInCorrect;
        Date inputDate;
        do {
            try {
                inputDate = formatter.parse(by);
                formatter.applyPattern(initialFormat);
                return formatter.format(inputDate);
            } catch (ParseException e) {
                isFormatInCorrect = true;
            }
        } while (isFormatInCorrect);
        return EMPTY_STRING;

    }

}
