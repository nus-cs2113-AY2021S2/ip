package duke;

import java.util.Scanner;

import duke.command.Command;

public class Duke {
    public static final String SAVE_PATH = "duke.save";

    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    public Duke(String filepath) {
        ui = new Ui();
        Storage storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.printLoadSaveException(filepath, e);
            tasks = new TaskList(storage);
        }
        parser = new Parser(ui, tasks);
    }

    public void run() {
        ui.printWelcome();

        Scanner in = new Scanner(System.in);
        Boolean isExit = false;

        while (!isExit && in.hasNextLine()) {
            String fullCommand = in.nextLine();
            ui.printLine();
            try {
                Command cmd = parser.parse(fullCommand);
                cmd.execute();
                isExit = cmd.isExit();
            } catch (Exception e) {
                ui.printException(e);
            } finally {
                ui.printLine();
            }
        }
        in.close();
    }

    public static void main(String[] args) {
        new Duke(SAVE_PATH).run();
    }
}
