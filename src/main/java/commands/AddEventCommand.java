package commands;

import io.DukePrint;
import models.Event;
import models.TaskList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddEventCommand extends AddCommand {

    public AddEventCommand(TaskList taskList, DukePrint dukePrint,
                           String name, String date) throws ParseException {
        super(taskList, dukePrint);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        taskToAdd = new Event(name, dateFormat.parse(date));
    }
}
