import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static UserInterface ui = new UserInterface();
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {

        ui.showWelcomeMessage();

        while(true){
            String input = ui.getUserInput();
            String[] parsedInput = ui.parseInput(input);
            String feedback = executeCommand(parsedInput);
            ui.printFeedback(feedback);
        }
    }

    private static String executeCommand(String[] inputs){
        String command = inputs[0].toLowerCase();
        String feedback = null;
        switch(command){
        case "bye":
            ui.showExitMessage();
            System.exit(0);
            break;
        case "list":
            feedback = taskManager.listTask();
            break;
        case "done":
            int taskNumber = Integer.parseInt(inputs[1])-1;
            feedback = taskManager.doneTask(taskNumber);
            break;
        default:
            feedback = taskManager.addTask(command, inputs[1]);
        }

        return feedback;
    }
}
