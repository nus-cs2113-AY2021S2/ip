package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private Command cmd;

    public Command getCmd() {
        return cmd;
    }

    public Parser() {
        cmd = null;
    }
    public void parse(String userInputString) {
        if(userInputString.equalsIgnoreCase("HELP")) {
            cmd =  new HelpCmd(userInputString);
        }else if(userInputString.equalsIgnoreCase("LIST")) {
            cmd =  new ListCmd(userInputString);
        } else if(userInputString.toUpperCase().matches("^(DONE).*$")) {
            cmd = new DoneCmd(userInputString);
        } else if (userInputString.toUpperCase().matches("^(DELETE).*$")) {
            cmd = new DeleteCmd(userInputString);
        }else if (userInputString.toUpperCase().matches("^(TODO).*$")) {
            cmd = new TodoCmd(userInputString);
        } else if (userInputString.toUpperCase().matches("^(DEADLINE).*$")) {
            cmd = new DeadlineCmd(userInputString);
        } else if (userInputString.toUpperCase().matches("^(EVENT).*$")) {
            cmd = new EventCmd(userInputString);
        } else if (userInputString.equalsIgnoreCase("BYE")) {
            cmd = new ExitCmd(userInputString);
        } else {
            cmd = null;
        }

    }

    public static LocalDate parseDate(String dateStr) throws DateTimeParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.parse(dateStr, dateFormatter);
    }
}
