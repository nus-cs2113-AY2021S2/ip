import commands.Command;
import exceptions.MissingInfoException;
import exceptions.UnknownCommandException;
import exceptions.UnknownFormatException;
import io.DukePrint;
import io.FileManager;
import models.Task;
import models.TaskList;
import parser.CommandParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final DukePrint dukePrint;
    private final CommandParser parser;
    private final FileManager fileManager;
    private TaskList tasks;

    public Duke() {
        dukePrint = new DukePrint();
        fileManager = new FileManager();
        dukePrint.printLogo();

        try {
            tasks = new TaskList(fileManager.loadFile());
            System.out.println("Save data loaded!");
        } catch (Exception e) {
            System.out.println("No save files found.");
            ArrayList<Task> taskList = new ArrayList<>();
            tasks = new TaskList(taskList);
        }

        parser = new CommandParser(tasks, dukePrint);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String phrase;
        Command command;

        dukePrint.printWelcome();

        try {
            do {
                phrase = sc.nextLine();
                dukePrint.printDivider();
                command = parser.parse(phrase);
                command.execute();
                dukePrint.printEndDivider();
                fileManager.saveFile(tasks.getTaskList());
            } while (!phrase.equals("bye"));
        } catch (MissingInfoException | UnknownCommandException | UnknownFormatException | ParseException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
