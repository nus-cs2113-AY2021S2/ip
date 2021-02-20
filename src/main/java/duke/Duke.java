package duke;

import duke.exception.DataErrorException;
import duke.exception.FullListException;
import duke.inputhandlers.CommandRunner;
import duke.inputhandlers.Parser;
import duke.storage.FileManager;
import duke.storage.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.TextUi;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    //public static ArrayList<Task> taskList = new ArrayList<>();
    public static TaskList tasks = new TaskList();
    public static TextUi ui = new TextUi();
    

    public static void main(String[] args) {
        ui.printHello();
        FileManager.checkSavedData();
        receiveUserInput();
        ui.printBye();
    }
    

    private static void receiveUserInput() {
        Scanner in = new Scanner(System.in);
        CommandRunner runner = new CommandRunner();
        boolean isExit = false;

        //Loop to receive response.
        while (!isExit) {
            String input = in.nextLine();
            int command = Parser.parseCommand(input);
            
            // If list is full, will only allow LIST and BYE command to pass
            try {
                runner.checkListCapacity(command);
            } catch (FullListException e) {
                ui.printListFullWarning();
                continue;
            }

            isExit = runner.selectCommandToRun(command, input);
        }
    }


}
