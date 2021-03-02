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
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.saysHiToUser();
        boolean isExit = false;
        while(!isExit) {

            storage.overwriteDukeListFile();
            String fullCommand = ui.readCommand(); //take in User's command

            ui.showLine(); // start of current Response to User
            try {
                Parser.parse(fullCommand);
            } catch (NoTaskNameException e) {
                System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
                ui.showLine(); //end of current Response to User
                continue;
            } catch (TaskDateFormatException e) {
                System.out.println("Please add date in the format of \"Task /by Date\" for Deadlines or \"Task /at Date\" for Events."); //wrong date format
                ui.showLine(); //end of current Response to User
                continue;
            } catch (NoTaskDateException e) {
                System.out.println("Please add a date to the task description. :))"); //missing date
                ui.showLine(); //end of current Response to User
                continue;
            } catch (EmptyTaskDateException e) {
                System.out.println("Please add a date to the task description. :))"); //empty date
                ui.showLine(); //end of current Response to User
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Please input in the format of \'done taskNumber OR delete taskNumber\'"); //wrong format
                ui.showLine(); //end of current Response to User
                continue;// for TaskNumber
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please input a smaller or bigger valid task number.");
                System.out.println("You can list all tasks to check the total number of tasks you have. :))"); //invalid TaskNumber
            }
            isExit = Command.execute(tasks, ui, storage);
            ui.showLine(); //end of current Response to User

        }

    }

    public static void main(String[] args) {
        new Duke("data/dukeList.txt").run();
    }

}
