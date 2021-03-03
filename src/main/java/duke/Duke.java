package duke;

import duke.taskexceptions.EmptyTaskDateException;
import duke.taskexceptions.NoTaskDateException;
import duke.taskexceptions.NoTaskNameException;
import duke.taskexceptions.TaskDateFormatException;

import java.io.IOException;

public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadPrevListIntoNewList());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (NullPointerException e) {
            ui.showNullPointerError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.saysHiToUser();
        boolean isExit = false;
        while(!isExit) {

            try {
                storage.overwriteDukeListFile(); //update DukeList (stores all latest tasks)
                String fullCommand = ui.readCommand(); //take in User's Command
                ui.showLine(); // start of current Response to User
                Parser.parse(fullCommand); //parse User's Command
                isExit = Command.execute(tasks, ui, storage); //Check if command = 'bye' & Execute the current command
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
            } finally {
                ui.showLine(); //end of current Response to User
            }

        }

    }

    public static void main(String[] args) {
        new Duke("data/dukeList.txt").run();
    }

}
