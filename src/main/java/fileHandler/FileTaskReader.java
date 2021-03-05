package fileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Represent the handler that processes the text file and converts the content to a task list
 */
public class FileTaskReader {
    private File source;
    public final static String SEPERATOR = " \\| ";

    public FileTaskReader(File file) {
        source = file;
    }

    /**
     * Convert the content of the file into a task list
     * @return the task list
     * @throws FileNotFoundException if the file doesn't exist
     */
    public ArrayList<Task> parseToArraylist() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(source);
        while(sc.hasNext()) {
            String currentLine = sc.nextLine();
            String[] taskParts = currentLine.split(SEPERATOR);
            switch (taskParts[0]) {
            case "T":
                tasks.add(new ToDo(taskParts[2], false));
                break;
            case "D":
                tasks.add(new Deadline(taskParts[2], false, taskParts[3]));
                break;
            case "E":
                tasks.add(new Event(taskParts[2], false, taskParts[3]));
                break;
            default:
                System.out.println("Unable to recognize input format: " + currentLine);
                break;
            }
            if(taskParts[1].equals("1")) {
                tasks.get(tasks.size()-1).setDone(true);
            }
        }
        return tasks;
    }

}
