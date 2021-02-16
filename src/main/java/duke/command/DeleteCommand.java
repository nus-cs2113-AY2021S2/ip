package duke.command;

import duke.main.DukeException;
import duke.task.TaskManager;

public class DeleteCommand implements Command {
    public DeleteCommand(String input) {
    }

    public void execute(String input) {
        String[] command = input.trim().split(" ");
        int num = 0;
        try {
            num = Integer.parseInt(command[1]);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(TaskManager.tasks.get(num - 1));
            TaskManager.tasks.remove(num - 1);
            TaskManager.numOfTasks -= 1;
            System.out.println("Now you have " + TaskManager.numOfTasks + " tasks in the list.");
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

