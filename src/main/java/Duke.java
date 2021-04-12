import Exceptions.IllegalCommandWordException;
import Exceptions.MissingDeadlineException;
import Exceptions.MissingEventTimeException;

import java.util.Scanner;
import java.io.FileNotFoundException;

public class Duke {

    private static Storage storage = new Storage();
    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();

    public static void main(String[] args) {
        ui.greetUser();

        try {
            tasks.setupTasks();
        } catch (FileNotFoundException | StringIndexOutOfBoundsException |
                MissingDeadlineException | MissingEventTimeException e) {
            System.out.println("Error when attempting to read duke.txt. No data will be read.");
        }

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(line.toLowerCase().equals("bye") != true){
            System.out.println("\t" + ui.SECTION_DIVIDER);
            try {
                Parser.handleInput(line);
            } catch (IllegalCommandWordException e){
                System.out.print("\tInvalid input! Please start with a valid command word!\n");
            } finally {
                System.out.println("\t" + ui.SECTION_DIVIDER);
                line = in.nextLine();
            }
        }
        ui.signOff();

        Storage.saveTasksToFile();
    }
}
