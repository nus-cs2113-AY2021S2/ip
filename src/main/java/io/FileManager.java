package io;

import models.Task;
import parser.DataParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static DataParser dataParser;

    public FileManager() {
        dataParser = new DataParser();
    }

    /**
     * Saves the current list of Tasks and writes them to a Text file
     *
     * @param tasks ArrayList of Tasks to be saved
     * @throws IOException Exception while trying to create a new File
     */
    public void saveFile(ArrayList<Task> tasks) throws IOException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            if (!path.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(path);
        for (Task task : tasks) {
            fileWriter.write(task.formatData());
        }
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Loads the TaskList by reading the tasks from the Text file
     *
     * @return taskList ArrayList of previously saved Tasks
     * @throws FileNotFoundException Exception when Text file does not exist
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();

        File path = new File("tasks.txt");
        if (!path.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(path);
        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = dataParser.parseData(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to load!");
        }
        return taskList;
    }
}
