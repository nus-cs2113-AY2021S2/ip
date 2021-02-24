package duke;

import parser.CommandParser;
import storage.DukeReader;
import storage.DukeWriter;
import ui.Printer;

import java.util.Scanner;

import static task.TaskList.tasks;

public class Duke {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DukeReader.readFromFile();
        Printer.printStartUpMessage();
        CommandParser.parse(scanner);
        DukeWriter.writeToFile(tasks);
        Printer.printExitMessage();
    }
}
