package jarvis;

import jarvis.exception.InvalidCommandException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws InterruptedException {
        Jarvis jarvis = new Jarvis();
        jarvis.startJarvis();

        try {
            printFileContents();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
        }

        while (true) {
            try {
                jarvis.performTask();
            } catch (InvalidCommandException exception) {
                System.out.println("\tSorry, sir. I do not recognise this command.");
                jarvis.printDivider();
            }
        }
    }

    private static void printFileContents() throws FileNotFoundException {
        File file = new File("data/jarvis.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }
}