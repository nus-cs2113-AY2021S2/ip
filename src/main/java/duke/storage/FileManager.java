package duke.storage;

import duke.exception.DataErrorException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import static duke.constants.FileIOStrings.*;
import static duke.Duke.tasks;
import static duke.Duke.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Manages file operations to read and store data locally.
 */

public class FileManager {

    /**
     * Reads from existing data file if present. If no existing data is found, a new file will be created.
     */
    public static void checkSavedData() {
        try {
            readFromFile();
            System.out.println(FILE_FOUND_MESSAGE);
        } catch (FileNotFoundException e) {
            System.out.println(NO_FILE_FOUND_MESSAGE);
            createNewFile();
        }
    }

    /**
     * Writes existing tasks in the list into data file. Overwrites existing file data.
     */
    public static void saveData() {
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println(SAVE_FILE_ERROR_MESSAGE);
        }
    }

    /**
     * Creates a new data file (.txt) at specified {@code PATH}.
     */
    private static void createNewFile() {
        File file = new File(PATH);
        try {
            if (file.createNewFile()) {
                System.out.println(CREATE_FILE_SUCCESS);
            }
        } catch (IOException e) {
            System.out.println(CREATE_FILE_ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Reads input from data file line by line until EOF, converting each line into a task 
     * and adding the task to the task list.
     * 
     * @throws FileNotFoundException if data file specified by {@code PATH} does not exist.
     */
    private static void readFromFile() throws FileNotFoundException {

        File dataFile = new File(PATH);
        Scanner reader = new Scanner(dataFile);

        while (reader.hasNext()) {
            int lineNumber = 0;
            String line = reader.nextLine();
            lineNumber++;

            try {
                Task task = formTask(line);
                tasks.add(task);
            } catch (DataErrorException e) {
                ui.printDataErrorWarning(lineNumber);
            }
        }
    }

    /**
     * Converts each task in the task list to a string and writing it to the data file.
     * 
     * @throws IOException if an IO error is encountered when writing to the file.
     */
    private static void writeToFile() throws IOException {
        FileWriter writer = new FileWriter(PATH);

        for (Task t : tasks.getTasks()) {
            writer.write(formLine(t) + '\n');
        }
        writer.close();
    }

    /**
     * Encodes the given task into a decodable and readable string representation for storage.
     * 
     * @param t task to be encoded
     * @return string representation of the given task
     */
    private static String formLine(Task t) {
        String line = "";

        if (t instanceof Todo) {
            line += "T" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.getDescription();
        } else if (t instanceof Deadline) {
            line += "D" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.getDescription() + DELIM;
            line += ((Deadline) t).getBy();
        } else if (t instanceof Event) {
            line += "E" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.getDescription() + DELIM;
            line += ((Event) t).getAt();
        }
        return line;
    }

    /**
     * Decodes given string into a {@code Task} object.
     * 
     * @param line string to be decoded
     * @return {@code Task} object formed from given string
     * @throws DataErrorException if string cannot be decoded properly
     */
    private static Task formTask(String line) throws DataErrorException {
        String[] words = line.split(DELIM);

        // type of task
        String type = words[0];

        // done
        boolean isDone = "1".equals(words[1]);

        // job description
        String job = words[2];
        Task newTask;

        switch (type) {
        case "D":
            newTask = new Deadline(job, words[3]);
            break;
        case "E":
            newTask = new Event(job, words[3]);
            break;
        case "T":
            newTask = new Todo(job);
            break;
        default:
            throw new DataErrorException();
        }
        newTask.setDone(isDone);
        
        return newTask;
    }
}
