import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {

    static File saveFile;
    static ArrayList<Task> tasks = new ArrayList<>();
    static String filePath;

    public Storage(String filePath) {
        saveFile = new File(filePath);
        Storage.filePath = filePath;
    }

    public void fileInit() {
        try {
            if (!(saveFile.exists())) {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("OOPS! I can't create the directory or file!");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        fileInit();
        try {
            Scanner fileScanner = new Scanner(saveFile);

            while (fileScanner.hasNext()) {
                String currentScan = fileScanner.nextLine();
                String[] taskSave = currentScan.trim().split(" \\| ");
                switch (taskSave[0].trim()) {
                    case "todo":
                        Task tempTask = new Task(taskSave[2]);
                        tempTask.setIsDone(taskSave[1].trim());
                        tasks.add(tempTask);
                        break;
                    case "deadline":
                        Deadline tempDeadline = new Deadline(taskSave[2], taskSave[3]);
                        tempDeadline.setIsDone(taskSave[1].trim());
                        tasks.add(tempDeadline);
                        break;
                    case "event":
                        Event tempEvent = new Event(taskSave[2], taskSave[3]);
                        tempEvent.setIsDone(taskSave[1].trim());
                        tasks.add(tempEvent);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS! I can't read the save file!");
        }
        return tasks;
    }

    public void store(TaskList saveInput) {
        fileInit();
        try {
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
