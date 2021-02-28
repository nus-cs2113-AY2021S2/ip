package duke;
import java.util.Scanner;
import commands.Command;
import commands.CommandResult;
import commands.Parser;
import commands.byeCommand;


public class duke {

    private static todoList inputList = new todoList();

    /**Sets the command to act on the current list of tasks and executes the input command.
     * @param command the command to be executed
     */
    private static CommandResult executeCommand(Command command) {
        command.setData(inputList);
        CommandResult result;
        try {
            result = command.execute();
        }catch(IncorrectFormatException e){
            return new CommandResult(e.getMessage());
        }
        return result;
    }
    /**Loops until bye command is detected. Write to storage every time command is entered
     * @param Data the storage instance to update
     */
    private static void runCommandLoopUntilExitCommand(Storage Data) {
        Command command = null;
        Ui ui = new Ui();
        do {
            Scanner in = new Scanner(System.in);
            String userCommandText = in.nextLine();
            try {
                command = new Parser().parseCommand(userCommandText);
            }catch(IncorrectFormatException e){
                System.out.println(e.getMessage()); ;
                continue;
            }
            CommandResult result = executeCommand(command);
            ui.showResult(result);
            Data.updateFile();

        } while (!byeCommand.isBye(command));
    }


    public static void main(String[] args) throws IncorrectFormatException {
        Ui ui = new Ui();
        ui.startupUi();
        Storage duke = new Storage(inputList);
        ui.showResult(Storage.initialiseStorage());
        runCommandLoopUntilExitCommand(duke);
    }
}


