package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Dates {
    public static final DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static final DateFormat outputFormat = new SimpleDateFormat("dd 'of' MMMMMMMMM yyyy, hh:mm aaa");
}
