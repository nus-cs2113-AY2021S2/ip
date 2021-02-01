public class Duke {
    public static void main(String[] args) {
        PrintManager.showLogo();
        PrintManager.showGreetMessage();

        InputManager listManager = new InputManager();
        listManager.manageInput();
    }
}
