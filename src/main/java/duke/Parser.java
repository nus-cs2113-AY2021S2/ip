package duke;

import duke.Command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public Ui ui = new Ui();
    /** Main command to execute */
    public String keyCommand;
    /** Description of the task to add*/
    public String taskDescription;
    /** Keyword to find among the task list */
    public String keyword;
    /** Date and time for deadlines and events */
    public LocalDateTime taskDate;
    /** The task index to mark as done or delete */
    public int taskIndex;
    public String dateTimeFormat = "yyyy-MM-dd HH:mm";
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);

    /**
     * Extracts information from the full user input depending on what the main command is.
     * The information is stored in class variables which can be utilised in the parse method.
     *
     * @param fullLine  Full input from user
     */
    public void extractInfo(String fullLine) {
        String[] lineParts;
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
        case "find":
            keyword = fullLine.replace("find", "").trim();
            break;
        }
    }

    /**
     * Returns the command to execute based on the user input.
     *
     * @param fullLine Full input from user.
     * @return Command to execute
     */
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
        case "find":
            command = new FindCommand(keyword);
            break;
        case "bye":
            command = new ExitCommand();
            break;
        }
        return command;
    }
}
