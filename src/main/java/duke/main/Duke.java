package duke.main;


public class Duke {
    /**
     * Prints welcome and goodbye message
     * Calls fileHandling
     * Calls run function from parser
     * Main function that combines all functions from other files
     */
    public static void main(String[] args) {
        UI.displayWelcomeMessage();
        UI.printLine();
        Storage.fileHandling();
        Parser.run();
        UI.displayByeMessage();
    }
}
