package dukehandler;

import taskclasses.Deadline;
import taskclasses.Event;
import taskclasses.Task;
import taskclasses.ToDo;
import ui.ErrorMessagePrinter;
import ui.SuccessMessagePrinter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
  
    /**
     * Checks if file exists, or creates new file if it doesn't already exist.
     * @return File where the tasks list will be saved at end of program.
     */
    public static File loadFileOnStartup() {
        String filePath = new File("").getAbsolutePath();
        File f = new File(filePath + "/tasks.txt");
        try {
            if (f.createNewFile()) {
                SuccessMessagePrinter.printNewFileCreatedMessage(f);
                return f;
            }
            loadTasksFromFile(f.getAbsolutePath());
        } catch (IOException e) {
            ErrorMessagePrinter.printIOErrorMessage();
        }
        return f;
    }

    /**
     * Loads the contents from file (tasks.txt) into tasks ArrayList
     * @param filePath path of the tasks.txt file
     * @throws FileNotFoundException if tasks.txt file cannot be accessed.
     */
    public static void loadTasksFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] part = s.nextLine().split(" ~~ ");
            switch (part[0]) {
            case "T":
                TaskManager.tasks.add(new ToDo(part[2]));
                break;
            case "D":
                TaskManager.tasks.add(new Deadline(part[2], part[3], part[4]));
                break;
            case "E":
                TaskManager.tasks.add(new Event(part[2], part[3], part[4]));
                break;
            }
            if (part[1].equals("X")) {
                TaskManager.tasks.get(TaskManager.tasks.size() - 1).markAsDone();
            }
        }
    }

    /**
     * Checks if contents of tasks ArrayList can be saved onto tasks.txt file.
     * If file can be accessed, the contents will be saved.
     * @param f tasks.txt File where tasks ArrayList contents are saved
     */
    public static void endOfProgramRoutine(File f) {
        try {
            saveTasksToFile(f.getAbsolutePath());
        } catch (IOException e) {
            ErrorMessagePrinter.printIOErrorMessage();
        }
        SuccessMessagePrinter.printByeMessage();
    }

    /**
     * Writes tasks ArrayList data into tasks.txt file on computer
     * Delimiter is ' ~~ '
     * @param filePath path of tasks.txt file
     * @throws IOException if tasks.txt file cannot be accessed.
     */
    public static void saveTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : TaskManager.tasks) {
            fw.write(task.getTaskType() + " ~~ "
                    + task.getStatusIcon() + " ~~ "
                    + task.getTaskName());
            if (task.getTaskType().equals("D") || task.getTaskType().equals("E")) {
                fw.write(" ~~ " + task.getDate() + " ~~ " + task.getTime());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
