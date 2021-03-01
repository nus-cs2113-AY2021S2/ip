import ManagerClasses.InputManager;
import ManagerClasses.PrintManager;

/**
 * Duke's main class where greeting messages are printed and InputManager is initialized
 */
public class Duke {
    public static void main(String[] args) {
        PrintManager.showLogo();
        PrintManager.showGreetMessage();

        // Initialize InputManager
        InputManager inputManager = new InputManager();
        inputManager.manageInput();
    }
}
