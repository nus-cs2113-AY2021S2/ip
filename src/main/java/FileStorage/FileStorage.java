package FileStorage;
import list.Deadline;
import list.Event;
import list.TaskList;
import list.Todo;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;


public class FileStorage {

    public static final String FILE_PATH = "duke.txt";

    public void saveToFile(ArrayList<TaskList> tasks) {
        boolean hasSaved = true;
        do {
            hasSaved = writeToFile(hasSaved, tasks);
        } while (!hasSaved);
    }

    private static boolean writeToFile(boolean hasSaved, ArrayList<TaskList> tasks) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            hasSaved = true;
            for (TaskList t : tasks) {
                hasSaved = writeTaskToFile(fw, t);
            }
            fw.close();
        } catch (java.io.IOException e) {
            File file = new File(FILE_PATH);
            hasSaved = false;
        }
        return hasSaved;
    }

    private static boolean writeTaskToFile(FileWriter fw, TaskList t) {
        boolean hasSaved = true;
        if (t instanceof Event) {
            Event temp = (Event) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }

        } else if (t instanceof Deadline) {
            Deadline temp = (Deadline) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }

        } else if (t instanceof Todo) {
            Todo temp = (Todo) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }
        }
        return hasSaved;
    }
}
