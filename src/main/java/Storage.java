import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Contains methods for loading and saving files
 */
public class Storage {

    static File saveFile;
    static ArrayList<Task> tasks = new ArrayList<>();
    static String filePath;

    /**
     * Instantiates a storage handler by taking in the filepath
     *
     * @param filePath the filepath where the file will be created
     */
    public Storage(String filePath) {
        saveFile = new File(filePath);
        Storage.filePath = filePath;
    }

    /**
     * Checks if the file exists and initializes one if there is not.
     */
    public void fileInit() {
        try {
            //makes file directory if it doesnt exist in the system.
            if (!(saveFile.exists())) {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("OOPS! I can't create the directory or file!");
        }
    }

    /**
     * Loads the saved list of tasks from save location
     *
     * @return Populated task arraylist
     * @throws DukeException if there is an error in loading
     */
    public ArrayList<Task> load() throws DukeException {
        fileInit();
        try {
            // initializing file scanner to scan the file
            Scanner fileScanner = new Scanner(saveFile);

            while (fileScanner.hasNext()) {
                String currentScan = fileScanner.nextLine();
                //splits the string into sections for storing in the ArrayList
                String[] taskSave = currentScan.trim().split(" \\| ");
                switch (taskSave[0].trim()) {
                    case "todo":
                        if (taskSave.length != 3) {
                            throw new DukeException("loadFile");
                        }
                        Task tempTask = new Task(taskSave[2]);
                        tempTask.setIsDone(taskSave[1].trim());
                        tasks.add(tempTask);
                        break;
                    case "deadline":
                        if (taskSave.length != 4) {
                            throw new DukeException("loadFile");
                        }
                        Deadline tempDeadline = new Deadline(taskSave[2], taskSave[3]);
                        tempDeadline.setIsDone(taskSave[1].trim());
                        tasks.add(tempDeadline);
                        break;
                    case "event":
                        if (taskSave.length != 4) {
                            throw new DukeException("loadFile");
                        }
                        Event tempEvent = new Event(taskSave[2], taskSave[3]);
                        tempEvent.setIsDone(taskSave[1].trim());
                        tasks.add(tempEvent);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS! I can't read the save file!");
        } catch (DukeException e) {
            e.getError("loadFile");
        }
        return tasks;
    }

    /**
     * Saves all tasks to text file
     *
     * @param saveInput current files to be stored
     */
    public void store(TaskList saveInput) {
        fileInit();
        try {
            //creates a new file writer to write to text file
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < saveInput.getSize(); i++) {
                fileWriter.write(saveInput.toSaveFile(i) + "\n");
            }
            fileWriter.close();
        } catch (java.io.IOException e) {
            System.out.println("☹ OOPS!!! The file can't be saved :-(");
        }
    }
}
