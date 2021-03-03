package jarvis.commands;

import jarvis.Duke;
import jarvis.exception.EmptyTaskIdException;
import jarvis.exception.InvalidTaskException;
import jarvis.parser.Parser;
import jarvis.task.Task;
import jarvis.task.TaskList;

public class Done extends Command {

    /**
     * Marks a task as done if it exists
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
        task.setTaskStatus(true);
        System.out.println("\tWell done, sir! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
        Duke.jarvis.printDivider();
    }
}
