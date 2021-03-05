package duke.commands;

import java.time.LocalDate;

import duke.ui.TextUI;
import duke.tasks.TaskList;
import duke.tasks.Deadline;

/**
 * Command class for deadlines. 
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String description;
    private final LocalDate date;

    /**
     * Constructor for the <code>DeadlineCommand</code> class. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param description description of deadline task
     * @param date due date of the deadline task
     */
    public DeadlineCommand(TaskList taskList, String description, LocalDate date) {
        super(taskList);
        this.description = description;
        this.date = date;
    }

    /**
     * Execute the deadline command. 
     * 
     * @param taskList tasklist instance on which the command is to be executed
     * @param ui ui instance for the text to be displayed on
     */
    @Override
    public void execute(TaskList taskList, TextUI ui) {
        taskList.addTask(new Deadline(description, date));
    }
}