package duke.commands;

import java.time.LocalDate;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.tasks.Event;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final String description;
    private final LocalDate date;

    public EventCommand(TaskList taskList, String description, LocalDate date) {
        super(taskList);
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.addTask(new Event(description, date));
    }
}