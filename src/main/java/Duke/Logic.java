package Duke;

import Duke.Commands.Command;
import Duke.Exceptions.DukeException;

/**
 * Logic class that deals with the entire high-level logic of the program
 */
public class Logic {

    /**
     * Initializing a TaskList, Storage and UserInterface program for use by the rest of the program
     */
    private TaskList taskList;
    private Storage storage;
    private UserInterface userInterface;

    /**
     * Constructor for the class which initializes a new TaskList object, UserInterface object and
     * Storage object for the program's use
     * @param filePath
     */
    public Logic(String filePath) {
        userInterface = new UserInterface();
        storage = new Storage(filePath);
        taskList = storage.loadTaskList();
    }

    /**
     * Main loop of the program.
     */
    public void run() {
        userInterface.printOutput(Output.printGreet());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = userInterface.getInput();
                Command currentCommand = Parser.parse(fullCommand);
                String commandOutput = currentCommand.execute(taskList, storage);
                userInterface.printOutput(commandOutput);
                isExit = currentCommand.isExit();
            } catch (DukeException e) {
                userInterface.printOutput(e.getMessage());
            }
        }
    }

}