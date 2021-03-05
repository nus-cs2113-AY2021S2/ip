package duke.commands;

import java.time.LocalDate;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.tasks.Event;

/**
 * Command class for events. 
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final String description;
    private final LocalDate date;

    /**
     * Constructor for the <code>EventCommand</code> class. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param description description of the event task
     * @param date date of the event
     */
    public EventCommand(TaskList taskList, String description, LocalDate date) {
        super(taskList);
        this.description = description;
        this.date = date;
    }

    /**
     * Execute the event command
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.addTask(new Event(description, date));
    }
}