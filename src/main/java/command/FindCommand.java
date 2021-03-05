package command;

import task.Task;
import task.TaskList;
import java.util.Iterator;

/**
 * Represents the command of searching within task list
 */
public class FindCommand extends Command{
    public static final String COMMAND_WORD = "find";
    public static final String ERROR_MESSAGE = "Invalid input format for find command.\n" +
            PRE_SPACE + "The syntax for searching the task list for tasks whose names contain the " +
            "keyword is: find <keyword>    Eg. find book";
    private final String FEEDBACK_FORMAT = "Here are the matching tasks in your list:";
    private String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
        feedback = FEEDBACK_FORMAT;
    }

    /**
     * Searches tasks whose names contain the keyword in task list.
     *
     * @return the feedback message and the list of tasks that satisfy the requirement
     */
    @Override
    public CommandResult execute(){
        TaskList relevantTasks = new TaskList();
        Iterator<Task> iterator = tasks.getIterator();
        while(iterator.hasNext()){
            Task currentTask = iterator.next();
            if(currentTask.getName().contains(keyword)){
                relevantTasks.addTask(currentTask);
            } else {
                relevantTasks.addTask(null);
            }
        }
        return new CommandResult(feedback, relevantTasks);
    }
}
