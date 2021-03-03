package jarvis;

import jarvis.exception.*;
import jarvis.storage.TextManager;
import jarvis.ui.JarvisUi;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point of the JARVIS program.
 * Initialises the program and starts the interaction with the user.
 */
public class Duke {

    public static final JarvisUi jarvis = new JarvisUi();

    public static void main(String[] args) throws InterruptedException {

        jarvis.startJarvis();

        /* Reads jarvis.txt file if it exists in the folder */
        try {
            TextManager.printFileContents();
            jarvis.printDivider();
        } catch (FileNotFoundException exception) {
            ExceptionHandler.handleFileNotFoundException();
        }

        /*
         * JARVIS program gets commands from user and executes it.
         * jarvis.txt file is written whenever the list is updated.
         */
        while (true) {
            try {
                TextManager.writeToFile(jarvis.performTask());
            } catch (InvalidCommandException exception) {
                ExceptionHandler.handleInvalidCommandException();
            } catch (IOException exception) {
                ExceptionHandler.handleIOException(exception);
            } catch (EmptyListException exception) {
                ExceptionHandler.handleEmptyListException();
            } catch (InvalidTaskException exception) {
                ExceptionHandler.handleInvalidTaskException();
            } catch (EmptyDescriptionException exception) {
                ExceptionHandler.handleEmptyDescriptionException();
            } catch (EmptyKeywordException exception) {
                ExceptionHandler.handleEmptyKeywordException();
            } catch (NoMatchException exception) {
                ExceptionHandler.handleNoMatchException();
            } catch (EmptyTaskIdException exception) {
                ExceptionHandler.handleEmptyTaskIdException();
            }
        }
    }
}