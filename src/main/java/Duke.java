import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    protected static FileManager storage;

    public Duke() {
//         set up the things needed to start Duke

        FileManager.createFolder();
        storage = new FileManager();
        if (storage.retrieveTextFile()) {
            storage.loadData();
        }

    }


    public void run() {

        DukeCommandHandler.greeting();
        Scanner in = new Scanner(System.in);
        String input;

        while (in.hasNext()) {
            input = in.nextLine();
            if (input.equals("list")) {
                DukeCommandHandler.listTask();
            } else if (input.contains("done")) {
                DukeCommandHandler.markTaskDone(input);
            } else if (input.contains("delete")) {
                DukeCommandHandler.deleteTask(input);
            } else if (input.contains("add")) {
                DukeCommandHandler.addTask(input);
            } else if (input.contains("todo")) {
                DukeCommandHandler.addToDo(input);
            } else if (input.contains("deadline")) {
                DukeCommandHandler.addDeadline(input);
            } else if (input.contains("event")) {
                DukeCommandHandler.addEvent(input);
            } else if (input.equals("bye")) {
                DukeCommandHandler.exitDuke();
                break;
            } else {
                DukeException.invalidCommand();
            }
        }
        in.close();

    }

    public static void main(String[] args) {

        new Duke().run();
    }

}
