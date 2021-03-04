package duke.command;

import duke.TaskList;
import duke.Ui;

import duke.error.EmptyNameFieldException;

import duke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private static final int ERR_NO_NAME = -4;

    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    public FindCommand(String input, TaskList taskList, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void execute(Ui ui) {
        try {
            ArrayList<Task> tasks = taskList.getTasks();
            ArrayList<Task> foundItems = findItems(this.input, tasks);
            this.ui.printFoundItems(foundItems);
        } catch (EmptyNameFieldException e) {
            this.ui.printError(ERR_NO_NAME);
        }
    }

    /**
     * Searches the list for tasks containing the substring given by the user.
     *
     * @param line user input, substring to search for.
     * @param tasks list of tasks to search.
     * @return ArrayList of items found with substring.
     * @throws EmptyNameFieldException if name is not given or is all whitespace.
     */
    public ArrayList<Task> findItems(String line, ArrayList<Task> tasks) throws EmptyNameFieldException {
        String searchName = extractSearchName(line);
        ArrayList<Task> foundItems = new ArrayList<>();
        for(int i = 0; i < Task.totalNumberOfTasks; i++) {
            if (tasks.get(i).getName().contains(searchName)) {
                foundItems.add(tasks.get(i));
            }
        }
        return foundItems;
    }

    /**
     * Extracts the substring user wants to search from the entire input.
     *
     * @param line user input.
     * @return substring to search.
     * @throws EmptyNameFieldException if name is not given or is all whitespace.
     */
    public String extractSearchName(String line) throws EmptyNameFieldException {
        String searchName = line.substring(4).trim();
        if (searchName.length() == 0) {
            throw new EmptyNameFieldException();
        }
        return searchName;
    }
}
