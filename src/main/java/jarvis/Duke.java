package jarvis;

import jarvis.exception.InvalidCommandException;

public class Duke {
    public static void main(String[] args) throws InterruptedException {
        Jarvis jarvis = new Jarvis();
        jarvis.startJarvis();
        while (true) {
            try {
                jarvis.performTask();
            } catch (InvalidCommandException exception) {
                System.out.println("\tSorry, sir. I do not recognise this command.");
                jarvis.printDivider();
            }
        }
    }
}