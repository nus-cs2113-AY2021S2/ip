package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents any file-related actions such as loading tasks from
 * the file and saving tasks in the file.
 */
public class Storage {

    private static String filePath;

    /**
     * Creates a new file for storing tasks using the input filepath if the file
     * does not exist.
     * If the file exists, read the contents of the file to get all existing tasks.
     * @param filePath path to the .txt file that stores all tasks.
     */
    public Storage(String filePath) {
        File f = new File(filePath);
        Storage.filePath = filePath;
        if(f.exists() && !f.isDirectory()) {
            ReadFile(f);
        }
    }

    /**
     * Add task to the .txt file that stores all tasks.
     * @param textToAppend String format of the task to add into the .txt file.
     * @throws IOException if the .txt file cannot be found.
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Storage.filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Add task to an empty .txt file that stores all tasks.
     * @param textToAdd String format of the task to add into the .txt file.
     * @throws IOException if the .txt file cannot be found.
     */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(Storage.filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void ReadFile(File fileObj) {
        try {
            Scanner fileReader = new Scanner(fileObj);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                char taskType = line.charAt(4);
                char doneStatus = line.charAt(7);
                String subLine = line.substring(10);
                switch (taskType) {
                    case 'T':
                        Todo t = new Todo(subLine);
                        if (doneStatus == 'X') {
                            t.setDone();
                        }
                        TaskList.allTasks.add(t);
                        break;
                    case 'D':
                        String[] deadlineParts = subLine.split(" \\(by: ");
                        Deadline d = new Deadline(deadlineParts[0], deadlineParts[1].substring(0, deadlineParts[1].length() -1));
                        if (doneStatus == 'X') {
                            d.setDone();
                        }
                        TaskList.allTasks.add(d);
                        break;
                    case 'E':
                        String[] eventParts = subLine.split(" \\(at: ");
                        Event e = new Event(eventParts[0], eventParts[1].substring(0, eventParts[1].length()-1));
                        if (doneStatus == 'X') {
                            e.setDone();
                        }
                        TaskList.allTasks.add(e);
                        break;
                    default:
                        break;
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Get an ArrayList of existing tasks from the .txt file.
     * @return ArrayList<Task></Task> TaskList.allTasks
     */
    public static ArrayList<Task> getSavedTasks(){
        return TaskList.allTasks;
    }

    //
}
