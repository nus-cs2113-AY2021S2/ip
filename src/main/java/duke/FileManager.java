package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String filePath = "./src/main/java/duke/data/task_list.txt";
    public static void createFileIfNotExist() {
        try {
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

    public static ArrayList<String> readFile() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        if (!f.exists()) {
            createFileIfNotExist();
        }
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        s.close();
        return list;
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
