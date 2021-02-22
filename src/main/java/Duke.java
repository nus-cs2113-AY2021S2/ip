import command.Command;
import parser.Parser;
import task.TaskManager;
import ui.Ui;

public class Duke {
    private Ui ui;
    private TaskManager taskList;

    public Duke(){
        ui = new Ui();
        taskList = new TaskManager();
    }

    public void run() {
        boolean isBye = false;
        ui.printMenu();
        do{
            String userInput = ui.getUserInput();
            String newUserInput = userInput.toUpperCase();
            Command command = Parser.parseCommand(newUserInput.toUpperCase());
            command.executeCommand(newUserInput, taskList);
            isBye = ui.sayGoodBye(newUserInput);
        }while(!isBye);
    }



    public static class Main {
        public static void main(String[] args) {
            Duke dk = new Duke();
            dk.run();
        }
    }
}
