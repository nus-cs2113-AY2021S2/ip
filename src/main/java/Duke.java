import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import Task.*;

/***
 * Starts the application.
 * initializes the interaction with the user.
 */

public class Duke {
    private final Storage storage;
    private final Ui ui;

    /**
     * Runs the application.
     */
    public void run() throws IOException {
        ui.welcome();
        ArrayList<Task> Tasks = new ArrayList<>();
        storage.readFile(Tasks);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            while(!line.equals("list")) {
                if (line.startsWith("done")){
                    TaskList.showDone(Tasks, line);
                }
                else if (line.startsWith("todo")) {
                    try {
                        line.substring(line.indexOf(" "));
                        Todo t = new Todo(line.replace("todo ", ""));
                        Tasks.add(t);
                        TaskList.showAddTasks(t.toString(),Tasks.size());
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        Ui.showEmptyDescriptionException(line);
                    }
                }
                else if (line.startsWith("event")) {
                    try {
                        line.substring(line.indexOf(" "));
                        line = line.replace("event", "");
                        String[] parts = line.split("/at");
                        Event t = new Event(parts[0].trim(), parts[1].trim());
                        Tasks.add(t);
                        TaskList.showAddTasks(t.toString(),Tasks.size());
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        Ui.showEmptyDescriptionException(line);
                    }
                }
                else if (line.startsWith("deadline")) {
                    try {
                        line.substring(line.indexOf(" "));
                        line = line.replace("deadline", "");
                        String[] parts = line.split("/by");
                        Deadline t = new Deadline(parts[0].trim(), parts[1].trim());
                        Tasks.add(t);
                        TaskList.showAddTasks(t.toString(),Tasks.size());
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        Ui.showEmptyDescriptionException(line);
                        }
                }
                else if (line.startsWith("delete")) {
                    TaskList.showRemoveTasks(Tasks, line);
                }
                else if (line.startsWith("find")){
                    try {
                        line.substring(line.indexOf(" "));
                        line = line.replace("find ", "");
                        TaskList.findTasks(Tasks, line);
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        Ui.showEmptyDescriptionException(line);
                    }

                }
                else if (!line.equals("")){
                    Ui.showException();
                }
                break;

            }
            while(line.equals("list")){
                TaskList.showList(Tasks);
                break;

            }
            line = in.nextLine();
        }
        ui.bye();
        storage.writeFile(Tasks);
    }

    /***
     * loads the required objects.
     * @param filePath the file path that was saved previously.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
    }

    public static void main(String[] args) throws IOException {
        new Duke("src\\main\\java\\duke.txt").run();
    }


}
