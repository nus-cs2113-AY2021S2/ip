import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(Storage.printFileContents("data/duke.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("task list doesn't exist yet");
            Storage.createDirAndFile();
            tasks = new TaskList();
        }
//        try {
//            tasks = new TaskList(storage.load());
//        } catch (DukeException e) {
//            ui.showLoadingError();
//            tasks = new TaskList();
//        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n"+
                        "Hello! I'm Duke\n"+
                        "What can I do for you?\n"+
                        "____________________________________________________________");


        new Duke("data/tasks.txt").run();


    }

    private static void run() {
        Ui.getCommand(tasks);
    }


}
