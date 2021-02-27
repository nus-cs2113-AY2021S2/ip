package duke.commands;

import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

public class AddCommand extends Command{

    public AddCommand(String activity, String action){
        super(activity, action);
    }

    public void execute(TaskList tasks, Storage storage) throws DukeException {
        switch(activity) {
        case "todo":
            tasks.createTodoTask(action);
            storage.dump(tasks);
            break;
        case "deadline":
            tasks.createDeadlineTask(action);
            storage.dump(tasks);
            break;
        case "event":
            tasks.createEventTask(action);
            storage.dump(tasks);
            break;
        default:
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
