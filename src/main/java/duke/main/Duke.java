package duke.main;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        Storage.loadTasks();
    }

    private void run() {
        Ui.greet();
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while (!input.equals("bye")) {
            Command command = Parser.parse(input);
            try {
                command.execute(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.commandDone();
            }
            Storage.saveTasks();
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}