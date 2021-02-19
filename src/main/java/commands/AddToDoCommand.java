package commands;

import errors.ListFullException;
import tasks.ToDoTask;

public class AddToDoCommand extends Command {

    private String input;

    public AddToDoCommand(String input) {
        this.input = input;
    }


    /**
     * Creates new ToDoTask.
     */
    @Override
    public void execute() {
        try {
            if (taskManager.getTaskCount() >= constants.MAX_SIZE) {
                //Array full
                throw new ListFullException();
            }
            input = input.substring(constants.COMMAND_TODO.length());
            taskManager.addTask(new ToDoTask(input));
            taskManager.setTaskCount(taskManager.getTaskCount()+1);
            printAddedContent(input);
            updateFile();
        } catch (ListFullException e) {
            System.out.println(constants.MESSAGE_LIST_FULL);
        }
    }
}
