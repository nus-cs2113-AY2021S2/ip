package jarvis.commands;

import jarvis.Duke;
import jarvis.exception.EmptyTaskIdException;
import jarvis.exception.InvalidTaskException;
import jarvis.parser.Parser;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class Delete extends Command{

    /**
     * Removes a task from the list if it exist
     *
     * @param command command entered by the user
     * @throws EmptyTaskIdException if task number is not stated by the user
     * @throws InvalidTaskException if the task is not in the list
     */
    public static void execute(String command) throws InvalidTaskException, EmptyTaskIdException {
        int taskNumber = Integer.parseInt(Parser.parseToGetTaskId(command));
        if (taskNumber > TaskList.getSize()) {
            throw new InvalidTaskException();
        }
        Task task = TaskList.getTaskWithIndex(taskNumber - 1);
        TaskList.removeFromTasks(task);
        System.out.println("\tNoted, sir! I've removed this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + TaskList.getSize() + " tasks in the list.");
        Duke.jarvis.printDivider();
    }
}
