package parser;

import models.Dates;
import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.text.DateFormat;
import java.text.ParseException;

public class DataParser {

    private final DateFormat inputFormat = Dates.inputFormat;

    public Task parseData(String line) throws ParseException {
        String[] tokens = line.split("#");
        Task parsedTask = null;

        switch (tokens[0]) {
        case "T":
            boolean isDone = Integer.parseInt(tokens[1]) == 1;
            parsedTask = new Todo(tokens[2], isDone);
            break;
        case "D":
            isDone = Integer.parseInt(tokens[1]) == 1;
            parsedTask = new Deadline(tokens[2], inputFormat.parse(tokens[3]), isDone);
            break;
        case "E":
            isDone = Integer.parseInt(tokens[1]) == 1;
            parsedTask = new Event(tokens[2], inputFormat.parse(tokens[3]), isDone);
        default:
            break;
        }
        return parsedTask;
    }
}
