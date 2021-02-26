import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import Task.*;

public class Duke {
    private final Storage storage;

    public void run() throws IOException {
        Ui.welcome();
        ArrayList<Task> Tasks = new ArrayList<>();
        Storage.readFile(Tasks);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            while(!line.equals("list")) {
                if (line.startsWith("done")){
                    String[] parts = line.split(" ");
                    int i=Parser.parserToInteger(parts[1].trim());
                    Tasks.get(i-1).markAsDone();
                    System.out.println(Tasks.get(i-1).toString() + "\n");
                    TaskList.showDone();
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
                    try {
                        line.substring(line.indexOf(" "));
                        TaskList.RemoveTasks(Tasks, line);
                    } catch (java.lang.StringIndexOutOfBoundsException e) {
                        Ui.showEmptyDescriptionException(line);
                    }
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
        Ui.bye();
        Storage.writeFile(Tasks);
    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
    }

    public static void main(String[] args) throws IOException {
        new Duke("C:\\Users\\Xinjia\\Desktop\\cs2113t\\ip\\src\\main\\java\\duke.txt").run();
    }


}
