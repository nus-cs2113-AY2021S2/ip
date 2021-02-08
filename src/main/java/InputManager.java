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
                try {
                    taskManager.addDeadlineToList(command.getDescription(), command.getWhen());
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println("Error -> Empty task description");
                } catch (EmptyByOrAtWhenException | NullPointerException e) {
                    System.out.println("Error -> Deadline by when is not stated");
                }
                break;
            case EVENT:
                try {
                    taskManager.addEventToList(command.getDescription(), command.getWhen());
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println("Error -> Empty task description");
                } catch (EmptyByOrAtWhenException | NullPointerException e) {
                    System.out.println("Error -> Event at when is not stated");
                }
                break;
            case TODO:
                try {
                    taskManager.addTodoToList(command.getDescription());
                } catch (EmptyTaskDescriptionException e) {
                    System.out.println("Error -> Empty task description");
                }
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
