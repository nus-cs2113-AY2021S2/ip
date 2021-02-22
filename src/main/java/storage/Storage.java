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

public class Storage {

    private static String filePath;

    public Storage(String filePath) {
        File f = new File(filePath);
        Storage.filePath = filePath;
        if(f.exists() && !f.isDirectory()) {
            ReadFile(f);
        }
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Storage.filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

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

    public static ArrayList<Task> getSavedTasks(){
        return TaskList.allTasks;
    }

    //
}
