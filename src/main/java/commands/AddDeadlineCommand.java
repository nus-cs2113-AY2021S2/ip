package commands;

import io.DukePrint;
import models.Deadline;
import models.TaskList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddDeadlineCommand extends AddCommand {

    public AddDeadlineCommand(TaskList taskList, DukePrint dukePrint,
                              String name, String date) throws ParseException {
        super(taskList, dukePrint);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        taskToAdd = new Deadline(name, dateFormat.parse(date));
    }
}