package duke.command;

import duke.task.*;
import duke.main.*;
import java.util.Arrays;

public class AddCommand implements Command {
    public AddCommand(String input) {
    }

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    public void execute(String input) throws DukeException {
        String[] command = input.trim().split(" ");
        String action = command[0];

        if (!action.equals(TODO) && !action.equals(DEADLINE) && !action.equals(EVENT)) { //to check if the action required is recognised
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (command.length == 1) { //to check if there is more than just a recognised action for todo, deadline and event commands
            throw new DukeException("OOPS!!! The description of a " + action + " cannot be empty.");
        }

        switch (action) {
            case TODO:
                Todo todoTask = new Todo(getDescription(input));
                addTask(todoTask);
                break;
            case DEADLINE:
                Deadline deadlineTask = new Deadline(getDescription(input), getBy(input));
                addTask(deadlineTask);
                break;
            case EVENT:
                Event eventTask = new Event(getDescription(input), getOn(input));
                addTask(eventTask);
                break;
            default:
                System.out.println("This function is not recognized!");
        }
        end();
    }

    public String getDescription(String input) {
        if (input.contains("/")) {
            String[] split = input.trim().split("/");
            String[] commandPlusDescription = split[0].trim().split(" ");
            return splitCommand(commandPlusDescription);
        } else {
            String[] commandPlusDescription = input.trim().split(" ");
            return splitCommand(commandPlusDescription);
        }
    }

    private String splitCommand(String[] commandPlusDescription) {
        String[] description = new String[commandPlusDescription.length - 1];
        for (int i = 1; i < commandPlusDescription.length; i++) {
            description[i - 1] = commandPlusDescription[i];
        }
        return Arrays.toString(description).replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

    public String getBy(String input) {
        String[] split = input.trim().split("/by");
        return split[1];
    }

    public String getOn(String input) {
        String[] split = input.trim().split("/at");
        return split[1];
    }

    private void addTask(Task t) {
        TaskManager.tasks.add(t);
        TaskManager.numOfTasks++;
        System.out.println("Got it. I've added this task:" + System.lineSeparator() + t.toString());
        System.out.println("Now you have " + TaskManager.numOfTasks + " task in the list.");
    }
    public static void end() {
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }
}
