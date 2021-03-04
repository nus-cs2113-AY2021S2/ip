package duke.storage;

import duke.data.TaskList;
import duke.data.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String home = System.getProperty("user.dir");
    private static final String directoryPath = home + File.separator + "data";
    private static final String filePath = home + File.separator + "data" + File.separator + "task_list.txt";

    public void createFileIfNotExist() {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }

            File myFile = new File(filePath);
            if (myFile.createNewFile()) {
                System.out.println("A new task list file is created!");
            } else {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> readFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        s.close();
        ArrayList<Task> decodedList = TaskListDecoder.decoder(list);
        return decodedList;
    }

    public static void writeToFile(TaskList tasks) throws IOException {
        String textToAdd = TaskListEncoder.encoder(tasks);
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        createFileIfNotExist();
        return readFile();
    }
}
