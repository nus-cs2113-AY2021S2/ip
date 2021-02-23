package duke;

import duke.Command.AddCommand;
import duke.Command.Command;
import duke.Command.DefaultCommand;
import duke.Command.DeleteCommand;
import duke.Command.DoneCommand;
import duke.Command.ExitCommand;
import duke.Command.HelpCommand;
import duke.Command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public Ui ui = new Ui();
    public String[] lineParts;
    public String keyCommand;
    public String taskDescription;
    public LocalDateTime taskDate;
    public int taskIndex;
    public String dateTimeFormat = "yyyy-MM-dd HH:mm";
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);

    public void extractInfo(String fullLine) {
        lineParts = fullLine.split(" ");
        keyCommand = lineParts[0];
        switch (keyCommand) {
        case "todo":
            taskDescription = fullLine.replace("todo", "").trim();
            break;
        case "deadline":
            try {
                int byIndex = fullLine.indexOf("/by");
                // takes the description from the start of the description to where /by is
                taskDescription = fullLine.substring(9, byIndex);
                taskDate = LocalDateTime.parse(fullLine.substring(byIndex + 4), formatter);
            } catch (IndexOutOfBoundsException e) {
                ui.showDeadlineByError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeHint(dateTimeFormat);
            }
            break;
        case "event":
            try {
                int atIndex = fullLine.indexOf("/at");
                taskDescription = fullLine.substring(6, atIndex);
                taskDate = LocalDateTime.parse(fullLine.substring(atIndex + 4), formatter);
            } catch (IndexOutOfBoundsException e) {
                ui.showEventAtError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeHint(dateTimeFormat);
            }
            break;
        case "done":
            try {
                taskIndex = Integer.parseInt(lineParts[1]) - 1;
            } catch (NumberFormatException e) {
                ui.showInvalidNumber(lineParts[1]);
            } catch (IndexOutOfBoundsException e) {
                ui.showDoneEmptyError();
            } catch (Exception e) {
                ui.showError(e);
            }
        case "delete":
            try {
                taskIndex = Integer.parseInt(lineParts[1]) - 1;
            } catch (NumberFormatException e) {
                ui.showInvalidNumber(lineParts[1]);
            } catch (Exception e) {
                ui.showError(e);
            }
        }
    }

    public Command parse(String fullLine) {
        Command command = new DefaultCommand(fullLine);
        extractInfo(fullLine);
        switch (keyCommand) {
        case "todo":
            Todo todo = new Todo(taskDescription, false);
            command = new AddCommand(todo);
            break;
        case "deadline":
            Deadline deadline = new Deadline(taskDescription, taskDate, false);
            command = new AddCommand(deadline);
            break;
        case "event":
            Event event = new Event(taskDescription, taskDate, false);
            command = new AddCommand(event);
            break;
        case "-h":
            command = new HelpCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "done":
            command = new DoneCommand(taskIndex);
            break;
        case "delete":
            command = new DeleteCommand(taskIndex);
            break;
        case "bye":
            command = new ExitCommand();
        }
        return command;
    }
}
