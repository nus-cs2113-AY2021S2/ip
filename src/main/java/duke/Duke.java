package duke;

import parser.CommandParser;
import storage.DukeReader;
import storage.DukeWriter;
import task.TaskList;
import ui.Printer;

import java.util.Scanner;



/**
 * Represents the main class, which is the entry point of the program.
 */
public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukeReader.readFromFile();
        Printer.printStartUpMessage();
        TaskList.loadTasks();
        CommandParser.parse(scanner);
        DukeWriter.writeToFile(TaskList.getTasks());
        Printer.printExitMessage();
    }
}
