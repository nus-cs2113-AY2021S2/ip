package duke.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DataManager {
    private final String filePath;

    public DataManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(String data) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }

    public Scanner loadData() {
        Scanner scanner = null;
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }else{
                System.out.println("loading existing data...");
            }
            scanner = new Scanner(file);
        } catch (IOException e) {
            System.out.println("Problems creating file: " + filePath);
            System.out.println("History will not be saved in " + filePath);
            System.exit(1);
        }

        return scanner;
    }
}
