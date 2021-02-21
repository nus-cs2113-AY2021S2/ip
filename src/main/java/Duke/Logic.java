package Duke;

import Duke.Commands.Command;
import Duke.Exceptions.DukeException;

public class Logic {

    private TaskList taskList;
    private Storage storage;
    private UserInterface userInterface;

    public Logic(String filePath) {
        userInterface = new UserInterface();
        storage = new Storage(filePath);
        taskList = storage.loadTaskList();
    }

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