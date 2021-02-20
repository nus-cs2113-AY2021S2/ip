package duke;

import java.io.*;

public class Duke {


    private static final String FILEPATH = "tasklogs/tasks.txt";

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Duke() {
        storage = new Storage(FILEPATH);
        ui = new Ui();
        taskList = new TaskList(ui);
        parser = new Parser(taskList, ui);
        try {
            storage.loadFile(taskList.getTasks());
        } catch (IOException e) {
            System.out.println("IO error.");
        }
    }
    public void run() {
        try {
            ui.printGreeting();
            parser.parseCommands();
            ui.sayGoodbye();
            storage.saveFile(taskList.getTasks());
        } catch (IOException e) {
            System.out.println("IO error.");
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
