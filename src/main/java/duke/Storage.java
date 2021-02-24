package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {

    private static String path;

    public Storage(String path) {
        this.path = path;
    }

    public static ArrayList<Task> loadTasks() throws FileNotFoundException, NoSuchElementException {
        ArrayList<Task> tasks = new ArrayList<>();
        String loadedCommand;
        String[] words;
        File f = new File(path);
        Scanner input = new Scanner(f);

        do {
            loadedCommand = input.nextLine();
            if (loadedCommand == null) {
                return null;
            }
            words = loadedCommand.split(",");

            if (loadedCommand.charAt(0) == 'T') {
                tasks.add(new Todo(words[1]));
                Keyword.setKeywords("T");
            }
            if (loadedCommand.charAt(0) == 'E') {
                tasks.add(new Event(words[1], words[2]));
                Keyword.setKeywords("E");
            }
            if (loadedCommand.charAt(0) == 'D') {
                tasks.add(new Deadline(words[1], words[2]));
                Keyword.setKeywords("D");
            }
        } while (input.hasNextLine());
        return tasks;
    }

    public static void writeData(FileWriter fw) throws IOException {
        for (int i = 0; i < TaskList.getListSize(); ++i) {
            String keyword = Keyword.getKeywords(i);
            fw.write(keyword + "," + TaskList.getTaskAtIndex(i).getDescription());
            if (keyword == "E") {
                fw.write(" | " + Event.getAt());
            } else if (keyword == "D") {
                fw.write(" | " + Deadline.getBy());
            }
            fw.write(System.lineSeparator());
        }
    }

    public static void saveData() {
        try {
            File path = new File("dukeSave.txt");
            FileWriter fw = new FileWriter(path);
            writeData(fw);
            fw.close();
        } catch (IOException e) {
            Ui.printSaveError();
        }
    }
}
