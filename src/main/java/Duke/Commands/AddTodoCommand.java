package Duke.Commands;

import Duke.Duke;
import Duke.Task.ToDoTask;
import Duke.UI.PrintMessages;

public class AddTodoCommand extends Duke {
    /**
     * Add Todo Task into the list
     * @param taskDescription
     */
    public static void execute(String taskDescription) {
        lists.add(new ToDoTask(taskDescription));
        PrintMessages.taskAddedText();
        taskCount++;
    }
}
