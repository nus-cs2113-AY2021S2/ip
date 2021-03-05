package Baggie.Commands;

import Baggie.Baggie;
import Baggie.Task.ToDoTask;
import Baggie.UI.PrintMessages;

public class AddTodoCommand extends Baggie {
    /**
     * Adds Todo Task into the list
     * @param taskDescription
     */
    public static void execute(String taskDescription) {
        lists.add(new ToDoTask(taskDescription));
        PrintMessages.taskAddedText();
        taskCount++;
    }
}
