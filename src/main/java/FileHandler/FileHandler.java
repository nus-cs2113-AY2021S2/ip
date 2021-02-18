package FileHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import TaskClass.Deadline;
import TaskClass.Event;
import TaskClass.Task;
import TaskClass.ToDo;

public class FileHandler {
    private File source;
    public final static String SEPERATOR = " \\| ";

    public FileHandler(File file) {
        source = file;
    }

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
