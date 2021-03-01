package Duke;

import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
@ This Duke class is where the main program runs.
@ The duke method initialise the UI and storage.
@ storage.loadflile loads the data saved in the file and writes it into an array list to be passed into the program
@An error exception is written so that if there is no file present, the program will create a new file and initialise an array list
@to be used
@ the run method runs the UI.welcome to print responses from the program to interact with the user
@ It reads in the user input and passes the command into the program to be processed.

*/


public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.loadFile();
        } catch (FileNotFoundException e) {
            try {
                storage.createFile();
                tasks = storage.loadFile();
            } catch (IOException e1) {
                System.out.println("File cannot be created");
            }
        }
    }

    public void run() {
        ui.showWelcome();
        ui.showLine();
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNext()) {
            String input = userInput.nextLine();
            String s = Parser.parse(input, tasks);

            if (input.equals("bye")) break;
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasksList.txt").run();
    }
}