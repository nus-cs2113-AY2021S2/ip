package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    /**
     * Loads a previously saved file or creates a new save
     * file if it doesn't exist.
     *
     * If the save file is unable to be located even though
     * it exists, the exception thrown will be captured.
     *
     * If the save file creation failed, th exception thrown
     * will be captured.
     */
    public static void loadFile(){
        try {
            File f = new File("duke.txt");
            if (f.createNewFile()){
                System.out.println("Save file is created: " + f.getName());
            } else {
                try {
                    Storage.loadList("duke.txt");
                } catch (FileNotFoundException e){
                    System.out.println("Save file not found.");
                    e.printStackTrace();
                }
                System.out.println("Save file loaded successfully.");
            }
        } catch (IOException e) {
            System.out.println("Save file creation failed.");
            e.printStackTrace();
        }
    }

    // Recreate the ArrayList from save file
    private static void loadList(String filePath) throws FileNotFoundException{
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        if(s.hasNext()) {
            s.nextLine();       // Skip the first line
        }
        while (s.hasNext()){
            String listEntry = s.nextLine();
            Scanner listEntryS = new Scanner(listEntry);
            String taskType = listEntryS.next();
            String[] parser;
            String taskName;
            String taskStatus;
            String date;
            switch (taskType){
            case "T":
                parser = listEntry.split("\\|");
                taskName = parser[2].trim();
                Task t = new Todo(taskName);
                t.addTaskToArrayList();
                taskStatus = parser[1].trim();
                if (taskStatus.equals("1")){
                    t.setIsDone();
                }
                break;
            case "D":
                parser = listEntry.split("\\|");
                taskName = parser[2].trim();
                date = parser[3].trim();
                Task d = new Deadline(taskName, date);
                d.addTaskToArrayList();
                taskStatus = parser[1].trim();
                if (taskStatus.equals("1")){
                    d.setIsDone();
                }
                break;
            case "E":
                parser = listEntry.split("\\|");
                taskName = parser[2].trim();
                date = parser[3].trim();
                Task e = new Event(taskName, date);
                e.addTaskToArrayList();
                taskStatus = parser[1].trim();
                if (taskStatus.equals("1")){
                    e.setIsDone();
                }
                break;
            }
        }
    }

    /**
     * Calls the method <code>saveFile</code> and
     * attempts to save the file.
     *
     * If the file saving process fails, the exception
     * thrown will be captured.
     */
    public static void attemptSaveFile() {
        try {
            Storage.saveFile("duke.txt");
        } catch (IOException e){
            System.out.println("Fail to save file.");
            e.printStackTrace();
        }
    }

    private static void saveFile(String filePath) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write("List of tasks: " + System.lineSeparator());
        for (int i = 0; i < Task.getTaskCount(); i++) {
            fw.write(Task.getTask(i));
        }
        fw.close();
    }
}
