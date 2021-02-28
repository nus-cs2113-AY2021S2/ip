package Duke;

import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.Tasks.TaskList;
import Duke.Ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


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