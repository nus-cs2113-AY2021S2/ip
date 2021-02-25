package duke.command;

import duke.Ui;
import duke.main.DukeException;
import duke.task.Task;
import duke.TaskList;

public class DeleteCommand implements Command {
    public DeleteCommand(String input) {
    }

    public void execute(String input) throws DukeException {

        String[] command = input.trim().split(" ");

        if (command.length == 1) { //to check if index of task to be deleted is given
            throw new DukeException("OOPS!!! You missed out the index of the task you want to delete.");
        }

        int num = 0;

        try {
            num = Integer.parseInt(command[1]);

            if (num > TaskList.numOfTasks || num < 1) {
                throw new DukeException("Error: no such task index");
            }

            Task task = TaskList.getTask(num - 1);
            TaskList.deleteTask(task);
            Ui.taskDeleted(task);
        } catch (NumberFormatException e) { //to check if the word after done is a number
            System.out.println("Error: input is not a number.");
        } catch (NullPointerException e) { //to check if the number after done is a valid number for a task
            System.out.println("Error: input is invalid");
        }

        end();
    }

    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }
}

