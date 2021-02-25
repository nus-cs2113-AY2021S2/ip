package duke.command;

import duke.TaskList;
import duke.main.DukeException;

public class DoneCommand implements Command {
    public DoneCommand(String input) {
    }

    public void execute(String input) throws DukeException {
        String[] command = input.trim().split(" ");

        if (command.length == 1) { //to check if index of task that is done is given
            throw new DukeException("OOPS!!! You missed out the index of the task you have done.");
        }

        int num = 0;

        try {
            num = Integer.parseInt(command[1]);
            if (num > TaskList.numOfTasks || num < 1) {
                throw new DukeException("Error: no such task index");
            }
            if (TaskList.getTask(num - 1).isDone == false) {
                TaskList.getTask(num - 1).setAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(TaskList.getTask(num - 1));
            }
            else {
                System.out.println("Task is already done!");
            }
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
