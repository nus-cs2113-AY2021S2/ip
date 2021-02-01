import java.util.Scanner;

public class InputManager {
    private final TaskManager taskManager;

    public InputManager(){
        taskManager = new TaskManager();
    }

    public void manageInput() {
        while(true) {
            String input = getUserInput();
            Command command = new Command(input);
            switch (command.getCommandType()) {
            case BYE:
                PrintManager.printByeMessage();
                return;
            case LIST:
                taskManager.printTasks();
                break;
            case DONE:
                taskManager.markDone(command.getTaskNum());
                break;
            case DEADLINE:
                taskManager.addDeadlineToList(command.getDescription(), command.getWhen());
                break;
            case EVENT:
                taskManager.addEventToList(command.getDescription(), command.getWhen());
                break;
            case TODO:
                taskManager.addTodoToList(command.getDescription());
                break;
            default:
                PrintManager.printHelpMessage();
            }
            PrintManager.printBreakLine();
        }
    }

    private String getUserInput() {
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        PrintManager.printBreakLine();
        return input;
    }
}
