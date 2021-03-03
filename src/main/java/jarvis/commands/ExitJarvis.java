package jarvis.commands;

import jarvis.Duke;

public class ExitJarvis extends Command {

    /** Terminates JARVIS program */
    public static void execute() {
        System.out.println("\tGoodbye, sir.");
        Duke.jarvis.printDivider();
        System.exit(0);
    }
}
