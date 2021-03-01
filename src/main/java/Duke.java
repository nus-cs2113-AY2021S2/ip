import ManagerClasses.InputManager;
import ManagerClasses.PrintManager;

public class Duke {
    public static void main(String[] args) {
        PrintManager.showLogo();
        PrintManager.showGreetMessage();

        // Initialize InputManager
        InputManager inputManager = new InputManager();
        inputManager.manageInput();
    }
}
