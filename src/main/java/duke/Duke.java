package duke;


import java.io.FileNotFoundException;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        String line;
        line = ui.readInput();
        while (!line.equals("bye")) {
            System.out.println("---------------------------------------------------------");
            if (line.equals("list")) {
                ui.printList(tasks.getTasks());
            } else if (line.startsWith("todo")) {
                tasks.addTodo(line);
                storage.saveFile(tasks.getTasks());
            } else if (line.startsWith("deadline")) {
                tasks.addDeadline(line);
                storage.saveFile(tasks.getTasks());
            } else if (line.startsWith("event")) {
                tasks.addEvent(line);
                storage.saveFile(tasks.getTasks());
            } else if (line.startsWith("done")) {
                tasks.markDone(line);
                storage.saveFile(tasks.getTasks());
            } else if (line.startsWith("delete")) {
                tasks.deleteTask(line);
                storage.saveFile(tasks.getTasks());
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("---------------------------------------------------------");
            line = ui.readInput();
        }
        ui.bidGoodbye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

}
