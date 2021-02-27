package file.storage;

import command.parser.CommandsHandler;
import command.parser.ListCommand;
import exceptions.IllegalListException;
import ui.UI;
import task.list.Deadline;
import task.list.Event;
import task.list.Task;
import task.list.Todo;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import command.parser.Parser;

/**
 * Implements the loading and saving of the list from and to the arthur.txt file
 */
public class Storage {

    public static final String FILE_PATH = "arthur.txt";

    /**
     * opens arthur.txt to read
     *
     * @param tasks is the list of tasks
     */
    public static void loadFromFile(ArrayList<Task> tasks) {
        try {
            File fileDescriptor = new File(FILE_PATH); // create a File for the given file path
            Scanner input = new Scanner(fileDescriptor); // create a Scanner using the File as the source
            readDataFile(input, tasks);
            printOldList(tasks);
        } catch (java.io.IOException e) {
            UI.printNoSavedFile();
        }
    }

    /**
     * reads all lines from arthur.txt and adds then to the list
     *
     * @param input is the scanner reading from the file
     * @param tasks is the list of tasks
     */
    private static void readDataFile(Scanner input, ArrayList<Task> tasks) {
        int i = 0;
        UI.printLoading();
        while (input.hasNext()) {
            CommandsHandler.handleCorruptedFile(input, tasks);
            if (input.nextLine().equals("true") && i < tasks.size()) {
                Task t = tasks.get(i);
                t.setDone();
                tasks.set(i, t);
            }
            i++;
        }
    }

    /**
     * prints the list of tasks obtained when the file is read after arthur has started
     *
     * @param tasks is the list of tasks
     */
    private static void printOldList(ArrayList<Task> tasks) {
        try {
            ListCommand.printAllLists(tasks);
        } catch (IllegalListException e) {
            UI.printEmptyFile();
        }
    }

    /**
     * initiates saving list into arthur.txt file
     *
     * @param tasks is the list of tasks
     */
    public static void saveToFile(ArrayList<Task> tasks) {
        boolean hasSaved;
        do {
            hasSaved = hasWrittenToFile(tasks);
        } while (!hasSaved);
    }

    /**
     * ensures that all tasks in list have been to the arthur.txt file
     *
     * @param tasks is the list of tasks
     * @return whether all tasks have been saved
     */
    private static boolean hasWrittenToFile(ArrayList<Task> tasks) {
        boolean hasSaved = false;
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            hasSaved = true;
            for (Task task : tasks) {
                hasSaved = hasWrittenTaskToFile(fileWriter, task);
            }
            fileWriter.close();
        } catch (java.io.IOException e) {
            File file = new File(FILE_PATH);
        }
        return hasSaved;
    }

    /**
     * ensures that one task has been written to the arthur.txt file
     *
     * @param fileWriter is the file descriptor
     * @param task       is one task from the line
     * @return whether the task has been wriiten
     */
    private static boolean hasWrittenTaskToFile(FileWriter fileWriter, Task task) {
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