package jarvis;

import jarvis.exception.InvalidCommandException;
import jarvis.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void printFileContents() throws FileNotFoundException {
        File file = new File("text-ui-test/jarvis.txt");
        Scanner scanner = new Scanner(file);
        if (scanner.hasNext()) {
            System.out.println("\tHere's the tasks in your list, sir: ");
        } else {
            System.out.println("\tYou do not have any pending task, sir.");
        }
        while (scanner.hasNext()) {
            System.out.println("\t\t" + scanner.nextLine());
        }
    }

    private static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter("text-ui-test/jarvis.txt");
        for (Task task : tasks) {
            fileWriter.write(task.toString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    public static void main(String[] args) throws InterruptedException {
        Jarvis jarvis = new Jarvis();
        jarvis.startJarvis();

        try {
            printFileContents();
            jarvis.printDivider();
        } catch (FileNotFoundException exception) {
            System.out.println("\tUnfortunately, I could not detect any files in the database!");
            System.out.println("\tBut don't worry sir.");
            System.out.println("\tI will create the files you might be needing later.");
            jarvis.printDivider();
        }

        while (true) {
            try {
                writeToFile(jarvis.performTask());
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