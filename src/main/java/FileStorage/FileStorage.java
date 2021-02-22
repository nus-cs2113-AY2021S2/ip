package FileStorage;

import exceptions.IllegalListException;
import list.duke;
import list.Deadline;
import list.Event;
import list.TaskList;
import list.Todo;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import list.Commands;

public class FileStorage {

    public static final String FILE_PATH = "duke.txt";

    public static void loadFromFile(ArrayList<TaskList> tasks) {
        try {
            File fd = new File(FILE_PATH); // create a File for the given file path
            Scanner s = new Scanner(fd); // create a Scanner using the File as the source
            readDataFile(s, tasks);
            printOldList(tasks);
        } catch (java.io.IOException e) {
            duke.printNoSavedFile();
        }
    }

    private static void readDataFile(Scanner s, ArrayList<TaskList> tasks) {
        int i = 0;
        duke.printLoading();
        while (s.hasNext()) {
            Commands.selectCommand(s.nextLine(), tasks);
            if (s.nextLine().equals("true")) {
                TaskList t = tasks.get(i);
                t.setDone();
                tasks.set(i, t);
            }
            i++;
        }
    }

    private static void printOldList(ArrayList<TaskList> tasks) {
        try {
            Commands.printAllLists(tasks);
        } catch (IllegalListException e) {
            duke.printEmptyFile();
        }
    }

    public static void saveToFile(ArrayList<TaskList> tasks) {
        boolean hasSaved;
        do {
            hasSaved = writeToFile(tasks);
        } while (!hasSaved);
    }

    private static boolean writeToFile(ArrayList<TaskList> tasks) {
        boolean hasSaved = false;
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            hasSaved = true;
            for (TaskList t : tasks) {
                hasSaved = writeTaskToFile(fw, t);
            }
            fw.close();
        } catch (java.io.IOException e) {
            File file = new File(FILE_PATH);
        }
        return hasSaved;
    }

    private static boolean writeTaskToFile(FileWriter fw, TaskList t) {
        boolean hasSaved = true;
        if (t instanceof Event) {
            Event temp = (Event) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }

        } else if (t instanceof Deadline) {
            Deadline temp = (Deadline) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }

        } else if (t instanceof Todo) {
            Todo temp = (Todo) t;
            try {
                fw.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }
        }
        return hasSaved;
    }
}
