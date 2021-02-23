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

/**
 * Implements the loading and saving of the list from and to the duke.txt file
 */
public class Storage {

    public static final String FILE_PATH = "duke.txt";

    // opens duke.txt to read
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

    // reads all lines from duke.txt and adds then to the list
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

    // prints the list of tasks obtained when the file is read after Duke has started
    private static void printOldList(ArrayList<TaskList> tasks) {
        try {
            ListCommand.printAllLists(tasks);
        } catch (IllegalListException e) {
            UI.printEmptyFile();
        }
    }

    // initiates saving list into duke.txt file
    public static void saveToFile(ArrayList<TaskList> tasks) {
        boolean hasSaved;
        do {
            hasSaved = hasWrittenToFile(tasks);
        } while (!hasSaved);
    }

    // ensures that all tasks in list have been to the duke.txt file
    private static boolean hasWrittenToFile(ArrayList<TaskList> tasks) {
        boolean hasSaved = false;
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            hasSaved = true;
            for (TaskList task : tasks) {
                hasSaved = hasWrittenTaskToFile(fileWriter, task);
            }
            fileWriter.close();
        } catch (java.io.IOException e) {
            File file = new File(FILE_PATH);
        }
        return hasSaved;
    }

    // ensures that one task has been written to the duke.txt file
    private static boolean hasWrittenTaskToFile(FileWriter fileWriter, TaskList task) {
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