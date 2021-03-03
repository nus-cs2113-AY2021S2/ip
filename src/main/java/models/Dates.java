package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Dates {
    /**
     * Format of Date that the User inputs
     */
    public static final DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    /**
     * Format of Date that the System outputs
     */
    public static final DateFormat outputFormat = new SimpleDateFormat("dd 'of' MMMMMMMMM yyyy, hh:mm aaa");
}
