package duke.command;

import duke.taslist.TaskList;
import duke.ui.Ui;


/**
 * Find out whether there are tasks in the taskList with the given keyword
 */
public class FindCommand implements Command {

    public FindCommand(String input){
    }

    /**
     * Find out whether there are tasks in the taskList with the given keyword
     * Prints the tasks if any or no such tasks found if there is none
     *
     * @param input This is the command input by the user eg. find read book
     */
    public void execute(String input) {
        if (input.length() == 4) {
            System.out.println("OOPS!!! You forgot to give the keyword!!!");
            Ui.commandDone();
            return;
        }
        String[] keyword = input.split(" ", 2);
        if (TaskList.numOfTasks == 0) {
            System.out.println("You have no task! :)");
        }
        else {
            int count = 0;
            int i = 1;
            while (count < TaskList.numOfTasks) {
                if (TaskList.findTask(keyword[1], count)) {
                    System.out.println(i + ": " + TaskList.getTask(count));
                    i++;
                }
                count++;
            }
            if (i == 1) {
                System.out.println("There is no such task!");
            }
        }
        Ui.commandDone();
    }
}
