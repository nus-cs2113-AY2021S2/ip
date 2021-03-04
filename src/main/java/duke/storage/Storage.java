package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String filePath;

    public Storage (String input) {
        filePath = input;
//        load();
    }

    public static void loadData() {
        try {
            Path dataFilePath = Paths.get("data/");
            Files.createDirectories(dataFilePath);
            File data = new File("data/duke.txt");
            Scanner readFile = new Scanner(data);
            while (readFile.hasNextLine()) {
                String input = readFile.nextLine();
                //split string by whitespace + "|" + whitespace
                String[] taskSplit = input.split("\\s\\|\\s");
                Task t = new Task("");
                if (taskSplit[0].equals("[D]")) {
                    t = new Deadline(taskSplit[2], taskSplit[3]);
                } else if (taskSplit[0].equals("[E]")) {
                    t = new Event(taskSplit[2], taskSplit[3]);
                } else if (taskSplit[0].equals("[T]")) {
                    t = new Todo(taskSplit[2]);
                }
                TaskList.tasks.add(t);
                if (taskSplit[1].equals("1")) {
                    t.setDone();
                }
            }
            readFile.close();
        } catch (IOException e) {
            System.out.println("Error when creating data directory. Please try again.");
        }


    }
//    public static void loadData() {
//        try {
//            createFile();
//        }
//        catch(IllegalStateException e) {
//            System.out.println("Error in loading data! Please try again!");
//        }
//    }

    public static boolean saveData() {
        try {
            System.out.println("Saving your data...");
            File file = new File("duke.txt");
            FileWriter fileWriter = new FileWriter("data/duke.txt");

            for (int i = 0; i <  TaskList.tasks.size(); i++) {
                Task currentTask =  TaskList.tasks.get(i);
                fileWriter.write(currentTask.getTaskType() + " | " + ((currentTask.getIsDone()) ? "1" : "0") + " | "
                        + currentTask.getDescription() + ((currentTask.hasDateTime()) ? " | "
                        + currentTask.getDateTime() : "") + "\n");
            }
            fileWriter.close();
            System.out.println("Data saved");
            return true;
        } catch (IOException e) {
            System.out.println("Error occurred when saving your data. Please try again.");
            return false;
        }
    }
}
