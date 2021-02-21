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
    private final SuccessMessagePrinter successMessagePrinter;
    protected TaskList taskList;

    public FileManager(TaskList taskList, SuccessMessagePrinter smp) {
        this.taskList = taskList;
        this.successMessagePrinter = smp;
    }

    /**
     * Checks if file exists, or creates new file if it doesn't already exist.
     *
     * @return File where the tasks list will be saved at end of program.
     */
    public File loadFileOnStartup() {
        String filePath = new File("").getAbsolutePath();
        File f = new File(filePath + "/tasks.txt");
        try {
            if (f.createNewFile()) {
                new SuccessMessagePrinter(taskList).printNewFileCreatedMessage(f);
            }
            loadTasksFromFile(f.getAbsolutePath());
        } catch (IOException e) {
            ErrorMessagePrinter.printIOErrorMessage();
        }
        return f;
    }

    /**
     * Loads the contents from file (tasks.txt) into tasks ArrayList
     *
     * @param filePath path of the tasks.txt file
     * @throws FileNotFoundException if tasks.txt file cannot be accessed.
     */
    public void loadTasksFromFile(String filePath)
            throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] part = s.nextLine().split(" ~~ ");
            switch (part[0]) {
            case "T":
                taskList.tasks.add(new ToDo(part[2]));
                break;
            case "D":
                taskList.tasks.add(new Deadline(part[2], part[3]));
                break;
            case "E":
                taskList.tasks.add(new Event(part[2], part[3]));
                break;
            }
            if (part[1].equals("X")) {
                taskList.tasks.get(taskList.tasks.size() - 1).markAsDone();
            }
        }
    }

    /**
     * Checks if contents of tasks ArrayList can be saved onto tasks.txt file.
     * If file can be accessed, the contents will be saved.
     * Called when 'bye' command is given to end the program after saving tasks contents
     *
     * @param f tasks.txt File where tasks ArrayList contents are saved
     */
    public void endOfProgramRoutine(File f) {
        try {
            saveTasksToFile(f.getAbsolutePath());
        } catch (IOException e) {
            ErrorMessagePrinter.printIOErrorMessage();
        }
        successMessagePrinter.printByeMessage();
    }

    /**
     * Writes tasks ArrayList data into tasks.txt file on computer
     * Delimiter is ' ~~ '
     *
     * @param filePath path of tasks.txt file
     * @throws IOException if tasks.txt file cannot be accessed.
     */
    public void saveTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList.tasks) {
            fw.write(task.getTaskType() + " ~~ "
                    + task.getStatusIcon() + " ~~ "
                    + task.getTaskName());
            if (task.getTaskType().equals("D")
                    || task.getTaskType().equals("E")) {
                fw.write(" ~~ " + task.getTime());
            }
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
