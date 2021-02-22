package duke;

import duke.Command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Parser {

    public Ui ui = new Ui();
    public String[] lineParts;
    public String keyCommand;
    public String taskDescription;
    public String taskDate;
    public int taskIndex;

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
                taskDate = fullLine.substring(byIndex + 4);
            } catch (IndexOutOfBoundsException e) {
                ui.showDeadlineByError();
            } catch (Exception e) {
                ui.showError(e);
            }
            break;
        case "event":
            try {
                int atIndex = fullLine.indexOf("/at");
                taskDescription = fullLine.substring(6, atIndex);
                taskDate = fullLine.substring(atIndex + 4);
            } catch (IndexOutOfBoundsException e) {
                ui.showEventAtError();
            } catch (Exception e) {
                ui.showError(e);
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
