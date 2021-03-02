import Exceptions.IllegalCommandWordException;
import Exceptions.MissingDeadlineException;
import Exceptions.MissingEventTimeException;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        Ui.greetUser();

        try {
            TaskList.setupTasks();
        } catch (FileNotFoundException | StringIndexOutOfBoundsException |
                MissingDeadlineException | MissingEventTimeException e) {
            System.out.println("Error when attempting to read duke.txt. No data will be read.");
        }

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(line.toLowerCase().equals("bye") != true){
            System.out.println("\t" + Ui.SECTION_DIVIDER);
            try {
                Parser.handleInput(line);
            } catch (IllegalCommandWordException e){
                System.out.print("\tInvalid input! Please start with a valid command word!\n");
            } finally {
                System.out.println("\t" + Ui.SECTION_DIVIDER);
                line = in.nextLine();
            }
        }
        Ui.signOff();

        Storage.saveTasksToFile();
    }
}
