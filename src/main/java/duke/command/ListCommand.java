package duke.command;

import duke.Keyword;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Class to handle the list command
 */
public class ListCommand extends Command {

    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Carries out the list command and prints out all the tasks in the list
     * And whether the tasks have been completed
     * @param taskList the Task List object which has the current tasks
     * @param ui The Ui object for user to interact with
     * @throws IndexOutOfBoundsException If the user entered "list" when it is empty
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IndexOutOfBoundsException {

        if (!isValidInput()) {
            Ui.printCommandIsInvalid();
            return;
        }
        if (TaskList.getListSize() == 0) {
            Ui.printListIsEmpty();
            return;
        }
        for (int i = 1; i <= TaskList.getListSize(); ++i) {
            System.out.print(i + ". ");
            printList(taskList, i);
        }
    }

    public static void printList(TaskList taskList, int index) {
        switch (Keyword.getKeywords(index - 1)) {
        case "T":
            System.out.print("[T]");
            taskList.getTaskAtIndex(index - 1).printDescription();
            System.out.print("\n");
            break;
        case "D":
            System.out.print("[D]");
            taskList.getTaskAtIndex(index - 1).printDescription();
            System.out.println("(by:" + Deadline.getBy() + ")");
            break;
        case "E":
            System.out.print("[E]");
            taskList.getTaskAtIndex(index - 1).printDescription();
            System.out.println("(at:" + Event.getAt() + ")");
            break;
        default:
            System.out.println("Oops! An error occurred");
            break;
        }
    }

    @Override
    public boolean isValidInput() {
        if (fullCommand.length() == 4) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
