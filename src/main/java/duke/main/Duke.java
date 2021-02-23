package duke.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.exceptions.*;

import static duke.main.Utils.createFile;
import static duke.main.Utils.loadFile;

public class Duke {
    public static void main(String[] args) {
        Utils.welcomeMessage();
        Utils.printLine();
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            createFile();
        }
        Scanner in = new Scanner(System.in);
        while (true) {
            String line;
            line = in.nextLine();
            Utils.printLine();
            try {
                if (Utils.commandHandler(line) == -1) {
                    break;
                }
            } catch (InvalidCommandExceptions e) {
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidParameterLengthExceptions | DeadlineParameterExceptions
                    | EventParameterExceptions e){
                System.out.println(" ☹ OOPS!!! Invalid parameter input for command: " + line.split(" ")[0]);
            } catch (InvalidIndexExceptions e){
                System.out.println(" ☹ OOPS!!! Index is out of range");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Utils.printLine();
        }
        Utils.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        Utils.printLine();
    }
}