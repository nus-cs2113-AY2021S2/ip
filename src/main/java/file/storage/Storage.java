package file.storage;
import command.parser.ListCommand;
import exceptions.IllegalListException;
import ui.UI;
import task.list.Deadline;
import task.list.Event;
import task.list.TaskList;
import task.list.Todo;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import command.parser.Parser;

public class Storage {

    public static final String FILE_PATH = "duke.txt";

    public static void loadFromFile(ArrayList<TaskList> tasks) {
        try {
            File fileDescriptor = new File(FILE_PATH); // create a File for the given file path
            Scanner input = new Scanner(fileDescriptor); // create a Scanner using the File as the source
            readDataFile(input, tasks);
            printOldList(tasks);
        } catch (java.io.IOException e) {
            UI.printNoSavedFile();
        }
    }

    private static void readDataFile(Scanner input, ArrayList<TaskList> tasks) {
        int i = 0;
        UI.printLoading();
        while (input.hasNext()) {
            Parser.selectCommand(input.nextLine(), tasks);
            if (input.nextLine().equals("true")) {
                TaskList t = tasks.get(i);
                t.setDone();
                tasks.set(i, t);
            }
            i++;
        }
    }

    private static void printOldList(ArrayList<TaskList> tasks) {
        try {
            ListCommand.printAllLists(tasks);
        } catch (IllegalListException e) {
            UI.printEmptyFile();
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
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            hasSaved = true;
            for (TaskList task : tasks) {
                hasSaved = hasWrittenToFile(fileWriter, task);
            }
            fileWriter.close();
        } catch (java.io.IOException e) {
            File file = new File(FILE_PATH);
        }
        return hasSaved;
    }

    private static boolean hasWrittenToFile(FileWriter fileWriter, TaskList task) {
        boolean hasSaved = true;
        if (task instanceof Event) {
            Event temp = (Event) task;
            try {
                fileWriter.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }

        } else if (task instanceof Deadline) {
            Deadline temp = (Deadline) task;
            try {
                fileWriter.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }

        } else if (task instanceof Todo) {
            Todo temp = (Todo) task;
            try {
                fileWriter.write(temp.getTaskToPrintInFile());
            } catch (java.io.IOException e) {
                hasSaved = false;
            }
        }
        return hasSaved;
    }
}