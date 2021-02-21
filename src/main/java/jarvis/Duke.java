package jarvis;

import jarvis.exception.InvalidCommandException;
import jarvis.storage.TextManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static final Jarvis jarvis = new Jarvis();

    // MAIN program
    public static void main(String[] args) throws InterruptedException {
        jarvis.startJarvis();

        try {
            TextManager.printFileContents();
            jarvis.printDivider();
        } catch (FileNotFoundException exception) {
            System.out.println("\tUnfortunately, I could not detect any files in the database!");
            System.out.println("\tBut don't worry sir.");
            System.out.println("\tI will create the files you might be needing later.");
            jarvis.printDivider();
        }

        while (true) {
            try {
                TextManager.writeToFile(jarvis.performTask());
            } catch (InvalidCommandException exception) {
                System.out.println("\tSorry, sir. I do not recognise this command.");
                jarvis.printDivider();
            } catch (IOException exception) {
                System.out.println("\tSomething went wrong: " + exception.getMessage());
                jarvis.printDivider();
            }
        }
    }
}