package duke;


import duke.taskexceptions.EmptyTaskDateException;
import duke.taskexceptions.NoTaskDateException;
import duke.taskexceptions.NoTaskNameException;
import duke.taskexceptions.TaskDateFormatException;
import duke.taskexceptions.KeywordFormatException;

import java.io.IOException;

/**
 * Main Duke Class: This class controls the flow for the entire program which manages a User's tasks.
 * When 'bye' command is executed, entire program stops immediately
 * and program exits with status code -1.
 */
public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Takes in a filepath given by User
     * and load the older list from the <code>Storage</code> object containing the filepath
     * into an empty list which stores the User's tasks
     *
     * @param filePath filepath given by User storing all the User's tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadPrevListIntoNewList());
        } catch (NullPointerException e) {
            ui.showNullPointerError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showCreateFileError();
            tasks = new TaskList();
        }
    }

    /**
     * Gives instructions for program to interact with User,
     * store tasks given by User or execute other (valid) User's command.
     */
    public void run() {
        ui.saysHiToUser();
        boolean isExit = false;
        while (!isExit) {

            try {
                storage.overwriteDukeListFile(); //update DukeList (stores all latest tasks)
                String fullCommand = ui.readCommand(); //take in User's Command
                ui.showLine(); // start of current Response to User
                Parser.parse(fullCommand); //parse User's Command
                isExit = Command.execute(tasks, ui); //Check if command = 'bye' & Execute the current command
            } catch (NoTaskNameException e) {
                System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
            } catch (TaskDateFormatException e) {
                System.out.println("Please add date in the format of \"Task /by Date\" for Deadlines or \"Task /at Date\" for Events."); //wrong date format
            } catch (NoTaskDateException e) {
                System.out.println("Please add a date to the task description. :))"); //missing date
            } catch (EmptyTaskDateException e) {
                System.out.println("Please add a date to the task description. :))"); //empty date
            } catch (NumberFormatException e) {
                System.out.println("Please input in the format of \'done taskNumber OR delete taskNumber\'"); //wrong format
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please input a smaller or bigger valid task number.");
                System.out.println("You can list all tasks to check the total number of tasks you have. :))"); //invalid TaskNumber
            } catch (KeywordFormatException e) {
                System.out.println("Please input in the format of: find \'keyword\'"); //wrong format
            } finally {
                ui.showLine(); //end of current Response to User
            }

        }

    }

    /**
     * Loads older task data from the list in the given filepath
     * Then, proceeds to run the entire program.
     * Otherwise, creates a file (embedded in the created directory) from filepath and uses the file for storage of tasks.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/dukeList.txt").run();
    }

}
