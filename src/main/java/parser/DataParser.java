package parser;

import models.Dates;
import models.Deadline;
import models.Event;
import models.TaskList;
import models.Todo;

import java.text.DateFormat;
import java.text.ParseException;

public class DataParser {

    private final DateFormat inputFormat = Dates.inputFormat;
    private TaskList taskList;
    private int taskCount;

    public DataParser(TaskList taskList, int taskCount) {
        this.taskList = taskList;
        this.taskCount = taskCount;
    }

    public void parseData(String line) throws ParseException {
        String[] tokens = line.split("#");

        switch (tokens[0]) {
        case "T":
            try {
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                Todo todo = new Todo(tokens[2], isDone);
                taskList.add(todo);
                taskCount++;
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case "D":
            try {
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                Deadline deadline = new Deadline(tokens[2], inputFormat.parse(tokens[3]), isDone);
                taskList.add(deadline);
                taskCount++;
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case "E":
            try {
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                Event event = new Event(tokens[2], inputFormat.parse(tokens[3]), isDone);
                taskList.add(event);
                taskCount++;
            } catch (NumberFormatException e) {
                return;
            }
            break;
        default:
            System.out.println("Invalid data!");
            break;
        }
    }
}
