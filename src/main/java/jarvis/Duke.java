package jarvis;

import jarvis.exception.EmptyListException;
import jarvis.exception.HandleException;
import jarvis.exception.InvalidCommandException;
import jarvis.exception.InvalidTaskException;
import jarvis.storage.TextManager;
import jarvis.ui.JarvisUi;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    public static final JarvisUi jarvis = new JarvisUi();

    /**
     * Entry point of the JARVIS application.
     * Initialises JARVIS and starts the interaction with the user.
     */
    public static void main(String[] args) throws InterruptedException {

        jarvis.startJarvis();

        try {
            TextManager.printFileContents();
            jarvis.printDivider();
        } catch (FileNotFoundException exception) {
            HandleException.handleFileNotFoundException();
        }

        while (true) {
            try {
                TextManager.writeToFile(jarvis.performTask());
            } catch (InvalidCommandException exception) {
                HandleException.handleInvalidCommandException();
            } catch (IOException exception) {
                System.out.println("\tSomething went wrong: " + exception.getMessage());
                jarvis.printDivider();
            } catch (EmptyListException exception) {
                HandleException.handleEmptyListException();
            } catch (InvalidTaskException exception) {
                HandleException.handleInvalidTaskException();
            }
        }
    }
}